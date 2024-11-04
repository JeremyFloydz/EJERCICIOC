package org.example.ejec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal para la aplicación JavaFX que configura y lanza la interfaz de usuario.
 *
 * <p>Esta clase extiende {@link Application} y es responsable de inicializar el escenario
 * principal de la aplicación, cargar la interfaz desde un archivo FXML y establecer un ícono.</p>
 */
public class HelloApplication extends Application {

    /**
     * Método de entrada de la aplicación JavaFX. Configura el escenario principal,
     * carga la vista desde un archivo FXML y establece un ícono de ventana.
     *
     * @param stage el escenario principal de la aplicación, proporcionado automáticamente por JavaFX.
     * @throws IOException si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Carga el archivo FXML para definir la interfaz de usuario
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Cargar y establecer el ícono de la ventana
        Image icon = new Image(getClass().getResourceAsStream("/img/agenda.png"));
        stage.getIcons().add(icon);

        // Configuración de la escena principal de la aplicación
        Scene scene = new Scene(fxmlLoader.load(), 750, 450);
        stage.setTitle("Hello!");  // Establece el título de la ventana
        stage.setScene(scene);     // Asigna la escena al escenario
        stage.show();              // Muestra el escenario
    }

    /**
     * Método principal que lanza la aplicación JavaFX.
     *
     * @param args los argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();  // Llama a launch() para iniciar la aplicación
    }
}
