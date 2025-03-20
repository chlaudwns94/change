package com.korit.mcdonaldkiosk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback {
    private int orderListId;
    private String answer1;
    private String answer2;
    private String answer3;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String q1a1;
    private String q1a2;
    private String q1a3;
    private String q2a1;
    private String q2a2;
    private String q2a3;
    private String q3a1;
    private String q3a2;
    private String q3a3;
}
