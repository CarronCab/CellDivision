import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial") //Ignore this warning : "The serializable class Window does not declare a static final serialVersionUID field of type long"

public class WindowContent extends JPanel{

	private JPanel grid;
	private JButton cleanButton;
	

	public WindowContent() {

		super();
		WindowContentParameters(); //set the window content parameters
		

	}


	private void WindowContentParameters() {

		//this.setLayout(null); //No layout -> items disposition made manually
		this.gridParameters(); //set the parameters of the grid
		this.CleanButtonsParameters(); //set the parameters of the clean button

	}


	private void gridParameters() {
		
		int row = 52;
		int columns = 135;

		grid = new JPanel(); //Call the constructor to allocate the memory space
		this.grid.setLayout(new GridLayout(row, columns, 0, 0)); //Organize the label into a grid layout of x by y
		this.grid.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		for (int i = 0 ; i < row; i++ ) {
			
			for (int j = 0 ; j < columns ; j++ ) {
				
				JLabel tmp = new JLabel("    ");
				tmp.setSize(50, 50);
				tmp.setOpaque(true);
				tmp.setBackground(Color.white);
				//stmp.setBorder(BorderFactory.createLineBorder(Color.black));
				this.grid.add(tmp);
			}
		}
		
		//this.label.setBounds(10, 10, Window.WIDTH - 37 , Window.HEIGHT-200); //set the position of the label and its size
		this.grid.setBorder(BorderFactory.createLineBorder(Color.black));
		//this.label.setText("my tag"); //set the text displayed in the label
		//this.label.setOpaque(true); //Make the panel opaque
		//this.label.setBackground(Color.white); //set the color background of the panel
		this.add(grid); //add the label to the window content

	}

	private void CleanButtonsParameters() {

		this.cleanButton = new JButton();
		this.cleanButton.setText("Clean");
		//this.cleanButton.setBounds(Window.WIDTH - 207, Window.HEIGHT - 100, 180, 40);
		this.add(cleanButton);
	}
}
