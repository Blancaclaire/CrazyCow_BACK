package Model;

import Exceptions.DuplicateIngredientException;
import Exceptions.IngredientNotFoundException;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Burguer extends Product {

    private ArrayList<Ingredient> listIngredients = new ArrayList<Ingredient>();

    //Constructor
    public Burguer(String idProduct, String name, double price) {
        super(idProduct, name, price);
    }

    public void addIngredient(Ingredient ing) throws DuplicateIngredientException {
        for (Ingredient i : listIngredients) {
            if (i.equals(ing)) {
                throw new DuplicateIngredientException("The ingredient alredy exists in the burguer");
            } else {
                listIngredients.add(ing);
            }
        }
    }

    public void deleteIngredient(Ingredient ing) throws IngredientNotFoundException {
        for (Ingredient i : listIngredients) {
            if (i.equals(ing)) {
                listIngredients.remove(ing);
            } else {
                throw new IngredientNotFoundException("The ingredient is not found on the product");
            }
        }
    }



    //estos metodos deben tener su case en BurguerAction??





}
