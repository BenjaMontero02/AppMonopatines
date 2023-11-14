package com.ScootersApp.controller;

import com.ScootersApp.Service.DTOs.Role.request.RoleRequest;
import com.ScootersApp.Service.DTOs.User.request.UserRequest;
import com.ScootersApp.Service.DTOs.User.response.UserLoginResponseDTO;
import com.ScootersApp.Service.DTOs.User.response.UserResponseDTO;
import com.ScootersApp.Service.DTOs.userAccount.request.UserAccountRequestDTO;
import com.ScootersApp.Service.DTOs.userAccount.response.UserAccountResponseDTO;
import com.ScootersApp.Service.UserService;
import com.ScootersApp.domain.DisableDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<UserResponseDTO> getAllUsers(){
        System.out.println("llego");
        return this.service.findAll();
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody @Valid UserRequest user){
        return  this.service.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        this.service.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUser(@RequestBody @Valid UserRequest userRequest, @PathVariable Long id){
        return this.service.updateUser(userRequest, id);
    }


    @GetMapping("/login/{mail}")
    public ResponseEntity<UserLoginResponseDTO> login(@PathVariable String mail){
        return this.service.findByMail(mail);
    }


    @GetMapping("/{mail}")
    public ResponseEntity<UserResponseDTO> getByMail(@PathVariable String mail){
        return this.service.getUserByMail(mail);
    }
    @PostMapping("/accounts/")
    public ResponseEntity<UserAccountResponseDTO> saveNewUserAccount(@RequestBody @Valid UserAccountRequestDTO userAccountRequest){
        return this.service.saveNewUserAccount(userAccountRequest);
    }
    @GetMapping("/{id}/accounts")
    public List<UserAccountResponseDTO> getUserAccountByUserId(@PathVariable Long id){
        return this.service.getUserAccountByUserId(id);
    }

    @PutMapping("/{mail}/disable")
    public ResponseEntity<String> disableUser(@PathVariable String mail){
        return this.service.disableUser(mail);
    }

    @PutMapping("/{mail}/enable")
    public ResponseEntity<String> enableUser(@PathVariable String mail){
        return this.service.enableUser(mail);
    }

    @GetMapping("/accounts/")
    public List<UserAccountResponseDTO> getAllUserAccount(){
        return this.service.getAllUserAccount();
    }

    @DeleteMapping("/{id}/accounts/{idAccount}")
    public void deleteUserAccount(@PathVariable Long id, @PathVariable Long idAccount){
        this.service.deleteUserAccount(id, idAccount);
    }
}
