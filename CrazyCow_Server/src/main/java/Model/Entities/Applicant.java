package Model.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;

public class Applicant {

    private int applicant_id;
    private String name;
    private String surname;
    private String email;
    private String phone_number;
    private String address;
    private String resume;



    //Getter y Setter

    public int getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(int applicant_id) {
        this.applicant_id = applicant_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }




    //Constructores

    public Applicant(){

    }

    public Applicant(int applicant_id, String name, String surname, String email, String phone_number, String address, String resume) {
        this.applicant_id = applicant_id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.resume = resume;

    }

    //metodos


    @Override
    public String toString() {
        return "Applicant{" +
                "applicant_id=" + applicant_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                ", resume='" + resume + '\'' +
                '}';
    }

    public static String toArrayJson(ArrayList<Applicant> listApplicants) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(listApplicants);
        return resp;
    }


}
