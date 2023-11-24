package com.ScootersApp.unitTests;

import com.ScootersApp.Service.DTOs.User.response.UserResponseDTO;
import com.ScootersApp.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    //private UserController controller;
    @Autowired
     public UserTest(UserController uc){
        // controller = uc;
    }

    @Test
    public void testGetAllUsers(){
        //List<UserResponseDTO> users = controller.getAllUsers();
        // Aseguramos que la lista no sea nula y tiene al menos un usuario
        //Assert.assertNotNull(users);
        //Assert.assertNotEquals(0, users.size());
    }

    @Test
    public void checkSyntaxMails(){
        //List<UserResponseDTO> users = controller.getAllUsers();
        //for (UserResponseDTO u: users) {
        //String currentMail = u.getMail();
            // Aseguramos que los mails de los usuarios contengan todos "@"
        // Assert.assertTrue(currentMail.contains("@"));
        // }
    }

    @Test
    public void checkPhoneNumbersDoesntContainsWrongCharacters(){
        //List<UserResponseDTO> users = controller.getAllUsers();
        //ArrayList<String> wrongCharacters = new ArrayList<>();
        // wrongCharacters.add("#");
        //wrongCharacters.add("!");
        //wrongCharacters.add("¿");
        //wrongCharacters.add("?");
        // wrongCharacters.add("¡");
        //for (UserResponseDTO u: users) {
        //   String currentNumber = u.getPhoneNumber();
        //  for (String character: wrongCharacters) {
                // Aseguramos que los numeros de telefono de los usuarios no contengan
                // caracteres invalidos
        // Assert.assertTrue(!currentNumber.contains(character));
        //  }
        // }
    }
}