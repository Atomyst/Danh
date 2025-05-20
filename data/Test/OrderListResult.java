package data.Test;
import java.util.List;

import Order;

public class OrderListResult {
    private List<Order> orders;
    private int currentPage;
    private int totalPages;

    public OrderListResult(List<Order> orders, int currentPage, int totalPages) {
        this.orders = orders;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public String toString() {
        return String.format("Page %d of %d - Orders: %s", currentPage, totalPages, orders);
    }
}

