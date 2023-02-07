package com.example.minilog.controller;
import com.example.minilog.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse exceptionHandler(MethodArgumentNotValidException e) {
         ErrorResponse response = new ErrorResponse("400", "잘못된 요청입니다.");

         //arrayList안에 getFieldErrors 필드객체가 담겨져있음
        for (FieldError fieldError : e.getFieldErrors()) {
            //ErrorResponse클래스에 addValidation 메소드 생성
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return response;
    }
}
