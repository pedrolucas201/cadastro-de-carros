package com.veiculosmonitor.gui;

import com.veiculosmonitor.entities.Carro;
import com.veiculosmonitor.entities.Caminhao;
import com.veiculosmonitor.entities.Moto;
import com.veiculosmonitor.entities.Veiculo;
import com.veiculosmonitor.services.VeiculosImplRepositorio;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VeiculoMonitorGUI extends Application {
    private VeiculosImplRepositorio repositorio = new VeiculosImplRepositorio();
    private ObservableList<Veiculo> veiculosList = FXCollections.observableArrayList();
    private TableView<Veiculo> tabelaVeiculos = new TableView<>();

    private TextField campoPlaca = new TextField();
    private TextField campoModelo = new TextField();
    private ComboBox<String> campoTipo = new ComboBox<>();
    private Button btnSalvar = new Button("Salvar");
    private Button btnAtualizar = new Button("Atualizar");
    private Button btnExcluir = new Button("Excluir");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Monitor de Veículos");

        // Layout principal
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));

        // Configurar tabela
        configurarTabela();
        layout.setCenter(tabelaVeiculos);

        // Configurar formulário
        VBox formulario = criarFormulario();
        layout.setRight(formulario);

        // Cena
        Scene cena = new Scene(layout, 800, 600);
        primaryStage.setScene(cena);
        primaryStage.show();

        // Carregar dados
        carregarDados();
    }

    private void configurarTabela() {
        TableColumn<Veiculo, String> colunaPlaca = new TableColumn<>("Placa");
        colunaPlaca.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPlaca()));

        TableColumn<Veiculo, String> colunaModelo = new TableColumn<>("Modelo");
        colunaModelo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getModelo()));

        TableColumn<Veiculo, String> colunaTipo = new TableColumn<>("Tipo");
        colunaTipo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getClass().getSimpleName()));

        tabelaVeiculos.getColumns().addAll(colunaPlaca, colunaModelo, colunaTipo);
        tabelaVeiculos.setItems(veiculosList);

        tabelaVeiculos.setOnMouseClicked(event -> {
            Veiculo selecionado = tabelaVeiculos.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                preencherFormulario(selecionado);
            }
        });
    }

    private VBox criarFormulario() {
        VBox formulario = new VBox(10);
        formulario.setPadding(new Insets(10));
        formulario.setStyle("-fx-background-color: #f4f4f4;");

        campoPlaca.setPromptText("Placa");
        campoModelo.setPromptText("Modelo");

        campoTipo.getItems().addAll("Carro", "Moto", "Caminhão");
        campoTipo.setPromptText("Tipo do Veículo");

        btnSalvar.setOnAction(event -> salvarVeiculo());
        btnAtualizar.setOnAction(event -> atualizarVeiculo());
        btnExcluir.setOnAction(event -> excluirVeiculo());

        formulario.getChildren().addAll(
                new Label("Placa:"), campoPlaca,
                new Label("Modelo:"), campoModelo,
                new Label("Tipo:"), campoTipo,
                btnSalvar, btnAtualizar, btnExcluir
        );

        return formulario;
    }

    private void carregarDados() {
        veiculosList.clear();
        veiculosList.addAll(repositorio.buscarTodos());
    }

    private void salvarVeiculo() {
        if (validarFormulario()) {
            Veiculo novoVeiculo;
            String tipo = campoTipo.getValue();
            if (tipo.equals("Carro")) {
                novoVeiculo = new Carro();
            } else if (tipo.equals("Moto")) {
                novoVeiculo = new Moto();
            } else {
                novoVeiculo = new Caminhao();
            }
            novoVeiculo.setPlaca(campoPlaca.getText());
            novoVeiculo.setModelo(campoModelo.getText());
            repositorio.salvar(novoVeiculo);
            carregarDados();
            limparFormulario();
        }
    }

    private void atualizarVeiculo() {
        Veiculo selecionado = tabelaVeiculos.getSelectionModel().getSelectedItem();
        if (selecionado != null && validarFormulario()) {
            selecionado.setPlaca(campoPlaca.getText());
            selecionado.setModelo(campoModelo.getText());
            repositorio.atualizar(selecionado);
            carregarDados();
            limparFormulario();
        }
    }

    private void excluirVeiculo() {
        Veiculo selecionado = tabelaVeiculos.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            repositorio.excluir(selecionado.getId());
            carregarDados();
            limparFormulario();
        }
    }

    private void preencherFormulario(Veiculo veiculo) {
        campoPlaca.setText(veiculo.getPlaca());
        campoModelo.setText(veiculo.getModelo());
        campoTipo.setValue(veiculo.getClass().getSimpleName());
    }

    private void limparFormulario() {
        campoPlaca.clear();
        campoModelo.clear();
        campoTipo.setValue(null);
    }

    private boolean validarFormulario() {
        if (campoPlaca.getText().isEmpty() || campoModelo.getText().isEmpty() || campoTipo.getValue() == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Validação");
            alerta.setHeaderText("Campos obrigatórios");
            alerta.setContentText("Preencha todos os campos antes de salvar.");
            alerta.showAndWait();
            return false;
        }
        return true;
    }
}
