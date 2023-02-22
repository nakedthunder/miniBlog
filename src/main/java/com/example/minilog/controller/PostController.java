package com.example.minilog.controller;

import com.example.minilog.domain.Post;
import com.example.minilog.request.PostCreate;
import com.example.minilog.response.PostResponse;
import com.example.minilog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    // 0215
    // 게시글을 저장하는거 post()
    // 저장한 게시글을 조회하는거 get()
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
    public PostResponse get(@PathVariable Long postId) {
        // 포스트 서비스에 글 한개만 가져오는 메소드를 만들어서 여기서 호출해야함
        // 포스트 컨틀롤러에서 Post엔티티로 그대로 사용하는 상황
        /*PostResponse response = postService.get(postId);
        return response;*/
        return postService.get(postId);
    }

    /*
    * 클라이언트를 위해서 만든 포스트의 getTitle()기능이
    * RSS 정책과 묶여지게 된다.
    * - RSS에서는 제목이 다 내려가야함에도 불구하고 10글자만 내려감
    * */
   /* @GetMapping("posts/{postId}/rss")
    public PostResponse getRss(@PathVariable Long postId) {
        // 포스트 서비스에 글 한개만 가져오는 메소드를 만들어서 여기서 호출해야함
        // 포스트 컨틀롤러에서 Post엔티티로 그대로 사용하는 상황
        PostResponse response = postService.getRss(postId);
        return response;
    }*/

    @GetMapping("/posts")
    public List<Post> getList() {
        return postService.getList();
    }
}

