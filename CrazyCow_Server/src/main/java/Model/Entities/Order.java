package Model.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    //Atributos

    private int order_id;
    private int customer_id;
    private int restaurant_id;
    private Date order_date;
    private  String order_status;
    private double total;
    private String location;
    private Payment payment;

    ArrayList<OrderDetail> order_details;

    //Getter and Setter

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<OrderDetail> getOrder_details() {
        return order_details;
    }

    public void setOrder_details(ArrayList<OrderDetail> order_details) {
        this.order_details = order_details;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    //Constructores


    public Order() {
        this.order_details = new ArrayList<>(); // Inicializa la lista aquí
    }

    public Order(int order_id, int customer_id, int restaurant_id, Date order_date,String order_status, double total, String location) {
        this();
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.restaurant_id = restaurant_id;
        this.order_date = order_date;
        this.order_status = order_status;
        this.total = total;
        this.location = location;

    }

    //Metodos

    public void addOrderDetail(OrderDetail detail) {
        this.order_details.add(detail);
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", customer_id=" + customer_id +
                ", restaurant_id=" + restaurant_id +
                ", order_date=" + order_date +
                ", order_status='" + order_status + '\'' +
                ", total=" + total +
                ", location='" + location + '\'' +
                '}';
    }

    public static String toArrayJson(ArrayList<Order> listOrders) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(listOrders);
        return resp;
    }

}
