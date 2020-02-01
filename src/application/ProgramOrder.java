/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author ut2u
 */
public class ProgramOrder {
    
    public static void main(String[] args) throws ParseException {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        System.out.println("Enter client data:");
        System.out.print("Name: ");
        String clientName = sc.nextLine();
        System.out.print("email: ");
        String clientEmail = sc.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        Date clientBirthDate = sdf1.parse(sc.next());
        
        //Client client = new Client(clientName, clientEmail, clientBirthDate);
        
        //seting the ORDER's day and hour to GMT-Brazil
        Date x1 = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(x1);
        cal.add(Calendar.HOUR_OF_DAY, 0);
        x1 = cal.getTime();
        
        System.out.println("Enter order data:");
        sc.nextLine();
        System.out.print("Status: ");
        String orderStatus = sc.nextLine();
        
        Order order = new Order(x1, OrderStatus.valueOf(orderStatus), new Client(clientName, clientEmail, clientBirthDate));
        
        System.out.print("Insert the number of order items to this order: ");
        int numberOfItems = sc.nextInt();
        for(int i = 1; i <= numberOfItems; i++){
            System.out.printf("Enter #%d item data", i);
            System.out.println();
            sc.nextLine();
            System.out.print("Product Name: ");
            String productName = sc.nextLine();
            System.out.print("Product price: U$");
            double productPrice = sc.nextDouble();
            System.out.print("Quantity: ");
            int numberItems = sc.nextInt();
            
            OrderItem orderItem = new OrderItem(numberItems, productPrice, new Product(productName, productPrice));
            order.addItem(orderItem);   
        }
        
        System.out.println();
        System.out.println("ORDER SUMMARY: ");
        System.out.println("Order moment: " + sdf2.format(order.getMoment()));
        System.out.println("Order status: " + order.getStatus());
        System.out.println("Client: " + order.clientToString("NAME") + " (" + order.clientToString("BIRTHDATE") + ") - " + order.clientToString("EMAIL"));
        System.out.println("\nOrder Items: ");
        order.itemsPrint();
        System.out.printf("Total price: U$%.2f%n", order.total());
        
        sc.close();
    }
    
}
