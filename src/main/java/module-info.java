module pt.ipvc.ittalents {
    requires javafx.controls;
    requires javafx.fxml;

    exports pt.ipvc.ittalents;
    exports pt.ipvc.ittalents.Backend;
    exports pt.ipvc.ittalents.Backend.Exceptions;
    exports pt.ipvc.ittalents.Backend.Controllers.Professional;
    exports pt.ipvc.ittalents.Models;
    exports pt.ipvc.ittalents.Routes;
    exports pt.ipvc.ittalents.Backend.Controllers.Auth;
}