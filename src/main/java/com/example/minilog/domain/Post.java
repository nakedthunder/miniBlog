package com.example.minilog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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
    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //10글자만 가져갈 수 있도록 함.
    /*
    * 문제
    * post에서 수정하면 나중에 특정기능이 추가될때 기존정책과 새로운정책이 충돌이 날 수 있다.
    * */
    public String getTitle() {
        return this.title.substring(0, 10);
    }
}
