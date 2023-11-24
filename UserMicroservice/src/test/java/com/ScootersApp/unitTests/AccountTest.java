package com.ScootersApp.unitTests;


import com.ScootersApp.Service.DTOs.User.response.UserResponseDTO;
import com.ScootersApp.Service.DTOs.account.response.AccountResponseDTO;
import com.ScootersApp.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountTest {
    //AccountController accountController;

    //@Autowired
    public AccountTest(AccountController ac){
        //accountController = ac;
    }

    @Test
    public void testGetAllAccounts(){
        // List<AccountResponseDTO> accounts = accountController.getAllAccounts();
        // Aseguramos que la lista no sea nula y tiene al menos un usuario
        //Assert.assertNotNull(accounts);
        //Assert.assertNotEquals(0, accounts.size());
    }



    @Test
    public void checkCorrectWalletAmount(){
        // List<AccountResponseDTO> accounts = accountController.getAllAccounts();
        // for (AccountResponseDTO account : accounts) {
        //       Double expected = 0.0;
        //     Double obtained = account.getWallet();
                //Aseguramos que todas las billeteras tengan valores positivos
        //      Assert.assertTrue(obtained>=expected);
        // }
    }
}
