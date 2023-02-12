package com.example.minilog.service;

import com.example.minilog.domain.Post;
import com.example.minilog.repository.PostRepository;
import com.example.minilog.request.PostCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    @DisplayName("글 작성")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        postService.write(postCreate);

        //then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }
}