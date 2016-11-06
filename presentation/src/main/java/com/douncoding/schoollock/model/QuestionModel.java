package com.douncoding.schoollock.model;

/**
 * Created by douncoding on 2016. 11. 6..
 */

public class QuestionModel {
    String writer;
    String content;

    public QuestionModel(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
