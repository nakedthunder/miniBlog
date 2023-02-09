package com.example.minilog.controller;

import com.example.minilog.request.PostCreate;
import com.example.minilog.service.PostService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate request){
        /*
            1. 컨트롤러가있고
            2. 서비스가 있고
            3. 레파지토리가 있다.
            4. 순차적으로 호출해서 최종적으로 넘어온 JSON값을 postEntity로 변환을 해서 저장하는 형태로 코딩을함.
            - 테스트 케이스는 나중에…
        */
        postService.write(request);
        //BEAN JSON 객체가 내려가고있다. 
        return Map.of();
    }
}
