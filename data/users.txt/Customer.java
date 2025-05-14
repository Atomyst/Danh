public class Customer extends User {
    private String userEmail;
    private String userMobile;
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
    @Override
    public String toString() {
        return String.format("{\"userID\":\"%s\", \"userName\":\"%s\", \"userPassword\":\"%s\", \"userRegisterTime\":\"%s\", \"userRole\":\"%s\", \"userEmail\":\"%s\", \"userMobile\":\"%s\"}", 
            this.userID, this.userName, this.userPassword, this.userRegisterTime, this.userRole, this.userEmail, this.userMobile);
    }
}
