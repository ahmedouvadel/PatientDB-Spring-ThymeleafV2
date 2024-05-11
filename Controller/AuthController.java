package com.exmple.patientsmvc.Controller;

import com.exmple.patientsmvc.Dto.UserDto;
import com.exmple.patientsmvc.Service.IService.UserService;
import com.exmple.patientsmvc.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService){
        this.userService=userService;
    }

    //handler method to handle home page.
    @GetMapping("/index")
    public String home(){
        return "index";

    }

    //handler method to handle user registeration form request.
    @GetMapping("/register")
    public String showRegistrationForm(Model model){

        UserDto userDto = new UserDto();
        model.addAttribute("user",userDto); //model object is used to store data that is entered from form.
        return "register";

    }
    //handler method to handle user registration form submit request.

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){ //Model attribute is used to extract model object which is a form data.


        User existingUser = userService.findByEmail(userDto.getEmail()); //checking if entered email already exists or not.

        if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email",null,"there is already an account existed with this email");
        }

        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "/register"; // if any form has errors it will be redirected to register page only.
        }

        userService.saveUser(userDto);
        return "redirect:/register?success"; // @Valid from jakarta.validation will enable the validation fields of dto objectsto be enabled.

    }


    //handler methods for handling login request.

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}

