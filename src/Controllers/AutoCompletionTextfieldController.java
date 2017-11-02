package Controllers;


import Controllers.Styles;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Dit is de controller die het auto aanvullen mogelijk maakt met het registreren van uren en het opvragen van het week overzicht
 *
 * @author Alex de Bruin
 * @version 3.0
 *
 */

public class AutoCompletionTextfieldController extends TextField {



    private final SortedSet<String> entries;
    private ContextMenu entriesPopup;


    public AutoCompletionTextfieldController() {
        super();
        this.entries = new TreeSet<>();
        this.entriesPopup = new ContextMenu();
        setListener();
    }

    private void setListener() {
        textProperty().addListener((Observable, oldValue, newValue) -> {
            String enteredText = getText();
            if (enteredText == null || enteredText.isEmpty()) {
                entriesPopup.hide();
            } else {
                List<String> filteredEntries = entries.stream()
                        .filter(e -> e.toLowerCase().contains(enteredText.toLowerCase()))
                        .collect(Collectors.toList());
                if (!filteredEntries.isEmpty()) {
                    populatePopup(filteredEntries, enteredText);
                    if(!entriesPopup.isShowing()) {
                        entriesPopup.show(Controllers.AutoCompletionTextfieldController.this, Side.BOTTOM, 0, 0);
                    }
                } else {
                    entriesPopup.hide();
                }
            }
        });

        focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            entriesPopup.hide();
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

    public void setEntries() {
        entries.clear();
    }
}

