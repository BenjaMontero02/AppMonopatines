package com.ScootersApp.Service.loadData;

import com.ScootersApp.domain.Account;
import com.ScootersApp.domain.User;
import com.ScootersApp.domain.UserAccount;
import com.ScootersApp.domain.UserAccountID;
import com.ScootersApp.repository.AccountRepository;
import com.ScootersApp.repository.UserAccountRepository;
import com.ScootersApp.repository.UserRepository;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.sql.Timestamp;


@Component
public class CSVReader {
    private UserAccountRepository userAccountRepository;
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private static final String userDir =
            System.getProperty("user.dir") + "/src/main/java/com/ScootersApp/Service/loadData/";

    public CSVReader(UserAccountRepository userAccountRepository, UserRepository userRepository, AccountRepository accountRepository) throws IOException, SQLException {
        this.userAccountRepository = userAccountRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public void load() throws SQLException, IOException {
        this.loadUser();
        this.loadAccount();
        this.loadUserAccount();
    }

    private void loadUser() throws IOException, SQLException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader(userDir + "User.csv"));
        for (CSVRecord row : parser) {
            String name = String.valueOf(row.get("name"));
            String surname = String.valueOf(row.get("sur_name"));
            String mail = String.valueOf(row.get("mail"));
            String password = String.valueOf(row.get("password"));
            String phoneNumber = String.valueOf(row.get("phone_number"));
            String role = String.valueOf(row.get("role"));

            User user = new User(name,surname,mail,password,phoneNumber,role);
            userRepository.save(user);
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
