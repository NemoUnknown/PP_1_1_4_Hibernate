package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Петр", "Петров", (byte) 18);
        userService.saveUser("Иван", "Иванов", (byte) 64);
        userService.saveUser("Семен", "Семенов", (byte) 33);
        userService.saveUser("Анна", "Петрова", (byte) 8);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
