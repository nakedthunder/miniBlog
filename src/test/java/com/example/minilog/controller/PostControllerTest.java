package com.example.minilog.controller;

import com.example.minilog.domain.Post;
import com.example.minilog.repository.PostRepository;
import com.example.minilog.request.PostCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
//@WebMvcTest
@SpringBootTest
class PostControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청시 Hello World를 출력한다. ")
    void test() throws Exception {

        //given
        //PostCreate request = new PostCreate("제목입니다.", "내용입니다.");
        //순서가 바뀌더라고 값을 명확하게 지정해서 문제가 없다.
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //객체를 새로 생성할 거 없이 스프릥부트에서 오브젝트 빈을 제공을 해준다.
        //설정을 바꾸고 싶을때는 새로 만드는게..
        /*ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);*/
        String json = objectMapper.writeValueAsString(request);


        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON) //컨텐츠타입 기본값 JSON 데이터 타입으로 명시해주기
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andDo(print()) //HTTP요청에 대한 summary를 남겨주게되서 응답내용 보여줌 
        ;

    }

    @Test
    @DisplayName("/posts 요청시 title값은 필수다.")
    void test2() throws Exception {

        PostCreate request = PostCreate.builder()
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON) //컨텐츠타입 기본값 JSON 데이터 타입으로 명시해주기
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                //jsonPath moveMVC resultMatchers: value에 notBlank메세지랑 맞춰야한다. 검증방법
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print()) //HTTP요청에 대한 summary를 남겨주게되서 응답내용 보여줌
        ;

    }

    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다..")
    void test3() throws Exception {

        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON) //컨텐츠타입 기본값 JSON 데이터 타입으로 명시해주기
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print()); //HTTP요청에 대한 summary를 남겨주게되서 응답내용 보여줌

        //then
        assertEquals(1L, postRepository.count());

        // assertEquals() 실제 요청한 값과 DB의 저장값이 맞는지 검증해봄
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());

        //요청이 완료되면 DB에 row가 하나가 저장이된다. 그래서 count가 한개가 들어간다.
        //그래서 검증을함.

        // @SpringBootTest //MockMvc 빈타입이 주입이 안됨
        // 현재 서비스, 레파지토리 생성해서
        //PostController실패로 Bean을 찾을 수 없음. 서비스, 레파지토리를 만들어서 애플리케이션의
        //전반적인 테스트여서 새로운 빈 주입을 해야한다.
        //@WebMvcTest안에 @AutoConfigureMockMvc를 달아준다. -> Bean 주입됨


        // db -> post 1개 등록인테 2개등록된 이유
        //전체테스트를 돌리면 실패가 되는데 실제값이 2개임. 왜냐하면 맨처음에 짠 테스트가
        //실행이됬고, 3번쨰가 실행이 되서 2개의 값이 들어감.
        //그러면 테스트가 늘릴수록 count를 늘여야되나? .... expect값을 바꿔저야함.
        //영향이 안가도록

        //clean()
        //postRepository.deleteAll() 방식은 있는데 지저분한 방식...매번 개발자가 신경을 써야함
        //각각의 테스트들이 클린하게 @BeforeEach로 -> 테스트 메소드들이 가각실행되기전에 수행이되도록
        //보장을 해준다. 각각의 메소드들이  수행하기전에 실행된다.
        //테스트 케이스를 짜기전에 항상 클린해주는 환경을 만듬 *중요*
    }

    @Test
    @DisplayName("글 1개 조회")
    void test4() throws Exception {
        //글 저장하고
        Post post = Post.builder()
                .title("123456789012345")
                .content("bar")
                .build();
        postRepository.save(post);

        //조회 api를 통해서 JSON으로 응답이 잘 내려오는지 확인
        mockMvc.perform(get("/posts/{postId}", post.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("1234567890"))
                .andExpect(jsonPath("$.content").value("bar"))
                .andDo(print());
    }
}