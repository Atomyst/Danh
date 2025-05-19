public class Customer extends User {
    protected String userEmail;
    protected String userMobile;
    public Customer(String userID, String userName, String userPassword, String userRegisterTime, String userRole, String userEmail, String userMobile) {
        super(userID, userName, userPassword, userRegisterTime, "customer");
        this.userEmail = userEmail;
        this.userMobile = userMobile;
}

    public Customer() {
        super();
        this.userRole = "customer";
        this.userEmail = "default@gmail.com";
        this.userMobile = "0000000000";
    }
    public String getUserEmail() {
        return userEmail;
    }
    public String getUserMobile() {
        return userMobile;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    @Override
    public String toString() {
        return String.format("{\"userID\":\"%s\", \"userName\":\"%s\", \"userPassword\":\"%s\", \"userRegisterTime\":\"%s\", \"userRole\":\"%s\", \"userEmail\":\"%s\", \"userMobile\":\"%s\"}", 
            this.userID, this.userName, this.userPassword, this.userRegisterTime, this.userRole, this.userEmail, this.userMobile);
    }
}
