package com.example.minilog.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC) //접근레벨: PROTECTED -> PUBLIC 변경
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String title;

    @Lob
    public String content;

    /* 외부의 오픈을 안하는 방식으로 생성자를 통해서
    *  변경을 닫는 형식으로..id는 자동생성이니 뺌
    * */
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
