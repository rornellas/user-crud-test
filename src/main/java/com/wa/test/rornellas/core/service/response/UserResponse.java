package com.wa.test.rornellas.core.service.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String document;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
