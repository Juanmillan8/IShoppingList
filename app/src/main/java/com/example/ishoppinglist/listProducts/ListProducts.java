package com.example.ishoppinglist.listProducts;

import com.example.ishoppinglist.models.Product;
import java.util.ArrayList;

public class ListProducts {

    public static ArrayList<Product> productArrayList = new ArrayList<Product>();

    public static void enterTestData(){

        for(int i=1;i<=10;i++){
            Product p = new Product();

            p.setId(i);
            p.setName("Product " + i);
            p.setInformativeNote("Note " + i);
            p.setNeedToBuy(true);
            productArrayList.add(p);

        }
    }

    public static void editProduct(Product p){

        for (int i = 0; i < productArrayList.size(); i++) {
            if(productArrayList.get(i).getId()==p.getId()){
                productArrayList.set(i, p);
            }
        }
    }

    public static void insertProducts(Product p){
        int id=0;
        for (Product product : ListProducts.productArrayList) {
            if(product.getId()>id){
                id=product.getId();
            }
        }
        p.setId(id+1);
        p.setNeedToBuy(false);

        productArrayList.add(p);


    }

    public static ArrayList<Product> getProductsThatNeedToBuy(){

        ArrayList<Product> productsThatNeedToBuy = new ArrayList<Product>();

        for (Product product : ListProducts.productArrayList) {

            if(product.getNeedToBuy()){
                productsThatNeedToBuy.add(product);
            }

        }

        return productsThatNeedToBuy;

    }

    public static ArrayList<Product> getProductsThatDontNeedToBuy(){

        ArrayList<Product> productsThatDontNeedToBuy = new ArrayList<Product>();

        for (Product product : ListProducts.productArrayList) {
            if(!product.getNeedToBuy()){
                productsThatDontNeedToBuy.add(product);
            }
        }
        return productsThatDontNeedToBuy;
    }

}
