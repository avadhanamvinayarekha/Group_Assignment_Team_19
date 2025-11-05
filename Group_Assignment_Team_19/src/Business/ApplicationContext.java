/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

/**
 *
 * @author Hp
 */
public class ApplicationContext {
     private static UniversitySystem systemInstance = null;
    
    /**
     * Get the shared system instance
     * Creates it on first call with pre-populated data
     */
    public static UniversitySystem getSystem() {
        if (systemInstance == null) {
            systemInstance = ConfigureSystem.configure();
            System.out.println("✓ System initialized with pre-populated data");
            System.out.println("✓ Total users: " + systemInstance.getUserAccountDirectory().getAllAccounts().size());
        }
        return systemInstance;
    }
    
    /**
     * Reset the system (useful for testing)
     */
    public static void resetSystem() {
        systemInstance = ConfigureSystem.configure();
        System.out.println("✓ System reset with fresh data");
    }
}
