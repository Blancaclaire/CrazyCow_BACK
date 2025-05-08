package Model;

import java.util.ArrayList;

public class Ingredient {
    private String _ingredient_id;
    private String _name;
    private ArrayList<Allergen> listAllergen = new ArrayList<Allergen>();

    public String get_ingredient_id() {
        return _ingredient_id;
    }

    private void set_ingredient_id(String ingredient_id) {
        _ingredient_id = ingredient_id;
    }

    public String getName() {
        return _name;
    }

    private void setName(String name) {
        _name = name;
    }

    //Constructor
    Ingredient(String ingredient_id,String name){
        set_ingredient_id(ingredient_id);
        setName(name);
    }

}
