package view;

import common.ExceptionHandler;
import common.RegexHandler;
import model.Product;
import model.ProductNormal;
import model.ProductSale;
import repository.Repo;
import service.impl.ManagerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerView {
    List<Product> productList = new ArrayList<>();
    Repo repo = new Repo();
    ManagerService mNService = new ManagerService();
    private Scanner sc = new Scanner(System.in);
    ProductNormal product;
    int select;
    String name;
    String id;
    String price;
    String quantity;

    public int viewManager() {
        System.out.println(" ================ MENU MANAGER ================");
        System.out.println("||> 1. Thêm mới sản phẩm                       ||");
        System.out.println("||> 2. Xóa sản phẩm                            ||");
        System.out.println("||> 3. Xem danh sách sản phẩm                  ||");
        System.out.println("||> 4. Sửa sản phẩm                            ||");
        System.out.println("||> 5. Xem sản phẩm đang khuyến mãi            ||");
        System.out.println("||> 6. Thoát                                   ||");
        System.out.println("=================================================");
        int choice ;
        System.out.println("Nhập lựa chọn của bạn : ");
        choice = ExceptionHandler.checkParseInteger();
        return choice;
    }

    public Product viewAdd() {

        try {
            System.out.println("Nhập id sản phẩm : ");
            id = RegexHandler.checkRegexId();
            System.out.println("Nhập tên sản phẩm ");
            name = RegexHandler.checkRegexName();
            System.out.println("Nhập giá sản phẩm :");
            price = RegexHandler.checkRegexPrice();
            System.out.println("Nhập số lượng tồn kho :");
            quantity = RegexHandler.checkRegexPrice();
            Product products = getProduct(id, name, price, quantity);
            if (products != null) return products;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Product getProduct(String id, String name, String price, String quantity) {
        System.out.println("Sản phẩm có được khuyến mãi không(1- Không ,2- Có):");
         select = ExceptionHandler.checkParseInteger();
        if (select==1){
            ProductNormal products = new ProductNormal(id, name, price, quantity);
            repo.add(products);
            return products;
        } else if (select==2) {
            System.out.println("Nhập giá khuyến mãi : ");
            String promotion =RegexHandler.checkRegexPrice();
            ProductSale productSale = new ProductSale(id, name, price, quantity,promotion);
            repo.addSale(productSale);
            return productSale;
        }
        return null;
    }
    public void messageCheck(boolean check) {
        if (check) {
            System.out.println("Tác vụ thành công ");
        } else {
            System.out.println("Tác vụ thất bại ");
        }
    }
    public void viewProduct(List<Product> products){
        System.out.println("Danh sách sản phẩm hiện có là :");
        for (Product product1 : products){
            System.out.println(product1.toString());
        }
    }
    public void viewSaleProduct(List<Product> products){
        System.out.println("Danh sách sản phẩm khuyến mãi là :");
        for (Product product1 : products){
            System.out.println(product1.toString());
        }
    }
    public Product viewUpdateProduct(){
        System.out.println("Nhập vào ID của đơn hàng bạn muốn sửa : ");
        String id = RegexHandler.checkRegexPrice();
            if (!repo.isIdExists(id)){
                System.out.println("ID này không tồn tại trong danh sách mới bạn nhập lại :");
            }else {
                System.out.println("Nhâp tên sản phẩm mới : ");
                String name = RegexHandler.checkRegexName();
                System.out.println("Nhập giá của sản phẩm mới : ");
                String price = RegexHandler.checkRegexPrice();
                System.out.println("Nhập số lượng tồn kho mới");
                String quantity = RegexHandler.checkRegexPrice();
                repo.updateProduct(id,name,price,quantity);
                if (repo.isIdExitsSale(id)){
                    System.out.println("Nhập giá khuyến mãi mới : ");
                    String promotion = sc.nextLine();
                    repo.updateProductSale(id,name,price,quantity,promotion);
                }
            }
            return null;
    }
    public String viewDelete(){
        System.out.println("Nhập id sản phẩm bạn muốn xoá khỏi danh sách : ");
        String id = RegexHandler.checkRegexPrice();
        if (!repo.isIdExists(id)) {
            return null;
        }
            return id;
    }

}
