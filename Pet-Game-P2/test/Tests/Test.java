package Tests;

import GUIComponents.MainFrame;
import PetGame.PetDBConnection;

public class Test {

    PetDBConnection db = new PetDBConnection();
    MainFrame mf = new MainFrame();

    /**
     * Test data for a pet that will die in the next turn
     */
    public void testDyingPet() {
        db.connectPetGameDB();
        db.newUser("test1", "", "dog", "doggo", 10, 2, 10, 0, 0, 0, 0.5, 0.5, 0.5, true);
    }

    /**
     * Test data for a dead pet
     */
    public void testDeadPet() {
        db.connectPetGameDB();
        db.newUser("test2", "", "cat", "cat", 0, 0, 0, 0, 0, 0, 0.5, 0.5, 0.5, true);
    }

    /**
     * Test login for dying pet
     */
    public void testDyingLogin() {
        db.connectPetGameDB();
        db.loginDB("test1", "");
    }

}
