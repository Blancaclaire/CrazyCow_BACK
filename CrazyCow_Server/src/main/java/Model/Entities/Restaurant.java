package Model.Entities;

public class Restaurant {

    //Atributos

    private int restaurant_id;
    private String adress;
    private String city;
    private String phone_number;
    private String opening_hours;
    private String closing_hours;



    //Getter y Setters

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    public String getClosing_hours() {
        return closing_hours;
    }

    public void setClosing_hours(String closing_hours) {
        this.closing_hours = closing_hours;
    }


    //Constructores

    public Restaurant(){

    }

    public Restaurant (int restaurant_id){
        this.restaurant_id = restaurant_id;
    }

    public Restaurant(int restaurant_id, String adress, String city, String phone_number, String opening_hours, String closing_hours) {
        this.restaurant_id = restaurant_id;
        this.adress = adress;
        this.city = city;
        this.phone_number = phone_number;
        this.opening_hours = opening_hours;
        this.closing_hours = closing_hours;
    }


    //Metodos


    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurant_id=" + restaurant_id +
                ", adress='" + adress + '\'' +
                ", city='" + city + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", opening_hours='" + opening_hours + '\'' +
                ", closing_hours='" + closing_hours + '\'' +
                '}';
    }


}
