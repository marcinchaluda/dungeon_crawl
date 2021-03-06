package com.codecool.dungeoncrawl.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BottomPanel {
    private final Game game;

    public BottomPanel(Game game) {
        this.game = game;
    }

    public VBox createLogContainer(Label previousLog, Label currentLog) {
        VBox logsMsg = new VBox(previousLog, currentLog);
        styleLogContainer(logsMsg);
        styleLogMessage(previousLog, currentLog);
        return logsMsg;
    }

    private void styleLogContainer(VBox logsMsg) {
        logsMsg.setStyle("-fx-background-color: #472D3C");
        logsMsg.setPadding(new Insets(20));
        logsMsg.setAlignment(Pos.BASELINE_CENTER);
    }

    private void styleLogMessage(Label previousLog, Label currentLog) {
        styleLabel(currentLog, "-fx-text-alignment: center; -fx-text-fill: #CFC6B8; -fx-font-size: 20;");

        styleLabel(previousLog, "-fx-text-alignment: center; -fx-text-fill: #CFC6B8; -fx-font-size: 15; -fx-opacity: 0.5");
    }

    private void styleLabel(Label currentLog, String styles) {
        currentLog.setContentDisplay(ContentDisplay.CENTER);
        currentLog.setStyle(styles);
    }
}
