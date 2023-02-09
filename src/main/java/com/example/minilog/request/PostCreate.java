package com.example.minilog.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요.")
    private String title;

    @NotBlank(message = "콘텐츠를 입력하주세요.")
    private String content;
    @Builder //디자인패턴, 생성자위에 다는것을 추천
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
