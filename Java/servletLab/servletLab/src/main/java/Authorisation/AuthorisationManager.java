package Authorisation;

public class AuthorisationManager {
    public static boolean userIsExist(String user){
        return MapUserToPassword.getInstance().containsKey(user);
    }

    public static void createUser(String user, String password){
        MapUserToPassword.getInstance().put(user, password);
    }

    public static boolean passwordIsCorrect(String user, String password){
        return MapUserToPassword.getInstance().get(user).equals(password);
    }
}
