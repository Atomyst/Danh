public class Admin extends User {
    public Admin(String userID, String userName, String userPassword, String userRegisterTime, String userRole) {
        super(userID, userName, userPassword, userRegisterTime, "admin");
    }
    public Admin(){
        super();
        this.userRole = "admin";
    }
    @Override
    public String toString() {
        return String.format("{\"userID\":\"%s\", \"userName\":\"%s\", \"userPassword\":\"%s\", \"userRegisterTime\":\"%s\", \"userRole\":\"%s\"}", 
            this.userID, this.userName, this.userPassword, this.userRegisterTime, this.userRole);
    }
}
