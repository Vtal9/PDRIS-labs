package Authorisation;

import java.util.HashMap;
import java.util.Map;

public class AutorisationData {
    private Map<String, String> mapUserToPassword = null;
    private AutorisationData(){
        mapUserToPassword = new HashMap<>();
    }
    public Map<String, String> getInstance(){
        if(mapUserToPassword == null){
            mapUserToPassword = new HashMap<>();
        }
        return mapUserToPassword;
    }
}
