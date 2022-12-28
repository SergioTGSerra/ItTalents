module pt.ipvc.ittalents {
    requires javafx.controls;
    requires javafx.fxml;

    opens pt.ipvc.ittalents to javafx.fxml;
    exports pt.ipvc.ittalents;
    exports pt.ipvc.ittalents.Backend;
    exports pt.ipvc.ittalents.Backend.Exceptions;
    exports pt.ipvc.ittalents.Backend.Controllers.Professional;
    exports pt.ipvc.ittalents.Backend.Controllers.Admin;
    exports pt.ipvc.ittalents.Backend.Controllers.Admin.Components;
    exports pt.ipvc.ittalents.Backend.Controllers.Client;
    exports pt.ipvc.ittalents.Backend.Controllers;
    exports pt.ipvc.ittalents.Models;
    exports pt.ipvc.ittalents.Routes;
    exports pt.ipvc.ittalents.Backend.Controllers.Auth;
    exports pt.ipvc.ittalents.Backend.Controllers.Professional.Components;
}