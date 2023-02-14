package com.example.minilog.service;

import com.example.minilog.domain.Post;
import com.example.minilog.repository.PostRepository;
import com.example.minilog.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        //Post post = new Post(postCreate.getTitle(), postCreate.getContent());
        Post post = Post.builder()
                        .title(postCreate.getTitle())
                                .content(postCreate.getContent())
                                        .build();

        postRepository.save(post);
    }

    public Post get(Long postId) {
        //findById가 post엔티티를 바로 반환하지않고 옵셔널데이터로 감싸져서 반환을 해줌
        //Optional데이터는 바로 가져와서 즉시 꺼내주는걸 추천함
        //글 데이터가 없을때(null)의 조치를 하는거면 orElse인 경우 "없다!" 에러를 만들어줌
        // 엑셉션을 떤져줌....
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글 입니다."));

        return post;
    }
}
