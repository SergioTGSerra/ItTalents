package pt.ipvc.ittalents.Routes;

public abstract class AdminRoutes extends ViewFactory {
    public static void showDashboard() {
        createStage("Admin/Dashboard");
    }
}