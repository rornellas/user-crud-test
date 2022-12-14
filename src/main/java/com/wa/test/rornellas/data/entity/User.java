package com.wa.test.rornellas.data.entity;

import java.time.LocalDateTime;

public interface User {
    Long getId();
    void setId(Long id);
    String getDocument();
    void setDocument(String document);
    String getName();
    void setName(String name);
    LocalDateTime getCreatedAt();
    void setCreatedAt(LocalDateTime createdAt);
    LocalDateTime getUpdatedAt();
    void setUpdatedAt(LocalDateTime createdAt);
}
