package pt.ipvc.ittalents.Routes;

public abstract class ProfessionalRoutes extends ViewFactory {
    public static void showDashboard() {
        createStage("Professional/Dashboard");
    }
    public static void showMySettings() {
        createStage("Professional/MySettings");
    }
}
