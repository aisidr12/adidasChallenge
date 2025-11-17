package com.adidas.challenge.email.email.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
class EmailServiceTest {

    @Test
    void showmessage() {
        int[] datos = new int[50];
        for (int i = 0; i < datos[i]; i++) {
            datos[i] = (int) ((Math.random() * 100 )+ 1);
            System.out.println(datos[i]);

        }
        for (int i = 0; i < datos[i]; i++) {
            System.out.println("Los datos son :"+ i);
        }

        Assertions.assertTrue(true);

    }

}