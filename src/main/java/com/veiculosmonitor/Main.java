package com.veiculosmonitor;

import com.veiculosmonitor.gui.VeiculoMonitorGUI;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        // Define a codificação padrão como UTF-8
        System.setProperty("file.encoding", "UTF-8");

        // Inicia a aplicação JavaFX
        Application.launch(VeiculoMonitorGUI.class, args);
    }
}
