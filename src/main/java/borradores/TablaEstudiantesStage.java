package Ventanas;

import ClasesPrincipales.Estudiante;
import Modelos.MostrarTodoModelo;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablaEstudiantesStage extends Application //Todas las class prinicplaes en JavaFX (nuevas ventanas) deben extender de Application.
{
    private int cursoId;
    public TablaEstudiantesStage(int cursoId)
    {
        this.setCursoId(cursoId);
    }
    @Override
    public void start(Stage stage) throws Exception
    {
        //Me tarigo los datos de la base de datos:
        ResultSet rs = MostrarTodoModelo.getData(this.getCursoId());
        // Crear la tabla
        TableView<Estudiante> tabla = new TableView<>();
// Crear columnas
        TableColumn<Estudiante, String> columnaNombre = new TableColumn<>("Nombre");
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tabla.getColumns().add(columnaNombre); // Agregar la columna de nombre al principio

// Agregar columnas dinámicamente para cada examen
        List<String> examenes = new ArrayList<>();
        while (rs.next()) {
            String examen = rs.getString("examen");
            if (!examenes.contains(examen)) {
                examenes.add(examen);
                TableColumn<Estudiante, String> columna = new TableColumn<>(examen);
                columna.setCellValueFactory(param -> {
                    Estudiante estudiante = param.getValue();
                    return new SimpleStringProperty(estudiante.getNotas().get(examen));
                });
                tabla.getColumns().add(columna);
            }
        }

// Agregar datos a la tabla
        ObservableList<Estudiante> datos = FXCollections.observableArrayList();
        rs.beforeFirst(); // Volver al principio del ResultSet
        Map<String, Estudiante> estudiantes = new HashMap<>();
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String examen = rs.getString("examen");
            String nota = rs.getString("nota");

            Estudiante estudiante = estudiantes.get(nombre);
            if (estudiante == null) {
                estudiante = new Estudiante(nombre);
                estudiantes.put(nombre, estudiante);
            }
            estudiante.getNotas().put(examen, nota);
        }
        datos.addAll(estudiantes.values());

        tabla.setItems(datos);



        // Crear la escena
        VBox root = new VBox(10);
        root.getChildren().add(tabla);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Tabla de estudiantes");
        stage.setScene(scene);
        stage.show();

    }
    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }
}


