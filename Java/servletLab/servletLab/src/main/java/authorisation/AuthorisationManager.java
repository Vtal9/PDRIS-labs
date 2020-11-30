package authorisation;

import java.util.Map;

public class AuthorisationManager {
    private static final Map<String, String> users = MapUserToPassword.getInstance();

    public static boolean userIsExist(String user){
        return users.containsKey(user);
    }

    public static void createUser(String user, String password){
        users.put(user, password);
    }

    public static boolean passwordIsCorrect(String user, String password){
        return users.get(user).equals(password);
    }
}
