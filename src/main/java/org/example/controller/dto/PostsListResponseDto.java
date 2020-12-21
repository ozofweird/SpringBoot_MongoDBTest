package org.example.controller.dto;

import lombok.Getter;
import org.example.domain.posts.Posts;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsListResponseDto(Posts document) {
        this.id = document.getId();
        this.title = document.getTitle();
        this.content = document.getContent();
        this.author = document.getAuthor();
    }
}
