package main.filter;


import main.model.Item;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryFilter implements Filter {
    private String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    public List<Item> apply(List<Item> items) {
        return items.stream()
            .filter(item -> item.isInCategory(category))
            .collect(Collectors.toList());
    }
}
