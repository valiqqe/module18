package com.example.module18.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();
}