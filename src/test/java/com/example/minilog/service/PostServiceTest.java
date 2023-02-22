package com.example.minilog.service;

import com.example.minilog.domain.Post;
import com.example.minilog.repository.PostRepository;
import com.example.minilog.request.PostCreate;
import com.example.minilog.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest //포스트 서비스가 주입임 안되서
class PostServiceTest {

    /*
    * 서비스 테스트 만들기
    *
    * 서비스 테스트 클래스 만들어서
    * 데이터 저장에 대한 서비스의 테스트
    * */
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when: 주입받으면 되니깐 글 작성에 대한 포스터데이터를 넘김
        postService.write(postCreate);

        //then: 글 작성을 하니 데이터 검증을 한다. 1개 작성을 해서 1이 된다.
        assertEquals(1L, postRepository.count());
        //글에 대한 내용까지 검증을함.
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }


    /*
    * 게시글을 저장 > 조회하는 서비스를 통해서 요청 > 엔티티 잘 가져오는지 확인
    * */
    @Test
    @DisplayName("글 1개 조회")
    void test2(){
        //given
        //우선은 글을 저장해줘야한다. post엔티티를 입력한다.
        //포스트 레파지토리에 저장을 하면, 상품의 아이디가 들어가져서 get()을 통해서 조회를 한다.
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost);

        /**
         * 요청
         * json응답에서 title값 길이를 최대 10글자로 해주세요라는 응답...사실은 FO에서함
         *
         */
        //when
        PostResponse response = postService.get(requestPost.getId());

        //than
        assertNotNull(response);
        assertEquals("123123123123123123123123123", response.getTitle());
        assertEquals("bar", response.getContent());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test3(){
        Post requestPost1 = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost1);

        Post requestPost2 = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost2);


        //when
        List<Post> posts = postService.getList();

        //than 검증을 해야함 두개를 저장해서 2
        assertEquals(2L, posts.size());

    }
}