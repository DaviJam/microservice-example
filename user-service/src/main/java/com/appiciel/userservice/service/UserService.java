package com.appiciel.userservice.service;

import com.appiciel.userservice.domain.User;
import com.appiciel.userservice.dto.CreateUserDTO;
import com.appiciel.userservice.dto.GetUserDTO;
import com.appiciel.userservice.mapper.CustomMapper;
import com.appiciel.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private UserRepository userRepository;
    private CustomMapper customMapper;


    public GetUserDTO getUser(String email){
        return customMapper.userToGetUserDTO(userRepository.findByEmail(email));
    }

    public GetUserDTO create(CreateUserDTO createUserDTO) {
        // create the user
        User user = userRepository.save(customMapper.createUserDTOToUser(createUserDTO));
        return customMapper.userToGetUserDTO(user);
    }

    public List<GetUserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> customMapper.userToGetUserDTO(user)).collect(Collectors.toList());
    }

    public void deleteUser(String email) {
        userRepository.delete(userRepository.findByEmail(email));
    }
}
