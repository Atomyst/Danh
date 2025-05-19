public class Product {
    private String proId;
    private String proModel;
    private String proCategory;
    private String proName;
    private double proCurrentPrice;
    private double proRawPrice;
    private double proDiscount;
    private int proLikesCount;

    public Product(String proId, String proModel, String proCategory, String proName,
                   double proCurrentPrice, double proRawPrice, double proDiscount, int proLikesCount) {
        this.proId = proId;
        this.proModel = proModel;
        this.proCategory = proCategory;
        this.proName = proName;
        this.proCurrentPrice = proCurrentPrice;
        this.proRawPrice = proRawPrice;
        this.proDiscount = proDiscount;
        this.proLikesCount = proLikesCount;
    }


    public String getProId() { return proId; }
    public String getProModel() { return proModel; }
    public String getProCategory() { return proCategory; }
    public String getProName() { return proName; }
    public double getProCurrentPrice() { return proCurrentPrice; }
    public double getProRawPrice() { return proRawPrice; }
    public double getProDiscount() { return proDiscount; }
    public int getProLikesCount() { return proLikesCount; }

    @Override
    public String toString() {
        return String.format("Product: %s (%s) - %s | Price: $%.2f | Likes: %d",
                proId, proModel, proName, proCurrentPrice, proLikesCount);
    }
}
