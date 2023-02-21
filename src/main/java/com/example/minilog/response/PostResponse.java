package com.example.minilog.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//서비스 정책에 맞는 클래스
@Getter
@Builder
public class PostResponse {
    private final Long id;
    private final String title;
    private final String content;

    //타이틀을 멤버변수에 세팅을 해주면 getter메서드는 손을 안바도된다.
    //이거는 버그여지발생이 있다. 글자가 5개 인경우...
    // StringIndexOutOfBoundsException: begin 0, end 10, length 5
    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 10));
        this.content = content;
    }
}
