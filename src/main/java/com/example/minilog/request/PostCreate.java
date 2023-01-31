package com.example.minilog.request;

public class PostCreate {
    public String title;
    public String content;

    //요청이 왔을 때 넣어주는 set, get
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //control + enter
    @Override
    public String toString() {
        return "PostCreate{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
