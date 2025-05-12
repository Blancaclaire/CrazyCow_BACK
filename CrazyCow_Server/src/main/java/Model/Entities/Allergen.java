package Model.Entities;

import java.util.ArrayList;

public class Allergen {
    private String _allergen_id;
    private String _name;

    private ArrayList<Ingredient> listIngredients = new ArrayList<Ingredient>();

    public String getAllergen_id() {
        return _allergen_id;
    }

    private void setAllergen_id(String allergen_id) {
        _allergen_id = allergen_id;
    }

    public String getName() {
        return _name;
    }

    private void setName(String name) {
        _name = name;
    }

    //Constructor

    public Allergen(String allergen_id, String name){
        setAllergen_id(allergen_id);
        setName(name);
    }

}
