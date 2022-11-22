module pt.ipvc.ittalents {
    requires javafx.controls;
    requires javafx.fxml;
    //Extra
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens pt.ipvc.ittalents to javafx.fxml;
    exports pt.ipvc.ittalents;
    exports pt.ipvc.ittalents.Controllers;
    exports pt.ipvc.ittalents.Models;
    exports pt.ipvc.ittalents.Views;

}