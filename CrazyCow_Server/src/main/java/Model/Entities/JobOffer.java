package Model.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Clase que representa una oferta de trabajo en el sistema.
 * Contiene información sobre las posiciones laborales disponibles.
 */
public class JobOffer {

    // ==================== ATRIBUTOS ====================
    private int job_offer_id;
    private int job_id;
    private String title;
    private String descriptioin;
    private String location;

    // ==================== MÉTODOS DE ACCESO (GETTERS/SETTERS) ====================

    public int getJob_offer_id() {
        return job_offer_id;
    }

    public void setJob_offer_id(int job_offer_id) {
        this.job_offer_id = job_offer_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptioin() {
        return descriptioin;
    }

    public void setDescriptioin(String descriptioin) {
        this.descriptioin = descriptioin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    // ==================== CONSTRUCTORES ====================

    public JobOffer(){

    }

    public JobOffer(int job_offer_id, int job_id, String title, String descriptioin, String location) {
        this.job_offer_id = job_offer_id;
        this.job_id = job_id;
        this.title = title;
        this.descriptioin = descriptioin;
        this.location = location;
    }

    // ==================== METODOS ====================


    @Override
    public String toString() {
        return "JobOffer{" +
                "job_offer_id=" + job_offer_id +
                ", job_id=" + job_id +
                ", title='" + title + '\'' +
                ", descriptioin='" + descriptioin + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    /**
     * Convierte una lista de ofertas a formato JSON
     * @param listOffers Lista de ofertas de trabajo
     * @return Cadena JSON con la lista de ofertas
     */
    public static String toArrayJson(ArrayList<JobOffer> listOffers) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(listOffers);
        return resp;
    }
}
