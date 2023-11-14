package com.ScootersApp.controller;

import com.ScootersApp.Service.AccountService;
import com.ScootersApp.Service.DTOs.account.request.AccountRequestDTO;
import com.ScootersApp.Service.DTOs.account.response.AccountResponseDTO;
import com.ScootersApp.Service.DTOs.userAccount.response.UserAccountResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService=accountService;
    }

    @PostMapping("/")
    public ResponseEntity save(@RequestBody @Valid AccountRequestDTO request){
        return accountService.save(request);
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getAccountById(@PathVariable Long id){
        return accountService.findAccountById(id);
    }

    @GetMapping("/{id}/users") //get associated users from the account
    public List<UserAccountResponseDTO> getAllUserAccountsByAccountId(@PathVariable Long id){
        return accountService.findAllUserAccounByAccountId(id);
    }

    @GetMapping("/")
    public List<AccountResponseDTO> getAllAccounts(){
        return this.accountService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long id) {
        this.accountService.deleteAccount(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updateAccount(@RequestBody @Valid AccountRequestDTO request, @PathVariable Long id){
        return accountService.updateAccount(request,id);
    }


}
