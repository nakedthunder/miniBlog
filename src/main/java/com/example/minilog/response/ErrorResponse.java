package com.example.minilog.response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorResponse {
    /*
    * 응답전용 클래스를 만듬
    * 실무에서는 회사마다 규칙이 다름..그래서 팀원끼리 만듬
    *
    * 응답은 향후의 json으로 응답을 했을떄 반환이 되게 작성을함
    * 어떤 필드가 잘못됬는지 알려줄라면 validation오브젝트를 배열하나 만들어서
    * 배열형태를 갔다가 하나 따주는 형태로 진행을 했었다.
    * */
    private final String code;
    private final String message;

}
