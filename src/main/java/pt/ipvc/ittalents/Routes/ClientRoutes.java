package pt.ipvc.ittalents.Routes;

public abstract class ClientRoutes extends ViewFactory {
    public static void showDashboard() {
        createStage("Client/Dashboard");
    }
    public static void ShowMakeOffer() {
        createStage("Client/MakeOffer");
    }
}
