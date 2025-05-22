package main.gui;

import main.model.Inventory;
import main.model.Item;
import main.filter.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ShoppingSystemGUI extends JFrame {
    private List<Item> inventory;
    private JTextField keywordField;
    private JComboBox<String> categoryBox;
    private JTextArea resultArea;

    public ShoppingSystemGUI() {
        setTitle("Shopping System - Pipe & Filter Pattern");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inventory = Inventory.getDefaultItems(); // Creator principle

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 5)); // Reduced rows from 5 to 3
        keywordField = new JTextField();
        categoryBox = new JComboBox<>(new String[]{"All", "Electronics", "Footwear", "Accessories", "Office"});

        inputPanel.add(new JLabel("Search Keyword:"));
        inputPanel.add(keywordField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryBox);

        JButton searchBtn = new JButton("Search");
        JButton resetBtn = new JButton("Reset");
        inputPanel.add(searchBtn);
        inputPanel.add(resetBtn);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Button Actions
        searchBtn.addActionListener(e -> performSearch());
        resetBtn.addActionListener(e -> resetForm());

        setVisible(true);
    }

    private void performSearch() {
        PipeLine pipeline = new PipeLine();
        String keyword = keywordField.getText().trim();
        String category = (String) categoryBox.getSelectedItem();

        if (!keyword.isEmpty()) pipeline.addFilter(new NameFilter(keyword));
        if (!"All".equals(category)) pipeline.addFilter(new CategoryFilter(category));

        List<Item> results = pipeline.execute(inventory);
        resultArea.setText(results.isEmpty() ? "No items found." :
                results.stream().map(Item::toString).reduce("", (a, b) -> a + b + "\n"));
    }

    private void resetForm() {
        keywordField.setText("");
        categoryBox.setSelectedIndex(0);
        resultArea.setText("");
    }
}
