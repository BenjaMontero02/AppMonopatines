package com.ScootersApp.Service;

import com.ScootersApp.Service.DTOs.account.request.AccountRequestDTO;
import com.ScootersApp.Service.DTOs.account.response.AccountResponseDTO;
import com.ScootersApp.Service.DTOs.userAccount.response.UserAccountResponseDTO;
import com.ScootersApp.Service.exception.ConflictExistException;
import com.ScootersApp.Service.exception.NotFoundException;
import com.ScootersApp.Service.exception.ReferencedRowException;
import com.ScootersApp.domain.Account;
import com.ScootersApp.domain.UserAccount;
import com.ScootersApp.repository.AccountRepository;
import com.ScootersApp.repository.UserAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("AccountService")
public class AccountService {

    private AccountRepository repository;
    private UserAccountRepository userAccountRepository;

    public AccountService(AccountRepository repository, UserAccountRepository userAccountRepository) {
        this.repository=repository;
        this.userAccountRepository=userAccountRepository;
    }

    @Transactional(readOnly = true)
    public AccountResponseDTO findAccountById(long id) {
        return repository.findById(id).map(AccountResponseDTO::new).orElseThrow(() -> new NotFoundException("Client", "ID", id));
    }

    @Transactional(readOnly = true)
    public List<UserAccountResponseDTO> findAllUserAccounByAccountId(Long id) {
        List<UserAccount> result = userAccountRepository.findAllById_Account(id);
        return result.stream().map(UserAccountResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AccountResponseDTO> findAll() {
        List<Account> accounts = repository.findAll();
        return accounts.stream().map(AccountResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity save(AccountRequestDTO request) {
        if(!this.repository.existsById(request.getId())){
            this.repository.save(new Account(request));
            return new ResponseEntity(request.getId(), HttpStatus.CREATED);
        }

        throw new ConflictExistException("Account", "ID", request.getId());
    }

    @Transactional
    public void deleteAccount(Long id) {
        //if exists i keep on
        if(repository.existsById(id)) {
            //is it ok to check associated accounts?
            List<UserAccount> associatedUsers = userAccountRepository.findAllById_Account(id);
            if(associatedUsers.isEmpty()) {
                this.repository.deleteById(id);
            }
            else {
                throw new ReferencedRowException("Account", "UserAccount", "ID", id);
            }
        }
        else {
            throw new NotFoundException("Account", "ID", id);
        }
    }

    @Transactional
    public ResponseEntity updateAccount(AccountRequestDTO request, Long id) {
        Account accountExisting = this.repository.findById(id).get();
        if(accountExisting != null){ //if exist, keep on
            accountExisting.setWallet(request.getWallet());
            /*accountExisting.setDateUp()*/ //it doesnt have sense
            this.repository.save(accountExisting);
            //another way is by sql with @Modifying***
            return new ResponseEntity(request.getId(), HttpStatus.CREATED);
        }

        throw new NotFoundException("Account", "Long", id);
    }
}
