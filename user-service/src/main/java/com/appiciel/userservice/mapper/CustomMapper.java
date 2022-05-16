package com.appiciel.userservice.mapper;

import com.appiciel.userservice.domain.User;
import com.appiciel.userservice.dto.CreateUserDTO;
import com.appiciel.userservice.dto.GetUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomMapper {
    GetUserDTO userToGetUserDTO(User user);
    User createUserDTOToUser(CreateUserDTO createUserDTO);
}
