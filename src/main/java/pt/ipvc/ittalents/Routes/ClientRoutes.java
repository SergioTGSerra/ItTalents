package pt.ipvc.ittalents.Routes;

public abstract class ClientRoutes extends ViewFactory {
    public static void showDashboard() {
        createStage("Client/Dashboard");
    }
}
