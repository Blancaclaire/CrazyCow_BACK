package Model.Entities;
import Model.DAO.IngredientDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;


/**
 * Clase que representa un producto en el sistema.
 * Contiene información básica del producto, sus ingredientes y alérgenos asociados.
 * Proporciona métodos para conversión a JSON tanto manual como usando la librería Gson.
 */

public class Product {

    // ==================== ATRIBUTOS ====================

    private int product_id;
    private int category_id;
    private String product_name;
    private String description;
    private double price;
    private String image;

    ArrayList<Ingredient> listIngredients = new ArrayList<Ingredient>();
    ArrayList<Allergen> listAllergen = new ArrayList<Allergen>();




    // ==================== MÉTODOS DE ACCESO (GETTERS/SETTERS) ====================

    public int getProduct_id() {
        return product_id;
    }


    public void setProduct_id(int p_product_id) {
        product_id = p_product_id;
    }

    public int getCategory_id() {
        return category_id;
    }


    public void setCategory_id(int p_category_id) {
        category_id = p_category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String p_product_name) {
        product_name = p_product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String p_description) {
        description = p_description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double p_price) {
        price = p_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String p_image) {
        image = p_image;
    }

    public ArrayList<Ingredient> getListIngredients() {
        return listIngredients;
    }

    public void setListIngredients(ArrayList<Ingredient> listIngredients) {
        this.listIngredients = listIngredients;
    }

    public ArrayList<Allergen> getListAllergen() {
        return listAllergen;
    }

    public void setListAllergen(ArrayList<Allergen> listAllergen) {
        this.listAllergen = listAllergen;
    }


    // ==================== CONSTRUCTORES ====================

    public Product() {

    }


    public Product(int product_id, int category_id, String product_name, String description, double price, String image) {
        setProduct_id(product_id);
        setCategory_id(category_id);
        setProduct_name(product_name);
        setDescription(description);
        setPrice(price);
        setImage(image);

    }

    // ==================== METODOS ====================



    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", category_id=" + category_id +
                ", product_name='" + product_name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }



    /**
     * Convierte una lista de productos a formato JSON manualmente.
     *
     * @param listProducts Lista de productos a convertir
     * @return Cadena en formato JSON que representa la lista de productos
     */
    public  String fromArrayToJson(ArrayList<Product> listProducts) {

        String resp = "[";
        for (Product p : listProducts) {
            resp += "{" +
                    " 'product_id ' : " + p.getProduct_id() + "',"
                    + "'category_id ' : " + p.getCategory_id() + "',"
                    + "'product_name ' : " + p.getProduct_name() + "',"
                    + "'description ' : " + p.getDescription() + "',"
                    + "'price ' : " + p.getPrice() + "',"
                    + "'image ' : " + p.getImage() + "}";
            resp = resp + "'";
        }

        resp = resp.substring(0, resp.length() - 1); //Se elimina la ultima coma cuando el Json termina
        resp = resp += "]";
        return resp;
    }

    /**
     * Convierte una lista de productos a formato JSON usando la librería Gson.
     *
     * @param listProducts Lista de productos a convertir
     * @return Cadena en formato JSON bien formateada
     */
    public static String toArrayJson(ArrayList<Product> listProducts) {

        //GsonBuilder permite configurar gson
        //Permite activar pretty printing, excluir campos null, cambiar nombres de campos, etc.
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();// Formato JSON más legible

        Gson gson = builder.create();
        String resp = gson.toJson(listProducts);
        return resp;
    }

    /**
     * Convierte un solo producto a formato JSON usando la librería Gson.
     *
     * @param product Producto a convertir
     * @return Cadena en formato JSON que representa el producto
     */
    public static String toJson(Product product) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(product);
    }



}
