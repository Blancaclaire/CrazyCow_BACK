package Model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Product {

    public  enum eCategory{Burguer, ForBitting, Dessert, Drink};

    //Atributos
    private String _idProduct;
    private eCategory _category;
    private String _name;
    private String _description;
    private double _price;
    private String _imagen;


    //Getters and Setters

    public String getIdProduct() {
        return _idProduct;
    }

    private void setIdProduct(String idProdcut) {
        _idProduct = idProdcut;
    }

    public eCategory get_category() {
        return _category;
    }

    private void set_category(eCategory category) {
        _category = category;
    }

    public String getName() {
        return _name;
    }

    private void setName(String name) {
        _name = name;
    }

    public String get_description() {
        return _description;
    }

    private void set_description(String description) {
        _description = description;
    }

    public double getPrice() {
        return _price;
    }

    private void setPrice(double price) {
        _price = price;
    }

    public String get_imagen() {
        return _imagen;
    }

    private void set_imagen(String imagen) {
        _imagen = imagen;
    }

    //Constructor
    public Product(String idProduct, eCategory category, String description, String name, double price, String image){
        setIdProduct(idProduct);
        set_category(category);
        set_description(description);
        setName(name);
        setPrice(price);
        set_imagen(image);
    }

    //Convierte una lista de Productos a una cadena JSON
    public  String toArrayJson(ArrayList<Product> lista){
        Gson gson = new Gson();
        return gson.toJson(lista);
    }
}
