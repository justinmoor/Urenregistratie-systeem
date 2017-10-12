package Controllers;


import Controllers.Styles;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.*;
import java.util.stream.Collectors;

public class AutoCompletionTextfieldController extends TextField {



    private final SortedSet<String> entries;
    private ContextMenu entriesPopup;


    public AutoCompletionTextfieldController() {
        super();
        System.out.println("10");
        this.entries = new TreeSet<>();
        this.entriesPopup = new ContextMenu();
        System.out.println("11");
        setListener();
    }

    private void setListener() {
        System.out.println("12");
        textProperty().addListener((Observable, oldValue, newValue) -> {
            String enteredText = getText();
            System.out.println("13");
            if (enteredText == null || enteredText.isEmpty()) {
                entriesPopup.hide();
                System.out.println("14");
            } else {
                List<String> filteredEntries = entries.stream()
                        .filter(e -> e.toLowerCase().contains(enteredText.toLowerCase()))
                        .collect(Collectors.toList());
                System.out.println("15");
                if (!filteredEntries.isEmpty()) {
                    populatePopup(filteredEntries, enteredText);
                    System.out.println("16");
                    if(!entriesPopup.isShowing()) {
                        entriesPopup.show(Controllers.AutoCompletionTextfieldController.this, Side.BOTTOM, 0, 0);
                        System.out.println("17");
                    }
                } else {
                    entriesPopup.hide();
                    System.out.println("18");
                }
            }
        });

        focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            entriesPopup.hide();
            System.out.println("19");
        });
    }

    private void populatePopup(List<String> searchResult, String searchReauest) {

        List<CustomMenuItem> menuItems = new LinkedList<>();

        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);

        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);

            Label entryLabel = new Label();
            entryLabel.setGraphic(Styles.buildTextFlow(result, searchReauest));
            entryLabel.setPrefHeight(10);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            menuItems.add(item);

            item.setOnAction(actionEvent -> {
                setText(result);
                positionCaret(result.length());
                entriesPopup.hide();
            });
        }

        entriesPopup.getItems().clear();;
        entriesPopup.getItems().addAll(menuItems);
    }

    public SortedSet<String> getEntries() {
        return entries;
    }
}

