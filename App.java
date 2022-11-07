import java.util.ArrayList;
import java.util.Scanner;

import Item.Item;
import Order.Order;

public class App {
    private ArrayList<Order> orders;
    private double moneyEarned;

    private String headerString = "  ___ _                ___       __     \n" +
            " | _ ) |_  _ ___ ___  / __|__ _ / _|___ \n" +
            " | _ \\ | || / -_|_-< | (__/ _` |  _/ -_)\n" +
            " |___/_|\\_,_\\___/__/  \\___\\__,_|_| \\___|\n";

    private Scanner scan;

    public App() {
        this.orders = new ArrayList<Order>();
        this.scan = new Scanner(System.in);
        this.moneyEarned = 0;
    }

    public void run() {
        this.displayMainMenu();
        this.scan.close();
    }

    private void displayMainMenu() {
        while (true) {
            System.out.println(headerString);

            System.out.printf("Total Earnings: %f\n", this.moneyEarned);

            System.out.println("1. Add Order");
            System.out.println("2. Show All Orders");
            System.out.println("3. Remove Order");
            System.out.println("4. Exit\n");

            int selectedMenu = 0;
            System.out.print("> ");
            while (!this.scan.hasNextInt()) {
                System.out.println("Option must be an integer!");
                System.out.print("> ");
                this.scan.nextLine();
            }

            selectedMenu = this.scan.nextInt();
            if (selectedMenu == 1)
                this.displayAddOrderMenu();
            if (selectedMenu == 2)
                this.displayShowAllOrdersMenu();
            if (selectedMenu == 3)
                this.displayRemoveOrderMenu();
            if (selectedMenu == 4)
                break;
        }
    }

    private void displayAddOrderMenu() {
        System.out.print("Table Number: ");
        while (!this.scan.hasNextInt()) {
            System.out.println("Table number must be an integer!");
            System.out.print("Table Number: ");
            this.scan.nextLine();
        }

        Order newOrder = new Order();

        int tableNumber = this.scan.nextInt();
        newOrder.setTableNumber(tableNumber);
        this.scan.nextLine();

        char addAgain = 'y';
        do {
            System.out.print("Item Name: ");
            String name = this.scan.nextLine();

            System.out.print("Item Price: ");
            double price = this.scan.nextDouble();
            this.scan.nextLine();

            System.out.print("Quantity: ");
            int quantity = this.scan.nextInt();
            this.scan.nextLine();

            Item newItem = new Item(name, price, quantity);

            newOrder.addItem(newItem);
            newOrder.setTotal(newOrder.getTotal() + newItem.getPrice());

            System.out.print("Add another item? (y/n) ");
            addAgain = Character.toLowerCase(this.scan.nextLine().charAt(0));
        } while (addAgain == 'y');

        this.addOrder(newOrder);
    }

    private void displayShowAllOrdersMenu() {
        if (this.orders.size() == 0) {
            System.out.println("There are no orders.");
        }

        double grandTotal = 0;
        for (Order order : this.orders) {
            ArrayList<Item> items = order.getItems();
            System.out.println("-----------------");
            System.out.printf("Table #%d\n", order.getTableNumber());
            System.out.println("Items:");
            int i = 1;
            for (Item item : items) {
                System.out.printf(" %d.\n", i);
                System.out.printf(" Name: %s\n", item.getName());
                System.out.printf(" Quantity: %d\n", item.getQuantity());
                System.out.printf(" Price: %f\n\n", item.getPrice());
                i++;
            }

            System.out.printf("\nTax: %f\n", order.getTax());
            System.out.printf("Service Fee: %f\n", order.getServiceFee());
            System.out.printf("Total Price: %f\n", order.getTotal());
            System.out.println("-----------------");

            grandTotal += order.getTotal();
        }

        System.out.printf("Grand Total: %f\n", grandTotal);
        this.pressEnterToContinue();
    }

    private void displayRemoveOrderMenu() {
        for (Order order : this.orders) {
            ArrayList<Item> items = order.getItems();
            System.out.println("-----------------");
            System.out.printf("Table #%d\n", order.getTableNumber());
            System.out.println("Items:");
            int i = 1;
            for (Item item : items) {
                System.out.printf(" %d.\n", i);
                System.out.printf(" Name: %s\n", item.getName());
                System.out.printf(" Quantity: %d\n", item.getQuantity());
                System.out.printf(" Price: %f\n\n", item.getPrice());
                i++;
            }

            System.out.printf("\nTax: %f\n", order.getTax());
            System.out.printf("Service Fee: %f\n", order.getServiceFee());
            System.out.printf("Total Price: %f\n", order.getTotal());
            System.out.println("-----------------");
        }

        System.out.print("Table Number: ");
        while (!this.scan.hasNextInt()) {
            System.out.println("Table number must be an integer!");
            System.out.print("Table Number: ");
            this.scan.next();
        }

        int tableNumber = this.scan.nextInt();
        int oldSize = this.orders.size();
        this.removeOrder(tableNumber);

        if (this.orders.size() == oldSize) {
            System.out.println("Table not found!");
        }

        this.pressEnterToContinue();
    }

    private void addOrder(Order newOrder) {
        this.orders.add(newOrder);
    }

    private void removeOrder(int tableNumber) {
        int idx = 0;
        for (Order order : orders) {
            if (order.getTableNumber() == tableNumber) {
                this.moneyEarned += order.getTotal();
                this.orders.remove(idx);
                break;
            }
            idx++;
        }
    }

    private void pressEnterToContinue() {
        System.out.println("Press Enter to continue...");
        this.scan.nextLine();
        this.scan.nextLine();
    }
}
