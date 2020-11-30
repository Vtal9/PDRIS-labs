package authorisation;

import java.util.HashMap;
import java.util.Map;

public class MapUserToPassword {
    private static Map<String, String> instance = null;
    private MapUserToPassword(){}
    public static Map<String, String> getInstance(){
        if(instance == null){
            instance = new HashMap<>();
        }
        return instance;
    }
}
