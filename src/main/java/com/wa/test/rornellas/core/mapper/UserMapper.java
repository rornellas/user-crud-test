package com.wa.test.rornellas.core.mapper;

import com.wa.test.rornellas.core.service.request.UserRequest;
import com.wa.test.rornellas.core.service.response.UserResponse;
import com.wa.test.rornellas.data.entity.User;
import com.wa.test.rornellas.data.entity.UserImpl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    UserImpl toUserEntity(UserRequest userRequest);
}
