package com.example.module18.entity.dto;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private String title;
    private String content;
}
