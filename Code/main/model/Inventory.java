package main.model;


import java.util.List;

public class Inventory {
    public static List<Item> getDefaultItems() {
        return List.of(
            new Item("Apple iPhone", 999.99, "Electronics"),
            new Item("Samsung TV", 799.99, "Electronics"),
            new Item("Nike Shoes", 120.00, "Footwear"),
            new Item("Leather Wallet", 49.99, "Accessories"),
            new Item("Laptop Stand", 25.00, "Office"),
            new Item("Office Chair", 150.00, "Office")
        );
    }
}
