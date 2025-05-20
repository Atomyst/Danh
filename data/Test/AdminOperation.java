import UserOperation;

package data.Test;
public class AdminOperation {
    private static AdminOperation instance;
    private boolean isAdminRegistered = false;

    private AdminOperation() {}

    public static AdminOperation getInstance() {
        if (instance == null) {
            instance = new AdminOperation();
        }
        return instance;
    }

    public void registerAdmin() {
        if (isAdminRegistered) {
            System.out.println("Admin account already exists.");
            return;
        }
        
        String adminId = UserOperation.getInstance().generateUniqueUserId();
        String adminName = "System Admin";
        String adminPassword = UserOperation.getInstance().encryptPassword("secureAdminPassword");
        String registerTime = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"));
        
        Admin admin = new Admin(adminId, adminName, adminPassword, registerTime, "admin");
        isAdminRegistered = true;
        
        System.out.println("Admin registered successfully: " + admin);
    }
}
