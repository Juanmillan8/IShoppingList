package com.example.ishoppinglist.dataBase;

import android.util.Log;

import com.example.ishoppinglist.models.Product;
import java.util.ArrayList;
import java.util.Random;

public class DataBase {

    //Lista estática de productos que será accesible desde cualquier parte de la aplicación
    public static ArrayList<Product> productArrayList = new ArrayList<Product>();

    /**
     * Método que sirve para insertar datos de prueba en la lista de productos
     */
    public static void enterTestData(){
        Random random = new Random();
        //Por cada iteración del bucle creo un nuevo objeto Product, le asigno datos y lo añado a la lista de productos
        for(int i=1;i<=20;i++){
            Product p = new Product();

            p.setId(i);
            p.setName("Product " + i);
            p.setInformativeNote("Note " + i);
            p.setNeedToBuy(true);
            p.setContainsGluten(random.nextBoolean());
            p.setContainsLactose(random.nextBoolean());


            productArrayList.add(p);

        }
    }

    /**
     * Edita un producto existente en la lista de productos
     *
     * @param p El producto con la nueva información que reemplazará al producto existente en la lista, identificado por el mismo id
     */
    public static void editProduct(Product p){

        //Recorremos la lista para encontrar el producto cuya id sea la misma que la id del producto pasado al método por parámetro
        for (int i = 0; i < productArrayList.size(); i++) {
            //Si encontramos el producto que estábamos buscando, lo reemplazamos por el producto pasado al método por parámetro y salimos del bucle
            if(productArrayList.get(i).getId()==p.getId()){
                productArrayList.set(i, p);
                break;
            }
        }
    }

    /**
     * Inserta un nuevo producto en la lista de productos
     *
     * @param p El producto que se va a insertar en la lista
     */
    public static void insertProducts(Product p){

        //Le añadimos la id al nuevo producto, esta id será el tamaño actual de la lista + 1, da igual que lo hagamos de esta forma, ya que no se
        //van a eliminar productos de la lista, por lo tanto no necesitamos saber cual id es mas alta independientemente del numero de
        //productos almacenados, simplemente con saber el tamaño de la lista y sumarle 1 nos basta, con esto nos aseguramos de que cada producto
        //tenga un id único
        p.setId(productArrayList.size()+1);

        //Añadimos el nuevo producto a la lista
        productArrayList.add(p);


    }

    /**
     * Método que devuelve una lista de productos que necesitan ser comprados
     */
    public static ArrayList<Product> getProductsThatNeedToBuy(){

        //Creamos una nueva lista para almacenar los productos que necesitan ser comprados
        ArrayList<Product> productsThatNeedToBuy = new ArrayList<Product>();

        //Recorremos la lista de productos y para cada producto que estemos recorriendo comprobamos si necesitan ser comprados o no, si necesitan ser comprados
        //los almacenamos en la lista creada anteriormente
        for (Product product : DataBase.productArrayList) {
            if(product.getNeedToBuy()){
                productsThatNeedToBuy.add(product);
            }
        }

        //Por último retornaremos la lista con todos los productos que necesitan ser comprados
        return productsThatNeedToBuy;

    }

    public static ArrayList<Product> getProductsWithoutGluten(){

        ArrayList<Product> productsThatContainsLactose = new ArrayList<Product>();

        for (Product product : DataBase.productArrayList) {
            if(!product.getContainsGluten() && product.getNeedToBuy()){
                productsThatContainsLactose.add(product);
            }
        }
        return productsThatContainsLactose;

    }

    public static ArrayList<Product> getProductsWithoutLactose(){

        ArrayList<Product> productsThatContainsLactose = new ArrayList<Product>();

        for (Product product : DataBase.productArrayList) {
            if(!product.getContainsLactose() && product.getNeedToBuy()){
                productsThatContainsLactose.add(product);
            }
        }
        return productsThatContainsLactose;

    }



    /**
     * Método que devuelve una lista de los nombres de los productos que NO necesitan ser comprados
     */
    public static ArrayList<String> getProductsThatDontNeedToBuy(){

        //Creamos una nueva lista de String la cual posteriormente retornaré
        ArrayList<String> productsThatDontNeedToBuy = new ArrayList<String>();

        //Recorremos los productos de la lista almacenando a la lista creada anteriormente los nombres de los productos
        //que no necesitan ser comprados
        for (Product product : DataBase.productArrayList) {
            if(!product.getNeedToBuy()){
                productsThatDontNeedToBuy.add(product.getName());
            }
        }
        //Por último retornamos la lista
        return productsThatDontNeedToBuy;
    }

}
