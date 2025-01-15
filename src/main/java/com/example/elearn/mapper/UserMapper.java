package com.example.elearn.mapper;

import com.example.elearn.entity.User;
import com.example.elearn.model.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", constant = "STUDENT")
    @Mapping(target = "enabled", constant = "false")
    User toRegister(RegisterRequest request);

}
