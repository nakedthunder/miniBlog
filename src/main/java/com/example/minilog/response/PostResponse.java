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

    //subString
    /*생성자를 통해서 @builder를 만들게 되면은
    * this.title은 멤버변수에 셋팅을 해줄때 10글자만 해주면 된다.
    * */

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 10));
        this.content = content;
    }
}
