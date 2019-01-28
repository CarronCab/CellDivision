import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;


public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	//List of cells
	public static Vector<Cell> listCell = new Vector();
	
	//Speed parameters
	static final int S_MIN = 50;
	static final int S_MAX = 2000;
	static final int S_INIT = 1500;
	
	//Temperature parameters
	static final int T_MIN = -50;
	static final int T_MAX = 100;
	static final int T_INIT = 37;
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public static void main(String[] args) throws CloneNotSupportedException {
		
		//Generation of the cell population set to 0
		int generation = 0;

		//Creation of the mother cell
		Cell firstCell = new Cell();
		listCell.add(firstCell);
		firstCell.setCanMut(false);
		
		//Creation of the window
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL,
                S_MIN, S_MAX, S_INIT);
		JSlider temperatureSlider = new JSlider(JSlider.HORIZONTAL,
                T_MIN, T_MAX, T_INIT);
		JTextArea sizeOfTheList = new JTextArea();
		JButton cleanButton = new JButton();
		Grid grid = new Grid();
		JPanel panel = new JPanel();
        Window window = new Window();
       
        GridLayout experimentLayout = new GridLayout(0,3, 10, 10);
        
        panel.setLayout(experimentLayout);
        window.setLayout(new BorderLayout(10,10));
        
        cleanButton.setText("Exit");
        
        sizeOfTheList.setEditable(true);
        sizeOfTheList.setText("size :" );
        sizeOfTheList.setBackground(null);
        
      //Turn on labels at major tick marks.
        speedSlider.setMajorTickSpacing(10);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        
      //Create the label table
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer( 0 ), new JLabel("Stop") );
        labelTable.put( new Integer( S_MAX/10 ), new JLabel("Slow") );
        labelTable.put( new Integer( S_MAX ), new JLabel("Fast") );
        speedSlider.setLabelTable( labelTable );
        
        //Turn on labels at major tick marks.
        temperatureSlider.setMajorTickSpacing(10);
        temperatureSlider.setMinorTickSpacing(1);
        temperatureSlider.setPaintTicks(true);
        temperatureSlider.setPaintLabels(true);
        
      //Create the label table
        Hashtable labelTable2 = new Hashtable();
        labelTable2.put( new Integer( 0 ), new JLabel("0") );
        labelTable2.put( new Integer( -45 ), new JLabel("-50") );
        labelTable2.put( new Integer( T_MAX - 5 ), new JLabel("100") );
        temperatureSlider.setLabelTable( labelTable2 );
    
        window.add(grid,BorderLayout.CENTER);
        window.add(panel, BorderLayout.PAGE_END);  
        
        panel.add(temperatureSlider);
        panel.add(speedSlider);
        panel.add(cleanButton);
        panel.add(sizeOfTheList);
       
        
        //Display the first cell
        grid.fillCell(firstCell.getPos_x(), firstCell.getPos_y());
    
        //Randomization of the mitosis
		Random r = new Random();
		int low = 0;
		int high = 100;
		int result;
		
		while (true) {
			
			cleanButton.addActionListener(new ActionListener(){  
				    public void actionPerformed(ActionEvent e){  
				           
				            System.exit(0);
				            
				    }  
				    });  
					
			//Display the generation
			System.out.printf("Generation " + generation + "\n");
			System.out.print(temperatureSlider.getValue());
			
			if(listCell.size() > 0) {
				
				//Check every cell on the list
				for(int i = 0 ; i < listCell.size() ; i++) {
					
					//Ask a random value between 0 and 100
					result = r.nextInt(high-low) + low;
					
					//Cell die after 3 generation
					if(listCell.get(i).ripCell()) {
						
						//Display a dead cell (black square) and remove the cell form the list
						grid.deadCell(listCell.get(i).getPos_x(),listCell.get(i).getPos_y());
						listCell.remove(listCell.get(i));
					
					} else {
						
						//Ask for mitosis
						try {
							
							mitosis(listCell.get(i), grid,i);
							
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						
						//Comparing the probability of the cell to do a mitosis with the random value
						if(listCell.get(i).getProbOfMut() > result)  {
												
							//Autozize the mutation
							listCell.get(i).setCanMut(true);
							
						}
			
						//Display some characteristic of the cell
						//listCell.get(i).Display(i);
						
						//Age of the cell plus one
						listCell.get(i).happyBirthday();
					}
	
				}
				
			}
			
			//Display the population size at this generation
			System.out.printf("size = %d\n\n" , listCell.size());
			sizeOfTheList.setText("size : " + listCell.size());
			
			//Wait some seconds
			try {
				
				TimeUnit.MILLISECONDS.sleep(2000 - speedSlider.getValue());
				
			} catch (InterruptedException e) {

				
				e.printStackTrace();
			}
			
			//Increment generation and go to the next one
			generation++;
		
		}
		
	}

	private static void mitosis(Cell cell, Grid grid, int i) {
		
		//Randomization of the cells spawns
		Random x = new Random();
		Random y = new Random();
		int l = 0;
		int h = 2;
		int newX;
		int newY;
		int newMappedX;
		int newMappedY;
		
		boolean collision1, collision2;
		
		//Do the mitosis if the cell can mutate
		if(cell.canMut() == true ) {
			
			//Remove the current cell from the list
			grid.removeCell(cell.getPos_x(),cell.getPos_y(), i);
			listCell.remove(i);
			
			//Disable the mutation so its child can not mutate yet
			cell.setCanMut(false);
			
			//Get a number between 0 and 2 for the x and y position
			newX = x.nextInt(h-l) + l;
			newY = y.nextInt(h-l) + l;
			
			//Change the range of these numbers from "0 to 2" to "-1 to 1" 
			newMappedX = map(newX, l, h, -1, 1);
			newMappedY = map(newY, l, h, -1, 1);
			
			//Clone the current cell to two new child
			Cell newCell = (Cell) cell.clone();
			Cell newCell2 = (Cell) cell.clone();
			
			//Set the characteristic of the new cells
			newCell.setPos_x(cell.getPos_x()+newMappedX);
			newCell.setPos_y(cell.getPos_y()+newMappedY);
			newCell.setCanMut(false);
			newCell.setAge(0);
			
			newCell2.setPos_x(cell.getPos_x()-newMappedX);
			newCell2.setPos_y(cell.getPos_y()-newMappedY);
			newCell2.setCanMut(false);
			newCell2.setAge(0);
			
			//Collision newCell 1
			collision1 = checkCollision(newCell);
			
			//Collision newCell 2
			collision2 = checkCollision(newCell2);
			
			if(collision1) {
				
				//Draw the new Cell
				grid.fillCell(newCell.getPos_x(), newCell.getPos_y());
				
				//Add the child to the list
				listCell.add(newCell);			
				
			}
			
			if(collision2 == true) {
				
				//Draw the new Cell
				grid.fillCell(newCell2.getPos_x(), newCell2.getPos_y());
				
				//Add the child to the list
				listCell.add(newCell2);
				
			}

			//Clean the garbage
			cell = null;
			System.gc();
		}
	}
	
	private static boolean checkCollision(Cell newCell) {
	
		for( int i = 0 ; i < listCell.size() ; i++) {
						
			if( 	
					(newCell.getPos_x() == listCell.get(i).getPos_x() + 1 && newCell.getPos_y() == listCell.get(i).getPos_y() + 1)
				||  (newCell.getPos_x() == listCell.get(i).getPos_x() + 1 && newCell.getPos_y() == listCell.get(i).getPos_y() - 1)
				||  (newCell.getPos_x() == listCell.get(i).getPos_x() - 1 && newCell.getPos_y() == listCell.get(i).getPos_y() + 1)
				||  (newCell.getPos_x() == listCell.get(i).getPos_x() - 1 && newCell.getPos_y() == listCell.get(i).getPos_y() - 1)
				||  (newCell.getPos_x() == listCell.get(i).getPos_x() && newCell.getPos_y() == listCell.get(i).getPos_y() + 1)
				||  (newCell.getPos_x() == listCell.get(i).getPos_x() && newCell.getPos_y() == listCell.get(i).getPos_y() - 1)
				||  (newCell.getPos_x() == listCell.get(i).getPos_x() + 1 && newCell.getPos_y() == listCell.get(i).getPos_y())
				||  (newCell.getPos_x() == listCell.get(i).getPos_x() - 1 && newCell.getPos_y() == listCell.get(i).getPos_y())
				||  (newCell.getPos_x() == listCell.get(i).getPos_x() && newCell.getPos_y() == listCell.get(i).getPos_y())
				||  (newCell.getPos_x() < 0 )
				||  (newCell.getPos_y() < 0 )
				||  (newCell.getPos_x() > 79 )
				||  (newCell.getPos_y() > 49 ) 
			
			){
				return false;
			}
			
		}
		
		return true;
	}

	//Change the range of a value
	private static int map(int x, int in_min, int in_max, int out_min, int out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}