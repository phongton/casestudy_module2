package service.impl;

import model.Product;

import java.util.List;

public interface IManagerService extends IService<Product>{
    @Override
    boolean add(Product product);

    @Override
    void deleteProduct(Product manager);

    @Override
    void updateProduct(Product productID, Product productName, Product productPrice, Product productQuantity);

    @Override
    List<Product> getAll();

    @Override
    List<Product> searchById(String id);
}
