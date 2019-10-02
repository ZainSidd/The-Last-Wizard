import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
// Game Launcher for LibGDX
public class MyLauncher {

	public static void main(String[] args) {
		TheLastWizard myProgram = new TheLastWizard();
        new LwjglApplication(myProgram, "Last Wizard", 270, 480); // Makes the game
	}

}
