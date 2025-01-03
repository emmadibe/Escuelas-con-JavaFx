module org.example.tpfinalconjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.persistence;
    requires net.bytebuddy;
    requires org.hibernate.orm.core;
    requires jdk.jfr;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires java.desktop;

    opens org.example.tpfinalconjavafx to javafx.fxml;
    exports org.example.tpfinalconjavafx;
    exports Scenes;
    opens Scenes to javafx.fxml;
    // Exporta el paquete Ventanas para que sea accesible por javafx.graphics
    exports Ventanas to javafx.graphics;

    opens ClasesPrincipales to javafx.base; // Abre el paquete a javafx.base

}