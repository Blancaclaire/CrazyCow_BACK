package Model.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa a un empleado en el sistema.
 * Contiene informacion personal, laboral y del empleado.
 */
public class Employee {

    // ==================== ATRIBUTOS ====================
    private int employee_id;
    private int manager_id;
    private int job_id;
    private  int restaurant_id;
    private double salary;
    private String name;
    private String surname;
    private String email;
    private String phone_number;
    private String user_name;
    private String password;
    private String address;
    private Date start_date;
    private String city;

    // ==================== MÉTODOS DE ACCESO (GETTERS/SETTERS) ====================


    public int getEmployee_id() {
        return employee_id;
    }

    private void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    private void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    // ==================== CONSTRUCTORES ====================

    public Employee(){

    }

    public Employee(int employee_id, int manager_id, double salary, String name, String surname, String email, String phone_number, String user_name, String password, String address, Date start_date) {
        this.employee_id = employee_id;
        this.manager_id = manager_id;
        this.salary = salary;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.user_name = user_name;
        this.password = password;
        this.address = address;
        this.start_date = start_date;
    }

    public Employee(int employee_id, int manager_id, int job_id, int restaurant_id, double salary, String name, String surname, String email, String phone_number, String user_name, String password, String address, Date start_date) {
        this.employee_id = employee_id;
        this.manager_id = manager_id;
        this.job_id = job_id;
        this.restaurant_id = restaurant_id;
        this.salary = salary;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.user_name = user_name;
        this.password = password;
        this.address = address;
        this.start_date = start_date;
    }

    // ==================== METODOS ====================


    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", manager_id=" + manager_id +
                ", job_id=" + job_id +
                ", restaurant_id=" + restaurant_id +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", start_date=" + start_date +
                '}';
    }

    /**
     * Convierte una lista de empleados a formato JSON usando la librería Gson.
     *
     * @param listEmployees Lista de empleados a convertir
     * @return Cadena en formato JSON que representa la lista de empleados
     */
    public static String toArrayJson(ArrayList<Employee> listEmployees) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(listEmployees);
        return resp;
    }

}
