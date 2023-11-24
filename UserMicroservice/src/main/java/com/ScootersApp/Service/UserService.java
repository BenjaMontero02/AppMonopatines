package com.ScootersApp.Service;

import com.ScootersApp.Service.DTOs.Role.request.RoleRequest;
import com.ScootersApp.Service.DTOs.User.request.UserRequest;
import com.ScootersApp.Service.DTOs.User.response.UserLoginResponseDTO;
import com.ScootersApp.Service.DTOs.User.response.UserResponseDTO;
import com.ScootersApp.Service.DTOs.userAccount.request.UserAccountRequestDTO;
import com.ScootersApp.Service.DTOs.userAccount.response.UserAccountResponseDTO;
import com.ScootersApp.Service.exception.*;
import com.ScootersApp.domain.*;
import com.ScootersApp.repository.AccountRepository;
import com.ScootersApp.repository.RoleRepository;
import com.ScootersApp.repository.UserAccountRepository;
import com.ScootersApp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("UserService")
public class UserService {

    UserRepository repository;
    AccountRepository accountRepository;
    UserAccountRepository userAccountRepository;
    RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;


    public UserService(UserAccountRepository userAccountRepository, UserRepository repository, AccountRepository accountRepository, RoleRepository roleRepository) {
        this.repository = repository;
        this.userAccountRepository = userAccountRepository;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(16);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO>findAll() {
        List<User> users = this.repository.findAll();
        return users.stream().map(UserResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity save(UserRequest user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        if(user.getMail().contains("@")){
            if(!this.repository.existsByMail(user.getMail())){
                User newUser= new User(user);
                List<Role> roles = new ArrayList<>();
                for(String s: user.getRoles()){
                    Role r = this.roleRepository.findById(s).get();
                    if(r != null){
                        roles.add(r);
                    }
                }
                newUser.setRoles(roles);
                this.repository.save(newUser);
                return new ResponseEntity(newUser.getID(), HttpStatus.CREATED);
            }
            else {
                throw new ConflictExistException("User", "mail", user.getMail());
            }
        }
        else {
            throw new ConflictInvalidMail(user.getMail());
        }
    }

    @Transactional(readOnly = true)
    public UserLoginResponseDTO findByMailAndPassword(String mail, String pass) {
        User u = this.repository.findByMail(mail);
        return new UserLoginResponseDTO(u);
    }
    @Transactional
    public void deleteUser(Long id) {
        if(this.repository.existsById(id)){
            List<UserAccount> accounts = this.userAccountRepository.findAllById_User(id);
            if(accounts.isEmpty()){
                this.repository.deleteById(id);
            }
            else{
                throw new ReferencedRowException("User", "UserAccount", "Id", id);
            }
        }
        else
            throw new NotFoundException("User","Id",id);
    }
    @Transactional
    public ResponseEntity<Long> updateUser(UserRequest userRequest, Long id) {
        userRequest.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        if(this.repository.existsById(id)){
            if(!this.repository.existsByMail(userRequest.getMail())){
                User user = this.repository.findById(id).get();
                user.setName(userRequest.getName());
                user.setSurname(userRequest.getSurname());

                user.setMail(userRequest.getMail());
                user.setPassword(userRequest.getPassword());
                List<Role> roles = new ArrayList<>();
                for(String s: userRequest.getRoles()){
                    Role r = this.roleRepository.findById(s).get();
                    if(r != null){
                        roles.add(r);
                    }
                }
                user.setRoles(roles);
                return new ResponseEntity(user.getID(), HttpStatus.ACCEPTED);
            }
            else {
                throw new ConflictExistException("User", "mail", userRequest.getMail());
            }
        }
        else{
            throw new NotFoundException("User","Id",id);
        }
     }
    @Transactional(readOnly = true)
    public ResponseEntity<UserLoginResponseDTO> findByMail(String mail) {
        User u = this.repository.findByMail(mail);
        return new ResponseEntity(new UserLoginResponseDTO(u), HttpStatus.CREATED);
    }
    @Transactional
    public ResponseEntity<UserAccountResponseDTO> saveNewUserAccount(UserAccountRequestDTO userAccountRequest) {
        if(this.repository.existsById(userAccountRequest.getUserID())){
            User u = this.repository.findById(userAccountRequest.getUserID()).get();
            if(this.accountRepository.existsById(userAccountRequest.getAccountId())){
                Account a = this.accountRepository.findById(userAccountRequest.getAccountId()).get();
                UserAccountID userAccountID = new UserAccountID(u, a);
                if(!this.userAccountRepository.existsById(userAccountID)){
                    UserAccount userAccount = new UserAccount(userAccountID);
                    this.userAccountRepository.save(userAccount);
                    return new ResponseEntity(userAccount.getId(), HttpStatus.CREATED);
                }
                else {
                    throw new ConflictExistException("UserAccount", "Id", userAccountID);
                }
            }
            else{
                throw new NotFoundException("Account", "Id)", userAccountRequest.getAccountId());
            }
        }
        else{
            throw new NotFoundException("User", "Id", userAccountRequest.getUserID());
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<UserResponseDTO> findByID(Long id){
        if(this.repository.existsById(id)){
            User u = this.repository.findById(id).get();
            System.out.println(u);
            return new ResponseEntity(new UserResponseDTO(u), HttpStatus.OK);
        }
        else{
            throw new NotFoundException("User", "ID_User(Long)", id);
        }
    }

    @Transactional
    public ResponseEntity<String> disableUser(String mail) {
        User u = this.repository.findByMail(mail);
        if(u.getAvailable()==1){
            u.setAvailable(0);
            return new ResponseEntity(u.getMail(), HttpStatus.OK);
        }
        else{
            throw new ConflictWithStatusException("User", "user.available", u.getAvailable());
        }
    }

    @Transactional
    public ResponseEntity<String> enableUser(String mail) {
        User u = this.repository.findByMail(mail);
        if(u.getAvailable()==0){
            u.setAvailable(1);
            return new ResponseEntity(u.getMail(), HttpStatus.OK);
        }
        else{
            throw new ConflictWithStatusException("User", "user.available", u.getAvailable());
        }
    }

    public List<UserAccountResponseDTO> getUserAccountByUserId(Long id) {
        if(this.repository.existsById(id)){
            List<UserAccount> accounts = this.userAccountRepository.findAllById_User(id);
            if(!accounts.isEmpty()){
                return accounts.stream().map(a1->new UserAccountResponseDTO(a1)).collect(Collectors.toList());
            }
            else {
                throw new NotFoundException("UserAccount", "User_id in userAccount_id(userAccountId)", id);
            }
        }
        else {
            throw new NotFoundException("User", "User_id(Long)", id);
        }
    }

    public List<UserAccountResponseDTO> getAllUserAccount() {
        List<UserAccount> userAccounts = this.userAccountRepository.findAll();
        return userAccounts.stream().map(ua1-> new UserAccountResponseDTO(ua1)).collect(Collectors.toList());
    }

    public void deleteUserAccount(Long id, Long idAccount) {
        if(this.repository.existsById(id)){
            User u = this.repository.findById(id).get();
            if(this.accountRepository.existsById(idAccount)){
                Account a = this.accountRepository.findById(idAccount).get();
                UserAccountID userAccountID = new UserAccountID(u, a);
                this.userAccountRepository.deleteById(userAccountID);
            }
            else {
                throw new NotFoundException("Account", "Account.id", idAccount);
            }
        }
        else {
            throw new NotFoundException("User", "User.id", id);
        }
    }

    public ResponseEntity<UserResponseDTO> getUserByMail(String mail) {
        if(this.repository.existsByMail(mail)){
            User u = this.repository.findByMail(mail);
            return new ResponseEntity<>(new UserResponseDTO(u), HttpStatus.OK);
        }
        else {
            throw new NotFoundException("User", "mail", mail);
        }
    }

    @Transactional(readOnly = true)
    public UserLoginResponseDTO login(String mail) {
        User u = this.repository.findByMail(mail);
        if(u == null) throw new NotFoundException("User", "mail", mail);
        return new UserLoginResponseDTO(u);
    }
}
