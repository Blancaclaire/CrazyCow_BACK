package Model.Entities;

import java.util.ArrayList;

/**
 * Clase que representa un alérgeno en el sistema.
 * Contiene información básica sobre un alérgeno que puede estar presente en los productos.
 */
public class Allergen {

    // ==================== ATRIBUTOS ====================

    private int allergen_id;
    private String name;
    public int getAllergen_id() {
        return allergen_id;
    }


    // ==================== MÉTODOS DE ACCESO (GETTERS/SETTERS) ====================

    private void setAllergen_id(int _allergen_id) {
        allergen_id = _allergen_id;
    }

    public String getName() {
        return name;
    }

    private void setName(String _name) {
        name = _name;
    }


    // ==================== CONSTRUCTORES ====================

    public Allergen(int allergen_id, String name){
        setAllergen_id(allergen_id);
        setName(name);
    }

    public Allergen(){

    }


    // ==================== METODOS ====================

    @Override
    public String toString() {
        return "Allergen{" +
                "allergen_id=" + allergen_id +
                ", name='" + name + '\'' +
                '}';
    }
}
