
import javax.swing.JFrame;

@SuppressWarnings("serial") //Ignore this warning : "The serializable class Window does not declare a static final serialVersionUID field of type long"

public class Window extends JFrame{

	WindowContent panel;
	public static final int WIDTH = 840; //840
	public static final int HEIGHT = 800; //560

	public Window() {
		super();
		WindowParameters();
	}

	private void WindowParameters() {

		this.setResizable(false);	//Resizable not allowed
		//this.setLocationRelativeTo(null); //Create the window in the middle of the screen
		this.setSize(WIDTH, HEIGHT);		//set the size of the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ends the program when the window is closed
        this.setVisible(true);
	}

}
