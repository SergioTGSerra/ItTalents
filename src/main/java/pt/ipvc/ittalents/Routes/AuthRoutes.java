package pt.ipvc.ittalents.Routes;

public abstract class AuthRoutes extends ViewFactory {
    public static void showLogin() {
        createStage("Auth/Login");
    }
    public static void showRegister() {
        createStage("Auth/Register");
    }
}
