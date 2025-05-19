import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomerOperation {
    private static CustomerOperation instance;
    private final List<Customer> customerDatabase = new ArrayList<>();
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^(04|03)\\d{8}$");

    private CustomerOperation() {}

    public static CustomerOperation getInstance() {
        if (instance == null) {
            instance = new CustomerOperation();
        }
        return instance;
    }

    public boolean validateEmail(String userEmail) {
        return EMAIL_PATTERN.matcher(userEmail).matches();
    }

    public boolean validateMobile(String userMobile) {
        return MOBILE_PATTERN.matcher(userMobile).matches();
    }
    public boolean registerCustomer(String userName, String userPassword, String userEmail, String userMobile) {

        if (!UserOperation.getInstance().validateUsername(userName) || 
            !UserOperation.getInstance().validatePassword(userPassword) || 
            !validateEmail(userEmail) || !validateMobile(userMobile)) {
            return false;
        }

        if (UserOperation.getInstance().checkUsernameExist(userName)) return false;

        String userId = UserOperation.getInstance().generateUniqueUserId();
        Customer newCustomer = new Customer(userId, userName, userPassword, getCurrentFormattedTime(), "customer", userEmail, userMobile);
        customerDatabase.add(newCustomer);
        return true;
    }

    public boolean updateProfile(String attributeName, String value, Customer customerObject) {
        return switch (attributeName.toLowerCase()) {
            case "email" -> {
                if (!validateEmail(value)) yield false;
                customerObject.setUserEmail(value);
                yield true;
            }
            case "mobile" -> {
                if (!validateMobile(value)) yield false;
                customerObject.setUserMobile(value);
                yield true;
            }
            default -> false;
        };
    }

    public boolean deleteCustomer(String UserID) {
        return customerDatabase.removeIf(c -> c.getUserID().equals(UserID));
    }

    public CustomerListResult getCustomerList(int pageNumber) {
        int pageSize = 10;
        int totalPages = (int) Math.ceil((double) customerDatabase.size() / pageSize);
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, customerDatabase.size());

        List<Customer> pageData = customerDatabase.subList(startIndex, endIndex);
        return new CustomerListResult(pageData, pageNumber, totalPages);
    }

    public void deleteAllCustomers() {
        customerDatabase.clear();
    }

    private String getCurrentFormattedTime() {
        return java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"));
    }
public class CustomerListResult {
    private final List<Customer> customerList;
    private final int currentPage;
    private final int totalPages;

    public CustomerListResult(List<Customer> customerList, int currentPage, int totalPages) {
        this.customerList = customerList;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<Customer> getCustomerList() { return customerList; }
    public int getCurrentPage() { return currentPage; }
    public int getTotalPages() { return totalPages; }
}
}