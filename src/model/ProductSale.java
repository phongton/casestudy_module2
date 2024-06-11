package model;

public class ProductSale extends Product {
    private String promotion;
    public ProductSale(String id, String name, String price, String quantity,String promotion) {
        super(id, name, price, quantity);
        this.promotion = promotion;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return super.toString()+" Promotion : "+ getPromotion()+"%";
    }
}
