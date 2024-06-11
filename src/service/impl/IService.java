package service.impl;

import model.Product;

import java.util.List;

public interface IService <T extends Product>{
    boolean add(T t);

    boolean addSale(Product product);
    List<T> getAllSale();
    void deleteProduct(T t);
    void updateProduct(T productID,T productName , T productPrice,T productQuantity);
    List<T> getAll();

    List<T> searchById(String id);

}
