package edu.phystech.lab2Spring;

import java.util.HashMap;
import java.util.Map;

public class AuditDataManager {
    private Map<String, String> mapUserToAttempts = new HashMap<>();
    public void addUserAttempt(String user, String attempt){
        mapUserToAttempts.put(user, attempt);
    }

    public Map<String, String> getAudit() {
        return mapUserToAttempts;
    }
}
