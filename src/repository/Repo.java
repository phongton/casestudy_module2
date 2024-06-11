package repository;

import model.Product;
import model.ProductNormal;
import model.ProductSale;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repo {
    private static final String URL_PRODUCTSALE = "D:\\benh_an\\casetudy_module_2\\src\\repository\\data\\productsale.csv";
    private static final String URL_PRODUCT = "D:\\benh_an\\casetudy_module_2\\src\\repository\\data\\product.csv";
    List<Product> productList = new ArrayList<>();
    ProductNormal productNormal;

    public void add(Product product) {
        productList.add(product);
        writeFile2(productList, true);
    }
    public void addSale(Product product) {
        productList.add(product);
        writeFileSale(product, true);
    }

    private void writeFile2(List<Product> product, boolean append) {
        File file = new File(URL_PRODUCT);
        try (FileWriter fileWriter = new FileWriter(file, append);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Product product1 : product) {
                if (product1 instanceof ProductNormal) {
                    bufferedWriter.write(toString( product1));
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi File!");
        } catch (Exception e) {
            System.out.println("lỗi ghi file");
        }
    }
    private void writeFile3(List<Product> product, boolean append) {
        File file = new File(URL_PRODUCTSALE);
        try (FileWriter fileWriter = new FileWriter(file, append);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Product product1 : product) {
                if (product1 instanceof ProductSale) {
                    bufferedWriter.write(toString2((ProductSale) product1));
                    bufferedWriter.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Lỗi ghi File!");
        } catch (Exception e) {
            System.out.println("lỗi ghi file");
        }
    }
   private void writeFileSale(List<Product> products,boolean append){
       File file = new File(URL_PRODUCTSALE);
       try (FileWriter fileWriter = new FileWriter(file, append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
           if (products instanceof ProductSale) {
               bufferedWriter.write(toString2((ProductSale) products));
               bufferedWriter.newLine();
           }
       } catch (IOException e) {
        System.out.println("Lỗi ghi File!");
    } catch (Exception e) {
        System.out.println("lỗi ghi file");
    }
   }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        File file = new File(URL_PRODUCT);
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] t = line.split(",");
                if (t.length>=4){
                    products.add(new ProductNormal(t[0],t[1],t[2],t[3]));

                }


            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thất File");
        } catch (IOException e) {
            System.out.println("Lỗi đọc dữ liệu File");
        }
        return products;
    }

    public List<Product> getSaleAll() {
        List<Product> products = new ArrayList<>();
        File file = new File(URL_PRODUCTSALE);
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] t = line.split(",");
                if (t.length>=5){
                    products.add(new ProductSale(t[0],t[1],t[2],t[3],t[4]));

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thất File");
        } catch (IOException e) {
            System.out.println("Lỗi đọc dữ liệu File");
        }
        return products;
    }

    public void updateProduct(String id, String name, String price, String quantity) {
        List<Product> products = getAll();
        for (Product product : products) {
            if (product.getId().equals(id)) {
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.add(new ProductNormal(id, name, price, quantity));
            }
        }
        writeFile2(products, false);
    }

    public void updateProductSale(String id, String name, String price, String quantity, String promotion) {
        List<Product> products1 = getSaleAll();
        for (Product productSale : products1){
            if (productSale.getId().equals(id)) {
                productSale.setName(name);
                productSale.setPrice(price);
                productSale.setQuantity(quantity);
                if (productSale instanceof ProductSale){
                    ((ProductSale) productSale).setPromotion(promotion);
                }
            }
        }
        writeFile3(products1,false);
    }

    private String toString2(ProductSale products) {
        return  products.getId() + "," + products.getName() + "," + products.getPrice() + "," + products.getQuantity() + ","+ products.getPromotion();
    }

    private String toString(Product products) {
        return products.getId() + "," + products.getName() + "," + products.getPrice() + "," + products.getQuantity();
    }

    public boolean isIdExists(String id) {
        return isIdExistsInFile(id, URL_PRODUCT) || isIdExistsInFile(id, URL_PRODUCTSALE);
    }
    public boolean isIdExitsSale(String id){
        return isIdExistsInFile(id,URL_PRODUCTSALE);
    }

    private boolean isIdExistsInFile(String id, String filePath) {
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(id)) {

                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void DeleteProduct(Product product){
        List<Product> products=getAll();
        List<Product> product2 = getSaleAll();

        for (int i =0; i< products.size();i++){
            if (products.get(i).getId().equals(product.getId())){
                products.remove(i);
                break;
            }
        }
        for (int j =0; j< product2.size();j++){
            if (product2.get(j).getId().equals(product.getId())){
                product2.remove(j);
                break;
            }
        }
        writeFile2(products,false);
        writeFile3(product2,false);
    }





}
