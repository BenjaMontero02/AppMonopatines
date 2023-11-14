package com.ScootersApp.controller;

import com.ScootersApp.Service.DTOs.User.request.UserRequest;
import com.ScootersApp.Service.DTOs.User.response.UserLoginResponseDTO;
import com.ScootersApp.Service.DTOs.User.response.UserResponseDTO;
import com.ScootersApp.Service.DTOs.userAccount.request.UserAccountRequestDTO;
import com.ScootersApp.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<UserResponseDTO> getAllUsers(){
        return this.service.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity createUser(@RequestBody UserRequest user) throws Exception {
        return  this.service.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        this.service.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody @Valid UserRequest userRequest, @PathVariable Long id){
        return this.service.updateUser(userRequest, id);
    }

   // @PostMapping("/login")
   //public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequest user){
        //UserLoginResponseDTO userRequest = this.service.findByMailAndPassword(user.getMail(), user.getPassword());
    //}
    @GetMapping("/login/{email}")
    public ResponseEntity<UserLoginResponseDTO> login(@PathVariable String email){
        System.out.println(email);
        UserLoginResponseDTO userRequest = this.service.findByMail(email);
        System.out.println(userRequest);

        return new ResponseEntity(userRequest, HttpStatus.OK);
    }

    /*@GetMapping("/{mail}")
    public UserLoginResponseDTO getByMail(@PathVariable String mail){
        return this.service.findByMail(mail);
    }*/

    @GetMapping("/{id}")
    public UserResponseDTO getByID(@PathVariable Long id){
        return this.service.findByID(id);
    }

    @PostMapping("/{id}/account/{idAccount}")
    public ResponseEntity saveNewUserAccount(@PathVariable Long id, @PathVariable Long idAccount){
        return this.service.saveNewUserAccount(id, idAccount);
    }

}
