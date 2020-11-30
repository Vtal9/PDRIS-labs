package edu.phystech.lab2Spring;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorisationDataManager{
    private final static Map<String, User> users = MapUserToPassword.getInstance();

    public static void addUser(User user) {
        users.put(user.getName(), user);
    }

    public static User getUserByName(String name) {
        return users.get(name);
    }

    public static boolean passwordIsCorrect(User user) {
        return users.get(user.getName()).getPassword().equals(user.getPassword());
    }

    public static boolean userIsExist(User user) {
        return users.containsKey(user.getName());
    }
}
