package edu.phystech.lab2Spring;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorisationDataManager{
    public static void addUser(User user) {
        MapUserToPassword.getInstance().put(user.getName(), user);
    }

    public static User getUserByName(String name) {
        return MapUserToPassword.getInstance().get(name);
    }

    public static boolean passwordIsCorrect(User user) {
        return MapUserToPassword.getInstance().get(user.getName()).getPassword().equals(user.getPassword());
    }

    public static boolean userIsExist(User user) {
        return MapUserToPassword.getInstance().containsKey(user.getName());
    }
}
