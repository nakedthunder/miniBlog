package com.example.minilog.service;

import com.example.minilog.domain.Post;
import com.example.minilog.repository.PostRepository;
import com.example.minilog.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor  //생성자 인젝션
public class PostService {


    private final PostRepository postRepository;


    public void write(PostCreate postCreate) {
        /*
        * PostCreate는 현재 request DTO형태이지 Entity형태가 아니여서 들어가지지 않음
        * 그래서 PostCreate -> entity 형태로 변환
        * */
        Post post = new Post(postCreate.getTitle(), postCreate.getContent());
        postRepository.save(post);
    }
}
