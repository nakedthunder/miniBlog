package com.example.minilog.controller;

import com.example.minilog.domain.Post;
import com.example.minilog.request.PostCreate;
import com.example.minilog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) {
        /*
            1. 컨트롤러가있고
            2. 서비스가 있고
            3. 레파지토리가 있다.
            4. 순차적으로 호출해서 최종적으로 넘어온 JSON값을 postEntity로 변환을 해서 저장하는 형태로 코딩을함.
            - 테스트 케이스는 나중에…
        */
        postService.write(request);
        //BEAN JSON 객체가 내려가고있다.
    }

    /*
     * 글 조회를 하는 부분
     * 1. /posts -> 글 전체 조회 (검색 + 페이징)
     * 2. /posts/{postId} -> 글 한개만 조회
     *
     * 다시 컨트롤러로 와서 최종적으로 JSON으로 반환을 해줌 return post;
     * */
    @GetMapping("posts/{postId}")
    public Post get(@PathVariable Long postId) {
        // 포스트 서비스에 글 한개만 가져오는 메소드를 만들어서 여기서 호출해야함
        Post post = postService.get(postId);
        return post;
    }
}