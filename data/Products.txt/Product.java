public class Product {
    private String proID;
    private String proModel;
    private String proCategory;
    private String proName;
    private double proCurrentPrice;
    private String proRawPrice;
    private double proDiscount;
    private int proLikesCOunt;

    public Product(String proID, String proModel, String proCategory, String proName, double proCurrentPrice, String proRawPrice, double proDiscount, int proLikesCOunt) {
        this.proID = proID;
        this.proModel = proModel;
        this.proCategory = proCategory;
        this.proName = proName;
        this.proCurrentPrice = proCurrentPrice;
        this.proRawPrice = proRawPrice;
        this.proDiscount = proDiscount;
        this.proLikesCOunt = proLikesCOunt;
    }
    public Product() {
        this.proID = "defaultID";
        this.proModel = "Default Model";
        this.proCategory = "Default Category";
        this.proName = "Default Name";
        this.proCurrentPrice = 0.0;
        this.proRawPrice = "0.0";
        this.proDiscount = 0.0;
        this.proLikesCOunt = 0;
    }
    @Override
    public String toString() {
        return String.format("{\"proID\":\"%s\", \"proModel\":\"%s\", \"proCategory\":\"%s\", \"proName\":\"%s\", \"proCurrentPrice\":%.2f, \"proRawPrice\":\"%s\", \"proDiscount\":%.2f, \"proLikesCOunt\":%d}", 
            this.proID, this.proModel, this.proCategory, this.proName, this.proCurrentPrice, this.proRawPrice, this.proDiscount, this.proLikesCOunt);
    }
}
