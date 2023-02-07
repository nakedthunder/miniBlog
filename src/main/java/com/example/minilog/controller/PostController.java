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
        return Map.of();
    }
}
