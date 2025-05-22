package main.filter;


import main.model.Item;
import java.util.List;

public interface Filter {
    List<Item> apply(List<Item> items);
}
