package com.example.minilog.controller;

import com.example.minilog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
public class PostController {

    @PostMapping("/posts")
    public String post(@RequestBody @Valid PostCreate params){
        log.info("params={}", params.toString()); //@Slfj를 통해 롬복사용하기, toString()을 통해서 문자값보여주게
        return "Hello World";
    }
}
