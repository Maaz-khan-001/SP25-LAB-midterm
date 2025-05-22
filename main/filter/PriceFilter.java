package main.filter;


import main.model.Item;
import java.util.List;
import java.util.stream.Collectors;

public class PriceFilter implements Filter {
    private double min, max;

    public PriceFilter(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public List<Item> apply(List<Item> items) {
        return items.stream()
            .filter(item -> item.isInPriceRange(min, max))
            .collect(Collectors.toList());
    }
}
