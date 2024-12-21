package ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class Pp313SpringBootApplication {

    public static void main(String[] args) {

       SpringApplication.run(Pp313SpringBootApplication.class, args);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                    // Этот код мне нужен для проверки шифрования
//
//        String rawPasswordAdmin = "admin";  // пароль,который я проверя
//        String hashedPasswordAdmin = encoder.encode(rawPasswordAdmin);
//        System.out.println("Hashed password: " + hashedPasswordAdmin); // посмотреть,что получилось

                    // или так
        //String rawPassword = "user33";
//        String encodedPassword = encoder.encode(rawPassword);
//        System.out.println(encodedPassword);

//
//        String storedPassword = "$2a$10$whkI/my0NkUtu73dnPpYfOqaHB8JsnBwWglV/DiTtfLHnSgex2/iu";  // Подставьте зашифрованный пароль из базы данных

//                //     Проверка пароля
//        boolean passwordMatches = encoder.matches(rawPassword, storedPassword);
//        System.out.println("Пароль совпадает: " + passwordMatches);
   }

}
