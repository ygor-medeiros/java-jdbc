package com.ygor.javajdbc.repository;

import com.ygor.javajdbc.model.User;
import com.ygor.javajdbc.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserRepositoryTest {

    private UserRepository userRepository = new UserRepository();

    @Test
    public void getById() {
        int id = 1;

        User user = userRepository.getById(id);

        assertEquals(id, user.getId());

        printUser(user);

    }

    @Test
    public void getAll() {
        List<User> users = this.userRepository.getAll();

        assertNotNull(users);

        for(User user : users) {
            printUser(user);
            System.out.println("-------------------------------------");
        }
    }

    @Test
    public void create() {
        User user = new User("Ygor", "ygoralcantara@gmail.com", "(83) 98888-7777");

        User savedUser = userRepository.create(user);
        User newUser = userRepository.getById(savedUser.getId());

        assertNotNull(newUser);
        assertEquals("Ygor", newUser.getName());
        assertEquals("ygoralcantara@gmail.com", newUser.getEmail());
        assertEquals("(83) 98888-7777", newUser.getCellphone());

        printUser(savedUser);

    }

    @Test
    public void update() {
        int id = 1;

        User updateUser = userRepository.getById(id);

        updateUser.setName("Ygor");
        updateUser.setEmail("ygor@gmail.com");

        userRepository.update(id, updateUser);

        User newUser = userRepository.getById(id);

        assertNotNull(newUser);
        assertEquals(updateUser.getName(), newUser.getName());
        assertEquals(updateUser.getEmail(), newUser.getEmail());

        printUser(newUser);
    }

    @Test
    public void remove() {
        int id = 5;

        userRepository.remove(id);

        User user = userRepository.getById(id);

        assertNull(user);
    }

    private void printUser(User user) {
        System.out.println("ID: " + user.getId());
        System.out.println("NAME: " + user.getName());
        System.out.println("EMAIL: " + user.getEmail());
        System.out.println("CELLPHONE: " + user.getCellphone());
    }
}
