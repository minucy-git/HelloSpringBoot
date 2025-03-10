package com.example.HelloSpringBoot.web.dto;

import com.example.HelloSpringBoot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id= entity.getId();
        this.title= entity.getTitle();
        this.author= entity.getTitle();
        this.modifiedDate=entity.getModifiedDate();
    }
}
