package main.filter;


import main.model.Item;
import java.util.ArrayList;
import java.util.List;

public class PipeLine {
    private final List<Filter> filters = new ArrayList<>();

    public void addFilter(Filter f) {
        filters.add(f);
    }

    public List<Item> execute(List<Item> items) {
        List<Item> result = items;
        for (Filter f : filters) {
            result = f.apply(result);
        }
        return result;
    }
}
