package com.example.minilog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/posts 요청시 Hello World를 출력한다. ")
    void test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON) //컨텐츠타입 기본값 JSON 데이터 타입으로 명시해주기
                        .content("{\"title\":  \"제목입니다.\", \"content\": \"내용입니다.\" }")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{}"))
                .andDo(print()) //HTTP요청에 대한 summary를 남겨주게되서 응답내용 보여줌 
        ;

    }

    @Test
    @DisplayName("/posts 요청시 title값은 필수다.")
    void test2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON) //컨텐츠타입 기본값 JSON 데이터 타입으로 명시해주기
                        .content("{\"title\":  null, \"content\": \"내용입니다.\" }")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                //jsonPath moveMVC resultMatchers: value에 notBlank메세지랑 맞춰야한다. 검증방법
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print()) //HTTP요청에 대한 summary를 남겨주게되서 응답내용 보여줌
        ;

    }
}