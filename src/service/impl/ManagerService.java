package service.impl;

import model.Product;
import repository.Repo;

import java.util.ArrayList;
import java.util.List;

public class ManagerService implements IManagerService{
    Repo repo = new Repo();

    @Override
    public boolean add(Product product) {
        repo.add(product);
        return true;
    }
    @Override
    public boolean addSale(Product product) {
        repo.addSale(product);
        return true;
    }

    @Override
    public List<Product> getAllSale() {
        return repo.getSaleAll();
    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void updateProduct(Product productID, Product productName, Product productPrice, Product productQuantity) {
//        repo.updateProduct(productID,productName,productPrice,productQuantity);
    }
    public void updateProduct1(String productID, String productName, String productPrice, String productQuantity) {
        repo.updateProduct(productID,productName,productPrice,productQuantity);

    }


    @Override
    public List<Product> getAll() {
        List<Product> all = new ArrayList<>();
        all.addAll(repo.getAll());
        all.addAll(repo.getSaleAll());
        return all;
    }

    @Override
    public List<Product> searchById(String id) {
        List<Product> products = getAll();
        for (Product product: products){
            if (product.getId().equals(id)){
                return product;

            }
        }
        return null;
    }
    public boolean check1(Product product){
        if (product==null){
            return false;
        }
        return true;
    }

}
