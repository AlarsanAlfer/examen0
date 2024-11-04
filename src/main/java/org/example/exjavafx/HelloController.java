package org.example.exjavafx;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableColumn<Usuario, Integer> idversion;
    @FXML
    private TextField idNuevoCorreo;
    @FXML
    private TableColumn<Usuario, String> iddate;
    @FXML
    private Button añadirBtn;
    @FXML
    private TableColumn<Usuario, String> idplataforma;
    @FXML
    private TableColumn<Usuario, Boolean> idadmin;
    @FXML
    private TableColumn<Usuario, String> idcorreo;
    @FXML
    private ChoiceBox<String> idNuevoPlataforma;
    @FXML
    private Spinner idNuevoVersion;
    @FXML
    private Button limpiarBtn;
    @FXML
    private CheckBox idNuevoAdmin;
    @FXML
    private TableView<Usuario> idTabla;

    @FXML
    protected void onHelloButtonClick() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idcorreo.setCellValueFactory((fila)->{
            return new SimpleStringProperty(fila.getValue().getCorreo());
        });
        idplataforma.setCellValueFactory((fila)->{
            return new SimpleStringProperty(fila.getValue().getPlataforma());
        });
        idversion.setCellValueFactory((fila) -> {
            return new SimpleIntegerProperty(fila.getValue().getVersion()).asObject();
        });
        idadmin.setCellValueFactory((fila) -> {
            return new SimpleBooleanProperty(fila.getValue().getAdmin());
        });
        iddate.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getDate());
        });

        idNuevoPlataforma.getItems().addAll("Windows", "Linux", "MacOs");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);
        idNuevoVersion.setValueFactory(valueFactory);


        añadirBtn.setOnAction(event -> nuevaFila());
        limpiarBtn.setOnAction(event -> limpiarTabla());

        idTabla.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Usuario usuarioSeleccionado = (Usuario)idTabla.getSelectionModel().getSelectedItem();
                if (usuarioSeleccionado != null) {
                    mostrarDetallesUsuario(usuarioSeleccionado);
                }
            }
        });
    }
    /**
     * Recogemos los valores de los campos, creamos un usuario y lo añadimos a la tabla
     * **/
    public void nuevaFila() {
        String c1 = idNuevoCorreo.getText();
        String c2 = idNuevoPlataforma.getValue();
        Integer c3 = (Integer) idNuevoVersion.getValue();
        Boolean c4 = idNuevoAdmin.isSelected();
        String c5 = LocalDateTime.now().toString();

        Usuario usuario = new Usuario(c1,c2,c4,c3,c5);
        idTabla.getItems().add(usuario);
        idTabla.refresh();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nuevo Usuario");
        alert.setHeaderText("Se ha añadido un nuevo Usuario");
        alert.showAndWait();
    }
        /**
         * Despues de una ventana de confirmación,
         * **/
    public void limpiarTabla() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("¿Desea limpiar los datos de la tabla?");
        alerta.setHeaderText("¿Estás seguro de que deseas limpiar los datos de la tabla?");

        Optional<ButtonType> result = alerta.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            idTabla.getItems().clear();
        }
    }
    /**
     * muestra los detalles completos del usuario
     * **/
    private void mostrarDetallesUsuario(Usuario usuario) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Detalles del Usuario");

        VBox dialogPane = new VBox();
        dialogPane.getChildren().addAll(
                new Label("Correo: " + usuario.getCorreo()),
                new Label("Plataforma: " + usuario.getPlataforma()),
                new Label("Versión: " + usuario.getVersion()),
                new Label("Administrador: " + (usuario.getAdmin() ? "Sí" : "No")),
                new Label("Fecha: " + usuario.getDate())
        );

        dialog.getDialogPane().setContent(dialogPane);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }
}
