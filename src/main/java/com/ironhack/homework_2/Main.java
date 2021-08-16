package com.ironhack.homework_2;

import com.ironhack.homework_2.main.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Menu.mainMenu();
    }

}
