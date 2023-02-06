package com.example.minilog.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> exceptionHandler(MethodArgumentNotValidException e) {
        /*
        * e를 찍었을 떄 넘어온 타입이 MethodArgumentNotValidException
        * 내용을 토대로 클라이언트에게 JSON응답을 만들어봄... e.getField()
        * 필드에러의 내용이 담겨져 있지 않아서 MethodArgumentNotValidException이 발생할떄만
        * 작동을 하게 변경을 함.
        * */
        log.error("exceptionHandler error 입니다: ", e);
        FieldError fieldError = e.getFieldError();
        /*
        * fieldError getField, defaultMessage로 필드명/에러메세지(postCreate에서 넣어준거)
        * */
        String field = fieldError.getField();
        String message = fieldError.getDefaultMessage();

        /*
        * JSON으로 넘겨주는 방법
        * Map hashMap으로 통해서 넘겨준다.
        * 리턴이 될지... Map으로 잘 바꿔줬지만... 에러가남 -> 작동이 안됨
        * 컨트롤러 어드바이스는 데이터를 뭔가 뷰리졸버를 찾아갈라는데 ... 검색: 컨트롤러어드바이스를 통해 ..모델엔뷰데이터를
        * 생성해서 넘겨줘 (일반적인 방법은 아님) ..-> 리턴 데이터를 해쉬맵으로 제이슨으로 리턴은 안한다..
        *
        * @ResponseBody를 추가하면... 잘 넘어왔네 이걸 통해서 만들었던 해쉬맵으로 잘 응답을 해줄수 있다.
        * 레스트컨트롤러에 리스폰스바디를 가지고 있다. 그래서 ResponseBody가 뭔지 보면은 ...
        * - responseBody는 모델을 뷰로 렌더링하지않고 반환된 객체를 .. 응답본문에 쓰기를 한다.
        * 컨트롤러 어드바이스 에도 응답본문을 본문에 쓰드록 할 수 있었던것......약간 불편....
        * 필드 에러에 대한 응답클래스를 만들지 않음. null포인트 가능성이 있고 ...
        * 그래서 해쉬맴말고 응답 전용클래스를 만듬
        *
        * */
        Map<String, String> response = new HashMap<>();
        response.put(field, message);
        return response;
    }
}
