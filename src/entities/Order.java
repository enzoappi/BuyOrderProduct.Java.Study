/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.enums.OrderStatus;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ut2u
 */
public class Order {
    
    private Date moment;
    private OrderStatus status;
    
    private Client client;
    private List<OrderItem> items = new ArrayList<>();

    public Order() {
    }

    public Order(Date moment, OrderStatus status, Client client) {
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderItem> getItems() {
        return items;
    }
    
    public void addItem(OrderItem item) {
        items.add(item);
    }
    
    public void removeItem(OrderItem item) {
        items.remove(item);
    }
    
    public String clientToString(String parametro) {
        if(parametro == "name" || parametro == "NAME") {
            return this.client.getName();
        }
        else if (parametro == "email" || parametro == "EMAIL"){
            return this.client.getEmail();
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(this.client.getBirthDate());
        }
    }
    
    public void itemsPrint() {
        for(OrderItem c : items) {
            System.out.println(c.getProduct().getName()
                    + ", " + c.getPrice() 
                    + ", Quantity: " + c.getQuantity()
                    + ", Sub-total: " + c.subTotal());
        }
    }
    
    public double total() {
        double sum = 0;
        for(OrderItem c : items) {
            sum += c.subTotal();
        }
        return sum;
    }
}
