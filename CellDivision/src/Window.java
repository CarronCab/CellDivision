
import javax.swing.JFrame;

@SuppressWarnings("serial") //Ignore this warning : "The serializable class Window does not declare a static final serialVersionUID field of type long"

public class Window extends JFrame{

	WindowContent panel;
	public static final int WIDTH = 1680;
	public static final int HEIGHT = 1050;

	public Window() {
		super();
		WindowParameters();
	}

	private void WindowParameters() {

		this.setSize(WIDTH, HEIGHT);		//set the size of the window
		this.setResizable(false);	//Resizable not allowed
		this.setVisible(true);
		this.setLocationRelativeTo(null); //Create the window in the middle of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ends the program when the window is closed
		this.setAlwaysOnTop(true); //Allows the window to stay always on top of every opened windows

		panel = new WindowContent();
		this.setContentPane(panel);

	}

}
