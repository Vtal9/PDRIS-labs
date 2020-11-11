package edu.phystech.lab2Spring;

import java.util.HashMap;
import java.util.Map;

public class MapUserToPassword {
    private static Map<String, User> instance = null;
    private MapUserToPassword(){}
    public static Map<String, User> getInstance(){
        if(instance == null){
            instance = new HashMap<>();
        }
        return instance;
    }
}