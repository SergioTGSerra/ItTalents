module pt.ipvc.ittalents {
    requires javafx.controls;
    requires javafx.fxml;


    opens pt.ipvc.ittalents to javafx.fxml;
    exports pt.ipvc.ittalents;
    exports pt.ipvc.ittalents.Controllers;
    exports pt.ipvc.ittalents.Backend;
    exports pt.ipvc.ittalents.Exceptions;
}