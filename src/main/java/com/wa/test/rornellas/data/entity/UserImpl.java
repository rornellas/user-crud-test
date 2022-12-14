package com.wa.test.rornellas.data.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "auth_user", schema = "PUBLIC")
public class UserImpl implements User {
    @Id
    private Long id;
    private String name;
    private String document;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
