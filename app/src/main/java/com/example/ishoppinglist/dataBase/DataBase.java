package com.example.ishoppinglist.dataBase;

import com.example.ishoppinglist.models.Product;
import java.util.ArrayList;

public class DataBase {

    //Lista estática de productos que será accesible desde cualquier parte de la aplicación
    public static ArrayList<Product> productArrayList = new ArrayList<Product>();

    /**
     * Método que sirve para insertar datos de prueba en la lista de productos
     */
    public static void enterTestData(){

        //Por cada iteración del bucle creo un nuevo objeto Product, le asigno datos y lo añado a la lista de productos
        for(int i=1;i<=20;i++){
            Product p = new Product();

            p.setId(i);
            p.setName("Product " + i);
            p.setInformativeNote("Note " + i);
            p.setNeedToBuy(true);
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
            //Si encontramos el producto que estábamos buscando, lo reemplazamos por el producto pasado al método por parámetro
            if(productArrayList.get(i).getId()==p.getId()){
                productArrayList.set(i, p);
            }
        }
    }

    /**
     * Inserta un nuevo producto en la lista de productos
     *
     * @param p El producto que se va a insertar en la lista
     */
    public static void insertProducts(Product p){
        //Variable id inicializada a 0, la inicializo a 0 para que la id del primer producto (osea, la id más baja) sea siempre mayor al número almacenado en
        //esta variable
        int id=0;

        //Recorremos la lista de productos para buscar el producto con el id más alto en la lista
        for (Product product : DataBase.productArrayList) {
            //Si la id del producto que estamos actualmente recorriendo es mas alta que la id almacenada sustituimos la id almacenada por la id del producto
            //actual, de esta forma, cuando salgamos del bucle, la variable id acabará obteniendo la id mas alta
            if(product.getId()>id){
                id=product.getId();
            }
        }

        //Asignamos al nuevo producto un id que sea el siguiente al id más alto encontrado y por ultimo lo añadimos a la lista
        p.setId(id+1);
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
