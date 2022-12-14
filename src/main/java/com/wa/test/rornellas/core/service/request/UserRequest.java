package com.wa.test.rornellas.core.service.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class UserRequest {
    @NotEmpty
    @Length(max = 100, message = "The name field shouldn't contain more than 100 characters")
    private String name;
    @NotEmpty
    @Length(max = 20, message = "The document field shouldn't contain more than 20 characters")
    private String document;
}
