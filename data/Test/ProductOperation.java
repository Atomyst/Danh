package data.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductOperation {
    private static ProductOperation instance;
    private final List<Product> productDatabase = new ArrayList<>();

    private ProductOperation() {}
    public static ProductOperation getInstance() {
        if (instance == null) {
            instance = new ProductOperation();
        }
        return instance;
    }

    public void extractProductsFromFiles() {
        System.out.println("Extracting product data from files...");
    
    }

    public ProductListResult getProductList(int pageNumber) {
        int pageSize = 10;
        int totalPages = (int) Math.ceil((double) productDatabase.size() / pageSize);
        int startIndex = (pageNumber - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, productDatabase.size());

        List<Product> pageData = productDatabase.subList(startIndex, endIndex);
        return new ProductListResult(pageData, pageNumber, totalPages);
    }

    public boolean deleteProduct(String proID) {
        return productDatabase.removeIf(p -> p.getProID().equals(proID));
    }
    public List<Product> getProductListByKeyword(String keyword) {
        return productDatabase.stream()
                .filter(p -> p.getProName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Product getProductById(String proID) {
        return productDatabase.stream()
                .filter(p -> p.getProId().equals(proID))
                .findFirst().orElse(null);
    }

    public void generateCategoryFigure() {
        System.out.println("Generating category figure...");
      
    }

    public void generateDiscountFigure() {
        System.out.println("Generating discount figure...");

    }

    public void generateLikesCountFigure() {
        System.out.println("Generating likes count figure...");
 
    }

    public void generateDiscountLikesCountFigure() {
        System.out.println("Generating discount vs likes count figure...");
    }
    public void deleteAllProducts() {
        productDatabase.clear();
        System.out.println("All products deleted.");
    }
}

