package com.example.minilog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*
     *  실제로 컨틀롤러에 요청을 날려볼라함.ㅣ
     * */
    @Test
    @DisplayName("/posts 요청시 Hello World를 출력한다. ")
    void test() throws Exception {
        /*
        * 보낼 데이터: 글 제목, 글 내용
        * mockMvc는 데이터형태를 application/JSON타입으로 보낸다.
        *
        * Content-Type은 서버로 요청할때 받을 떄 HTTP의 헤더값이다. 보낼떄는 어떤
        * 데이터 형태인지 너머 올떄는 넘어온 데이터가 어떤 데이터 형태를 알려줌
        * - 보낼때는 application/json형태로 보내는데 예전에는 x-www-form-urlencoded타입이였다.
        * 웹 서비스를 하는 아이티회사에서는 json형태로 통신을 하는 추세..
        *
        * -  .andDo(print()) 를 추가함으로써 HTTP요청에 대한 summary를 남겨주게되서 응답내용을 볼 수 있다.
        *
        * JSON데이터로 통신
        * 사용자의 데이터는 많을텐데..데이더를 표현하는데 한계가 있어 JSON데이터로 만들어서 보낸다.
        * */

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON) //컨텐츠타입 기본값 JSON 데이터 타입으로 명시해주기
                        .content("{\"title\":  \"제목입니다.\", \"content\": \"내용입니다.\" }")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello World"))
                .andDo(print()) //HTTP요청에 대한 summary를 남겨주게되서 응답내용 보여줌 
        ;

    }
}