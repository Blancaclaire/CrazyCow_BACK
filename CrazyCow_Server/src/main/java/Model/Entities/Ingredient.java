package Model.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Clase que representa un ingrediente en el sistema.
 * Contiene información básica sobre los ingredientes utilizados en los productos.
 */
public class Ingredient {

    // ==================== ATRIBUTOS ====================

    private int ingredient_id;
    private String name;


    // ==================== MÉTODOS DE ACCESO (GETTERS/SETTERS) ====================

    public int get_ingredient_id() {
        return ingredient_id;
    }

    public void set_ingredient_id(int _ingredient_id) {
        ingredient_id = _ingredient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    // ==================== CONSTRUCTORES ====================

    public Ingredient(int ingredient_id,String name){
        set_ingredient_id(ingredient_id);
        setName(name);
    }

    public Ingredient (){

    }

    // ==================== METODOS ====================

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredient_id='" + ingredient_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Convierte una lista de ingredientes a formato JSON usando la librería Gson.
     * @param listIngredients Lista de ingredientes a convertir
     * @return Cadena en formato JSON que representa la lista de ingredientes
     */

    public static String toArrayJson(ArrayList<Ingredient> listIngredients) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(listIngredients);
        return resp;
    }


}
