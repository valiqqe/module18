package com.example.module18.entity.dto;

import lombok.Data;

@Data
public class UpdateNoteRequest {
    private long id;
    private String title;
    private String content;
}