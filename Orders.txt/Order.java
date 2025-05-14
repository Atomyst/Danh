import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
public class Order {
    private String orderID;
    private String userID;
    private String proID;
    private String orderTime;

    private static final Pattern ORDER_ID_PATTERN = Pattern.compile("o_\\d{5}");
    public Order(String orderID, String userID, String proID, String orderTime) {
        if (!ORDER_ID_PATTERN.matcher(orderID).matches()) {
            throw new IllegalArgumentException("Invalid orderID(5 digits)");
        }
        this.orderID = orderID;
        this.userID = userID;
        this.proID = proID;
        this.orderTime = orderTime;
    }
    public Order() {
        this.orderID = generateUniqueOrderID();
        this.userID = "defaultUserID";
        this.proID = "defaultProID";
        this.orderTime = getCurrentFormattedTime(); 
    }
    private String generateUniqueOrderID() {
        return"o_" + (int)(Math.random() *4000 + 2000);
    }
        private String getCurrentFormattedTime() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
            return LocalDateTime.now().format(formatter);
        
    }
    @Override
    public String toString() {
        return String.format("{\"orderID\":\"%s\", \"userID\":\"%s\", \"proID\":\"%s\", \"orderTime\":\"%s\"}", 
            this.orderID, this.userID, this.proID, this.orderTime);
    }
}
