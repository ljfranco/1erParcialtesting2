package org.testing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String password;
    private String confirmPassword;

    public User(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public User() {
    }

    public static User randomUser() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = User.class.getClassLoader().getResourceAsStream("usersData.json")) {
            List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
            return users.get(new Random().nextInt(users.size()));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
