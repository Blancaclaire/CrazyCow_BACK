package Model.Entities;

import java.util.ArrayList;

public class Allergen {
    private int allergen_id;
    private String name;

    //private ArrayList<Ingredient> listIngredients = new ArrayList<Ingredient>();

    public int getAllergen_id() {
        return allergen_id;
    }

    private void setAllergen_id(int _allergen_id) {
        allergen_id = _allergen_id;
    }

    public String getName() {
        return name;
    }

    private void setName(String _name) {
        name = _name;
    }


    //Constructor

    public Allergen(int allergen_id, String name){
        setAllergen_id(allergen_id);
        setName(name);
    }

    public Allergen(){

    }


    @Override
    public String toString() {
        return "Allergen{" +
                "allergen_id=" + allergen_id +
                ", name='" + name + '\'' +
                '}';
    }
}
