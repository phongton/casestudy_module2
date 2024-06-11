package controller;

import model.Product;
import repository.Repo;
import service.impl.ManagerService;
import view.ManagerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {


    public static void main(String[] args) {
        ManagerView managerView = new ManagerView();
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean check;
        List<Product> products;
        ManagerService service1 = new ManagerService();
        Repo repo = new Repo();
        String id;

        while (true) {
            choice = managerView.viewManager();
            switch (choice) {
                case 1:
                    products = managerView.viewAdd();
                   check= service1.check1((Product) products);
                    managerView.messageCheck(check);

                    break;
                case 2:
                  id=managerView.viewDelete();
                   if (id==null){
                       System.err.println("ID này không có trong kho");
                   }else {
                       products = service1.searchById(id);
                       repo.DeleteProduct((Product) products);
                   }
                   break;
                case 3:
                    products = service1.getAll();
                    managerView.viewProduct(products);
                    break;
                case 4:
                    products = managerView.viewUpdateProduct();
                    check=service1.add((Product) products);
                    managerView.messageCheck(check);
                    break;
                case 5:
                    products=service1.getAllSale();
                    managerView.viewSaleProduct(products);
                    break;
                case 6:
                    return;
            }

        }
    }
}
