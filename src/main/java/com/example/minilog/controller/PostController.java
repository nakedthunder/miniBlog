package com.example.minilog.controller;

import com.example.minilog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PostController {

    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate params){
        log.info("params={}", params.toString()); //@Slfj를 통해 롬복사용하기, toString()을 통해서 문자값보여주게
       /*
       * BindingResult를 통해 client한테 값을 보여준다.
       * - 테스트 디버그 모드시에 컨트롤러로 걸린다.
       * - hasErrors로 getFieldErrors로 몽땅 가져와서 필드 에러안에있는 값들을 JSON형태로 만들어준다.
       * - get(0); 으로 0번째 데이터를 가져온다.
       * - getField() -> 타이틀
       * - defaultMessage()가 있다.
       * - map에 넣고 return 타입으로 맞춘다.
       * - Map.of();
       * */
        /*if(result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); //title
            String errorMessage = firstFieldError.getDefaultMessage(); //에러메세지

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }*/

        return Map.of();
    }
}
