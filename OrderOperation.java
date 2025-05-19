import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrderOperation {
    private static OrderOperation instance;
    private static final String ORDER_FILE_PATH = "data/orders.txt";
    private static final String ORDER_ID_PREFIX = "o_";
    private static final Pattern ORDER_ID_PATTERN = Pattern.compile("o_\\d{5}");

    private OrderOperation() {}

    public static synchronized OrderOperation getInstance() {
        if (instance == null) {
            instance = new OrderOperation();
        }
        return instance;
    }
    public void generateTestOrderData() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE_PATH, true))) {
        for (int i = 1; i <= 10; i++) {
            String customerId = "CUST_" + i;
            int numOrders = (int) (Math.random() * 150) + 50; // Between 50-200 orders
            
            for (int j = 0; j < numOrders; j++) {
                String orderId = generateUniqueOrderId();
                String productId = "PROD_" + (int) (Math.random() * 100); // Random product ID
                String orderTime = generateRandomOrderTime();

                String orderEntry = orderId + "," + customerId + "," + productId + "," + orderTime;
                writer.write(orderEntry);
                writer.newLine();
            }
        }
        System.out.println("Test order data generated successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public void generateSingleCustomerConsumptionFigure(String customerId) {
    Map<String, Double> monthlyConsumption = new TreeMap<>(); // Sorted by month

    try {
        List<String> orders = Files.readAllLines(Paths.get(ORDER_FILE_PATH));

        for (String order : orders) {
            String[] data = order.split(",");
            if (data[1].equals(customerId)) {
                String month = data[3].substring(3, 10); // Extract "MM-yyyy"
                double price = Math.random() * 100 + 10; // Simulated order price
                monthlyConsumption.put(month, monthlyConsumption.getOrDefault(month, 0.0) + price);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }

   
    String filePath = "output/" + customerId + "_spending.csv";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        writer.write("Month,Total Spending ($)\n");
        for (Map.Entry<String, Double> entry : monthlyConsumption.entrySet()) {
            writer.write(entry.getKey() + "," + String.format("%.2f", entry.getValue()) + "\n");
        }
        System.out.println("Spending data saved successfully to " + filePath);
    } catch (IOException e) {
        e.printStackTrace();
    }
}



    private String generateRandomOrderTime() {
        LocalDateTime randomDate = LocalDateTime.now().minusDays((int) (Math.random() * 365));
        return randomDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"));
}
    public String generateUniqueOrderId() {
        Set<String> existingIds = getAllOrderIds();
        String newId;
        do {
            newId = ORDER_ID_PREFIX + (int) (Math.random() * 90000 + 10000);
        } while (existingIds.contains(newId));
        return newId;
    }
    public boolean createAnOrder(String customerId, String productId, String createTime) {
        if (customerId == null || productId == null) {
            throw new IllegalArgumentException("Customer ID and Product ID cannot be null.");
        }

        String orderId = generateUniqueOrderId();
        if (createTime == null) {
            createTime = getCurrentFormattedTime();
        }

        Order newOrder = new Order(orderId, customerId, productId, createTime);
        return saveOrderToFile(newOrder);
    }

    private boolean saveOrderToFile(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE_PATH, true))) {
            writer.write(order.toString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOrder(String orderId) {
        if (!ORDER_ID_PATTERN.matcher(orderId).matches()) {
            throw new IllegalArgumentException("Invalid order ID format.");
        }

        try {
            List<String> orders = Files.readAllLines(Paths.get(ORDER_FILE_PATH));
            List<String> filteredOrders = orders.stream()
                    .filter(order -> !order.contains("\"orderID\":\"" + orderId + "\""))
                    .collect(Collectors.toList());

            Files.write(Paths.get(ORDER_FILE_PATH), filteredOrders);
            return orders.size() != filteredOrders.size();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public OrderListResult getOrderList(String customerId, int pageNumber) {
        if (customerId == null || pageNumber < 1) {
            throw new IllegalArgumentException("Invalid customer ID or page number.");
        }

        try {
            List<Order> orders = Files.lines(Paths.get(ORDER_FILE_PATH))
                    .map(line -> line.split(","))
                    .filter(data -> data.length >= 4 && data[1].equals(customerId))
                    .map(data -> new Order(data[0], data[1], data[2], data[3]))
                    .collect(Collectors.toList());

            int totalPages = (int) Math.ceil((double) orders.size() / 10);
            int startIndex = (pageNumber - 1) * 10;
            List<Order> paginatedOrders = orders.subList(
                    Math.min(startIndex, orders.size()), 
                    Math.min(startIndex + 10, orders.size())
            );

            return new OrderListResult(paginatedOrders, pageNumber, totalPages);
        } catch (IOException e) {
            e.printStackTrace();
            return new OrderListResult(Collections.emptyList(), pageNumber, 0);
        }
    }

    private String getCurrentFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    private Set<String> getAllOrderIds() {
        try {
            return Files.lines(Paths.get(ORDER_FILE_PATH))
                    .map(line -> line.split(",")[0])
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            return new HashSet<>();
        }
    }

    public void deleteAllOrders() {
        try {
            Files.write(Paths.get(ORDER_FILE_PATH), new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}