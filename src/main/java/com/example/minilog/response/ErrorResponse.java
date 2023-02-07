package com.example.minilog.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    /*
    * 응답전용 클래스
    * 응답은 향후의 json으로 응답을 했을떄 반환이 되게 작성
    * */
    private final String code;
    private final String message;
    //null이여서 new HashMap<>(); 해줌
    private final Map<String, String> validation = new HashMap<>();

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
