import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Window  {

    private static final Dimension PREF_SIZE = new Dimension(50, 50);
    private JPanel ui;
    private JPanel gameGrid;

@SuppressWarnings("serial") //Ignore this warning : "The serializable class Window does not declare a static final serialVersionUID field of type long"


	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public Window() {
		super();
		JFrame panel=new JFrame();
		panel.setSize(WIDTH, HEIGHT);		//set the size of the window
		panel.setResizable(false);	//Resizable not allowed
		panel.setVisible(true);
		panel.setLocationRelativeTo(null); //Create the window in the middle of the screen
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ends the program when the window is closed
		panel.setAlwaysOnTop(true); //Allows the window to stay always on top of every opened windows

		JLabel yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(248, 213, 131));
        yellowLabel.setPreferredSize(new Dimension(780,500 ));
        yellowLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
        JPanel greenMenuBar = new JPanel();
        greenMenuBar.setOpaque(true);
        greenMenuBar.setBackground(new Color(154, 165, 127));
        greenMenuBar.setPreferredSize(new Dimension(780,40));
        greenMenuBar.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		panel.getContentPane().add(greenMenuBar,BorderLayout.SOUTH);
        panel.getContentPane().add(yellowLabel, BorderLayout.CENTER);
        
		panel.pack();
		panel.setVisible(true);
	}
	public static void main(String[] args) {
		Window window=new Window();

	}
}
