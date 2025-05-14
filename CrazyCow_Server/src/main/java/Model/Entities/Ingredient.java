package Model.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Ingredient {
    private int ingredient_id;
    private String name;

    //private ArrayList<Allergen> listAllergen = new ArrayList<Allergen>();

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

    //Constructor
    public Ingredient(int ingredient_id,String name){
        set_ingredient_id(ingredient_id);
        setName(name);
    }

    public Ingredient (){

    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredient_id='" + ingredient_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static String toArrayJson(ArrayList<Ingredient> listIngredients) {

        //GsonBuilder permite configurar gson
        //Permite activar pretty printing, excluir campos null, cambiar nombres de campos, etc.
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(listIngredients);
        return resp;
    }


}
