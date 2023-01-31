package com.example.minilog.controller;

import com.example.minilog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class PostController {
    /*
    *  SSR: 서버에서 렌더링해서 데이터를 내려줌
    *  SPA: js에서 화면을 만들어주고 api로 통신을 한다. JSON으로 응답처리를 한다.
    * @RestController: ResponseBody + controller
    * */

    // 첫 라우팅은 간단하게 String을 리턴한다. GetMapping 으로 사용
    /*
    *  http://localhost:8080/posts 요청을하면 String을 리턴한다.
    * */
    @PostMapping("/posts")
    public String post(@RequestBody PostCreate params){
        log.info("params={}", params.toString()); //@Slfj를 통해 롬복사용하기, toString()을 통해서 문자값보여주게
        return "Hello World";
    }
}
