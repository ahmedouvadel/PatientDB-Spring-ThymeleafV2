package com.exmple.patientsmvc.Service.IService;

import com.exmple.patientsmvc.Dto.UserDto;
import com.exmple.patientsmvc.entities.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}