package com.ScootersApp.Service.loadData;

import com.ScootersApp.Service.DTOs.User.request.UserRequest;
import com.ScootersApp.Service.UserService;
import com.ScootersApp.domain.*;
import com.ScootersApp.repository.AccountRepository;
import com.ScootersApp.repository.RoleRepository;
import com.ScootersApp.repository.UserAccountRepository;
import com.ScootersApp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;


@Component
public class CSVReader {
    private UserAccountRepository userAccountRepository;
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    private static final String userDir =
            System.getProperty("user.dir") + "/src/main/java/com/ScootersApp/Service/loadData/";

    public CSVReader(RoleRepository roleRepository,UserAccountRepository userAccountRepository, UserRepository userRepository, AccountRepository accountRepository, UserService userService) throws IOException, SQLException {
        this.userAccountRepository = userAccountRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder(16);
    }

    public void load() throws SQLException, IOException {
        this.loadRoles();
        this.loadUser();
        this.loadAccount();
        this.loadUserAccount();
    }

    private void loadRoles() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "Roles.csv"));
        for (CSVRecord row : parser) {
            String type = String.valueOf(row.get("type"));

            Role role = new Role(type);
            roleRepository.save(role);
        }
    }

    private void loadUser() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "User.csv"));

        CSVParser parserRol = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "Roles.csv"));

        ArrayList<String> roles = new ArrayList<>();

        for (CSVRecord row : parserRol) {
            String type = String.valueOf(row.get("type"));
            roles.add(type);
        }

        for (CSVRecord row : parser) {
            String name = String.valueOf(row.get("name"));
            String surname = String.valueOf(row.get("sur_name"));
            String mail = String.valueOf(row.get("mail"));
            String password = String.valueOf(row.get("password"));
            String phoneNumber = String.valueOf(row.get("phone_number"));

            Random random = new Random();
            int i = random.nextInt(0,roles.size()-1);
            ArrayList<String> userRoles = new ArrayList<>();

            while (i>=0){
                    userRoles.add(roles.get(i));
                    i--;
            }

            UserRequest ur = new UserRequest(name,surname,mail,password,phoneNumber,userRoles);
            userService.save(ur);
        }
    }

    private void loadAccount() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "Account.csv"));
        for (CSVRecord row : parser) {
            Long id = Long.valueOf(row.get("id"));
            Double wallet = Double.valueOf(row.get("wallet"));
            Timestamp dateUp = Timestamp.valueOf(row.get("dateUp"));

            Account account = new Account(id, wallet, dateUp);
            accountRepository.save(account);
        }
    }

    private void loadUserAccount() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "UserAccount.csv"));
        for (CSVRecord row : parser) {
            Long userId = Long.valueOf(row.get("user_id"));
            Long accountId = Long.valueOf(row.get("account_id"));

            User u = userRepository.findById(userId).get();
            Account a = accountRepository.findById(accountId).get();

            UserAccountID userAccountID = new UserAccountID(u,a);
            UserAccount UserAccount = new UserAccount(userAccountID);
            userAccountRepository.save(UserAccount);
        }
    }
}
