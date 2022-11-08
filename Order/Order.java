package Order;

import java.util.ArrayList;

import Item.Item;

public class Order {
    private ArrayList<Item> items = new ArrayList<Item>();
    private int tableNumber;
    private double total;

    public Order() {
        this.total = 0;
    }

    public int getTableNumber() {
        return this.tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public double getTax() {
        return this.total * 0.1;
    }

    public double getServiceFee() {
        return this.total * 0.075;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
