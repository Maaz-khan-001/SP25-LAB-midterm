package main.model;

public class Item {
    private String name;
    private double price;
    private String category;

    public Item(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // === Information Expert Methods ===
    public boolean matchesKeyword(String keyword) {
        return name.toLowerCase().contains(keyword.toLowerCase());
    }

    public boolean isInCategory(String cat) {
        return category.equalsIgnoreCase(cat);
    }

    public boolean isInPriceRange(double min, double max) {
        return price >= min && price <= max;
    }

    public String toString() {
        return name + " - $" + price + " - " + category;
    }
}
