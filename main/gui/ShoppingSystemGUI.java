package main.gui;

import main.model.Inventory;
import main.model.Item;
import main.filter.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ShoppingSystemGUI extends JFrame {
    private List<Item> inventory;
    private JTextField keywordField, minPriceField, maxPriceField;
    private JComboBox<String> categoryBox;
    private JTextArea resultArea;

    public ShoppingSystemGUI() {
        setTitle("Shopping System - Pipe & Filter Pattern");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inventory = Inventory.getDefaultItems(); // Creator principle

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 5));
        keywordField = new JTextField();
        minPriceField = new JTextField();
        maxPriceField = new JTextField();
        categoryBox = new JComboBox<>(new String[]{"All", "Electronics", "Footwear", "Accessories", "Office"});

        inputPanel.add(new JLabel("Search Keyword:"));
        inputPanel.add(keywordField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryBox);
        inputPanel.add(new JLabel("Min Price:"));
        inputPanel.add(minPriceField);
        inputPanel.add(new JLabel("Max Price:"));
        inputPanel.add(maxPriceField);

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
        Pipeline pipeline = new Pipeline();
        String keyword = keywordField.getText().trim();
        String category = (String) categoryBox.getSelectedItem();
        String minStr = minPriceField.getText().trim();
        String maxStr = maxPriceField.getText().trim();

        if (!keyword.isEmpty()) pipeline.addFilter(new NameFilter(keyword));
        if (!"All".equals(category)) pipeline.addFilter(new CategoryFilter(category));

        try {
            if (!minStr.isEmpty() && !maxStr.isEmpty()) {
                double min = Double.parseDouble(minStr);
                double max = Double.parseDouble(maxStr);
                pipeline.addFilter(new PriceFilter(min, max));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price input!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Item> results = pipeline.execute(inventory);
        resultArea.setText(results.isEmpty() ? "No items found." :
            results.stream().map(Item::toString).reduce("", (a, b) -> a + b + "\n"));
    }

    private void resetForm() {
        keywordField.setText("");
        minPriceField.setText("");
        maxPriceField.setText("");
        categoryBox.setSelectedIndex(0);
        resultArea.setText("");
    }
}
