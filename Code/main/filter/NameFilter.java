package main.filter;

import main.model.Item;
import java.util.List;
import java.util.stream.Collectors;

public class NameFilter implements Filter {
    private String keyword;

    public NameFilter(String keyword) {
        this.keyword = keyword;
    }

    public List<Item> apply(List<Item> items) {
        return items.stream()
            .filter(item -> item.matchesKeyword(keyword))
            .collect(Collectors.toList());
    }
}
