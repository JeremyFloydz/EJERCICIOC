package org.example.ejec;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/**
 * Controlador principal para la aplicación JavaFX que permite la gestión de una lista de personas.
 *
 * <p>Ofrece funcionalidad para agregar, modificar y eliminar personas de una lista, y se comunica con
 * elementos de la interfaz gráfica definidos en el archivo FXML.</p>
 */
public class HelloController {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField edadField;
    @FXML
    private Button agregarButton;
    @FXML
    private Button modificarButton;
    @FXML
    private Button eliminarButton;
    @FXML
    private TableView<Persona> tableView;
    @FXML
    private TableColumn<Persona, String> nombreColumn;
    @FXML
    private TableColumn<Persona, String> apellidosColumn;
    @FXML
    private TableColumn<Persona, Integer> edadColumn;

    private ObservableList<Persona> personas;
    private Persona personaSeleccionada;

    /**
     * Inicializa el controlador y configura los elementos de la interfaz y los listeners.
     * Este método es llamado automáticamente después de que los elementos @FXML han sido inyectados.
     */
    @FXML
    public void initialize() {
        personas = FXCollections.observableArrayList();
        tableView.setItems(personas);

        // Configuración de las columnas de la tabla
        nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        apellidosColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));
        edadColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());

        // Configuración de acciones de los botones
        agregarButton.setOnAction(e -> agregarPersona());
        modificarButton.setOnAction(e -> modificarPersona());
        eliminarButton.setOnAction(e -> eliminarPersona());

        // Listener para cambios en la selección de la tabla
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            personaSeleccionada = newValue;
            mostrarDatosPersonaSeleccionada(newValue);
        });
    }

    /**
     * Agrega una nueva persona a la lista, tras validar los datos ingresados por el usuario.
     */
    private void agregarPersona() {
        String nombre = nombreField.getText().trim();
        String apellidos = apellidosField.getText().trim();
        String edadText = edadField.getText().trim();

        // Validación de campos
        if (nombre.isEmpty() || apellidos.isEmpty() || edadText.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben estar rellenos.");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadText);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La edad debe ser un número entero.");
            return;
        }

        Persona nuevaPersona = new Persona(nombre, apellidos, edad);

        // Verificar duplicados
        if (personas.contains(nuevaPersona)) {
            mostrarAlerta("Error", "Esta persona ya existe en la lista.");
        } else {
            personas.add(nuevaPersona);
            mostrarAlerta("Éxito", "Persona agregada con éxito.");
            limpiarFormulario();
        }
    }

    /**
     * Modifica los datos de una persona seleccionada en la lista.
     * Si no hay selección, muestra una alerta.
     */
    private void modificarPersona() {
        if (personaSeleccionada == null) {
            mostrarAlerta("Error", "No hay ninguna persona seleccionada para modificar.");
            return;
        }

        String nombre = nombreField.getText().trim();
        String apellidos = apellidosField.getText().trim();
        String edadText = edadField.getText().trim();

        if (nombre.isEmpty() || apellidos.isEmpty() || edadText.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos deben estar rellenos.");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadText);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La edad debe ser un número entero.");
            return;
        }

        Persona personaModificada = new Persona(nombre, apellidos, edad);

        if (personas.stream().anyMatch(p -> !p.equals(personaSeleccionada) && p.equals(personaModificada))) {
            mostrarAlerta("Error", "Esta persona ya existe en la lista.");
            return;
        }

        personaSeleccionada.setNombre(nombre);
        personaSeleccionada.setApellidos(apellidos);
        personaSeleccionada.setEdad(edad);

        tableView.refresh();
        mostrarAlerta("Éxito", "Persona modificada con éxito.");
        limpiarFormulario();
    }

    /**
     * Elimina la persona seleccionada en la lista.
     * Muestra una confirmación antes de proceder con la eliminación.
     */
    private void eliminarPersona() {
        if (personaSeleccionada == null) {
            mostrarAlerta("Error", "Debes seleccionar una persona para eliminar.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Estás seguro de que deseas eliminar a " + personaSeleccionada.getNombre() + "?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Confirmar Eliminación");
        alert.setHeaderText(null);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                personas.remove(personaSeleccionada);
                mostrarAlerta("Éxito", "Persona eliminada con éxito.");
                limpiarFormulario();
            }
        });
    }

    /**
     * Muestra los datos de la persona seleccionada en los campos de texto.
     * Si no hay selección, limpia los campos.
     *
     * @param persona la persona seleccionada o null si no hay selección.
     */
    private void mostrarDatosPersonaSeleccionada(Persona persona) {
        if (persona != null) {
            nombreField.setText(persona.getNombre());
            apellidosField.setText(persona.getApellidos());
            edadField.setText(String.valueOf(persona.getEdad()));
        } else {
            nombreField.clear();
            apellidosField.clear();
            edadField.clear();
        }
    }

    /**
     * Limpia el formulario de entrada de datos y deselecciona la persona en la tabla.
     */
    private void limpiarFormulario() {
        nombreField.clear();
        apellidosField.clear();
        edadField.clear();
        tableView.getSelectionModel().clearSelection();
    }

    /**
     * Muestra una alerta informativa al usuario con un título y mensaje especificados.
     *
     * @param titulo el título de la alerta.
     * @param mensaje el mensaje de la alerta.
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
