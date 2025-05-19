import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
public abstract class User{
    protected String userID;
    protected String userName; 
    protected String userPassword;
    protected String userRegisterTime;
    protected String userRole;

    private static final Pattern USER_ID_PATTERN = Pattern.compile("u_\\d{10}");
    public User(String userID, String userName, String userPassword, String userRegisterTime, String userRole){
        if(!USER_ID_PATTERN.matcher(userID).matches()){
            throw new IllegalArgumentException("Invalid userID");
        }
        this.userID = userID;
        this.userName = userName;
        this.userPassword = encryptPassword(userPassword);
        this.userRegisterTime = userRegisterTime;
        this.userRole = (userRole.equalsIgnoreCase("admin")) ? "admin" : "customer";
    }

    public User() {
        this.userID = generateUniqueUserID();
        this.userName = "Default User";
        this.userPassword = encryptPassword("defaultPassword");
        this.userRegisterTime = getCurrentFormattedTime();
        this.userRole = "customer";
    }
    private String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }
    private String generateUniqueUserID(){
        long time = System.currentTimeMillis() % 10000000000L;
        return String.format("u_%10d", time);
    }
    private String getCurrentFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:ss");
        return LocalDateTime.now().format(formatter);      
    }
    public String getUserID() {
        return userID;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public String getUserRegisterTime() {
        return userRegisterTime;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = encryptPassword(userPassword);
    }
    
    @Override 
    public String toString() {
        return String.format("{\"userID\":\"%s\", \"userName\":\"%s\", \"userPassword\":\"%s\", \"userRegisterTime\":\"%s\", \"userRole\":\"%s\"}", 
            this.userID, this.userName, this.userPassword, this.userRegisterTime, this.userRole);
    }
    
}