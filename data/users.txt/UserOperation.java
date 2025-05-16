import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
public class UserOperation {
    private static UserOperation instance;
    private Map<String, User> userDatabase;

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z_]{5,}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d].{5,}$");
    
    private UserOperation() {
        userDatabase = new HashMap<>();
    }
    public static UserOperation getInstance() {
        if (instance == null) {
            instance = new UserOperation();
        }
        return instance;
    }
    public String generateUniqueUserID() {
        Random random = new Random();
        return "u_" + (random.nextInt(100000000, 999999999));
    }
    public String encryptPassword(String userPassword) {
        
    }
    public String decryptPassword(String encryptedPassword) {
       
    }
    public boolean checkUsernameExist(String userName) {
        return userDatabase.containsKey(userName);
    }
    public  boolean validateUsername(String userName) {
        return USERNAME_PATTERN.matcher(userName).matches();
    }
    public boolean validatePassword(String userPassword) {
        return PASSWORD_PATTERN.matcher(userPassword).matches();
    }
    public User login(String userName, String userPassword){
        User user = userDatabase.get(userName);
        if(user != null && user.getUserPassword().equals(encryptPassword(userPassword))){
            return user;
        }
        return null;
    }
}
