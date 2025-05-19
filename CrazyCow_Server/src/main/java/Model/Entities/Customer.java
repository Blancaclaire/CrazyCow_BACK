package Model.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Clase que representa a un cliente en el sistema.
 * Contiene información personal, de contacto y credenciales de acceso.
 */
public class Customer {

    // ==================== ATRIBUTOS ====================
    private int customer_id;
    private String name;
    private String surname;
    private String email;
    private String phone_number;
    private String user_name;
    private String password;
    private String address;


    // ==================== MÉTODOS DE ACCESO (GETTERS/SETTERS) ====================

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int _customer_id) {
        customer_id = _customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String _surname) {
        surname = _surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String _email) {
        email = _email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String _phone_number) {
        phone_number = _phone_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String _user_name) {
        user_name = _user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        password = _password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String _address) {
        address = _address;
    }

    // ==================== CONSTRUCTORES ====================

    public Customer() {

    }

    public Customer(int customer_id, String name, String surname, String email, String phone_number, String user_name, String password, String address) {
        setCustomer_id(customer_id);
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPhone_number(phone_number);
        setUser_name(user_name);
        setPassword(password);
        setAddress(address);
    }


    // ==================== METODOS ====================

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    /**
     * Convierte una lista de clientes a formato JSON usando la librería Gson.
     *
     * @param listCustomer Lista de clientes a convertir
     * @return Cadena en formato JSON que representa la lista de clientes
     */
    public static String toArrayJson(ArrayList<Customer> listCustomer) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(listCustomer);
        return resp;
    }

    /**
     * Convierte un solo cliente a formato JSON usando la librería Gson.
     *
     * @param customer Cliente a convertir
     * @return Cadena en formato JSON que representa el cliente
     */
    public static String toJson(Customer customer) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(customer);
    }


}
