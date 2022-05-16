package com.appiciel.userservice.controller;

import com.appiciel.userservice.dto.CreateUserDTO;
import com.appiciel.userservice.dto.GetUserDTO;
import com.appiciel.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("get/{email}")
    GetUserDTO getUser(@PathVariable String email){
        return userService.getUser(email);
    }


    @GetMapping("list")
    List<GetUserDTO> listUsers(){
        return userService.getAll();
    }

    @PostMapping("add")
    ResponseEntity<GetUserDTO> create(@RequestBody CreateUserDTO createUserDTO){
        GetUserDTO getUserDTO = userService.create(createUserDTO);
        return new ResponseEntity<GetUserDTO>(getUserDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{email}")
    void deleteUser(@PathVariable String email){
        userService.deleteUser(email);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> error (){
        return new ResponseEntity<String>("Wrong request",HttpStatus.BAD_REQUEST);
    }

}
