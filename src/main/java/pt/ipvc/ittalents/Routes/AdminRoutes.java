package pt.ipvc.ittalents.Routes;

public abstract class AdminRoutes extends ViewFactory {
    public static void showDashboard() {
        createStage("Admin/Dashboard");
    }
    public static void showManageUsers() {
        createStage("Admin/ManageUsers");
    }
    public static void showManageSkills() {
        createStage("Admin/ManageSkills");
    }
    public static void showAddSkill() {
        createStage("Admin/AddSkill");
    }
    public static void showEditSkill() {
        createStage("Admin/EditSkill");
    }
}