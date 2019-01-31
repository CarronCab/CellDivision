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
	public static Vector<Cell> deadCell = new Vector<Cell>();
	
	//Speed parameters
	static final int S_MIN = 50;
	static final int S_MAX = 2000;
	static final int S_INIT = 1000;
	
	//Temperature parameters
	static final int T_MIN = -50;
	static final int T_MAX = 100;
	static final int T_INIT = 37;
	
	//Temperature parameters
	static final int N_MIN = 0;
	static final int N_MAX = 100;
	static final int N_INIT = 50;
	
	public static boolean running = true;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public static void main(String[] args) throws CloneNotSupportedException {
		

		//Creation of the window
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL,
                S_MIN, S_MAX, S_INIT);
		
		JSlider temperatureSlider = new JSlider(JSlider.HORIZONTAL,
                T_MIN, T_MAX, T_INIT);
		
		JSlider nutrimentSlider = new JSlider(JSlider.HORIZONTAL,
                N_MIN, N_MAX, N_INIT);
		
		JTextArea sizeOfTheList = new JTextArea();
		JTextArea generationAge = new JTextArea();
		
		JTextArea nutriment = new JTextArea();
		JTextArea temperature = new JTextArea();
		
		JButton cleanButton = new JButton();
		Grid grid = new Grid();
		JPanel panel = new JPanel();
        Window window = new Window();
        
        
        GridLayout experimentLayout = new GridLayout(3,3, 10, 10);
        
        panel.setLayout(experimentLayout);
        //window.setLayout(new BorderLayout(10,10));
        
        cleanButton.setText("Kill All");
        
        nutriment.setEditable(true);
        nutriment.setText("Nutriment : " );
        nutriment.setBackground(null);
        
        temperature.setEditable(true);
        temperature.setText("Temperature : " );
        temperature.setBackground(null);
        
        generationAge.setEditable(true);
        generationAge.setText("Generation 0" );
        generationAge.setBackground(null);
        
      
        sizeOfTheList.setEditable(true);
        sizeOfTheList.setText("Size :" );
        sizeOfTheList.setBackground(null);
        
     
        speedSlider.setPaintLabels(true);
        
      //Create the label table
        Hashtable labelTable = new Hashtable();
        labelTable.put( new Integer( 0 ), new JLabel("Stop") );
        labelTable.put( new Integer( 50 ), new JLabel("Slow") );
        labelTable.put( new Integer( S_MAX ), new JLabel("Fast") );
        speedSlider.setLabelTable( labelTable );
        
        //Turn on labels at major tick marks.
        temperatureSlider.setMajorTickSpacing(20);
        temperatureSlider.setMinorTickSpacing(2);
        temperatureSlider.setPaintTicks(true);
        temperatureSlider.setPaintLabels(true);
        
      //Create the label table
        Hashtable labelTable2 = new Hashtable();
        labelTable2.put( new Integer( 0 ), new JLabel("0°C") );
        labelTable2.put( new Integer( 37 ), new JLabel("37°C") );
        labelTable2.put( new Integer( -45 ), new JLabel("-50°C") );
        labelTable2.put( new Integer( T_MAX - 5 ), new JLabel("100°C") );
        temperatureSlider.setLabelTable( labelTable2 );
        
        //Turn on labels at major tick marks.
        nutrimentSlider.setMajorTickSpacing(10);
        nutrimentSlider.setMinorTickSpacing(1);
        nutrimentSlider.setPaintTicks(true);
        nutrimentSlider.setPaintLabels(true);
        
      //Create the label table
        Hashtable labelTable3 = new Hashtable();
        labelTable3.put( new Integer( 0 ), new JLabel("0%") );
        labelTable3.put( new Integer( 100 ), new JLabel("100%") );
        labelTable3.put( new Integer( 50 ), new JLabel("50%") );
        nutrimentSlider.setLabelTable( labelTable3 );
    
        window.add(grid,BorderLayout.CENTER);
        window.add(panel, BorderLayout.PAGE_END);  
        
        panel.add(sizeOfTheList);
        panel.add(generationAge);
        panel.add(cleanButton);
        panel.add(temperatureSlider);
        panel.add(nutrimentSlider);
        panel.add(speedSlider);
        panel.add(temperature);
        panel.add(nutriment);
                      
        //Randomization of the mitosis
		Random r = new Random();
		int low = 0;
		int high = 100;
		int result;
		
        //Randomization of the first x
		Random x = new Random();
		int x_low = 20;
		int x_high = 50;
		int xresult;
		
        //Randomization of the first y
		Random y = new Random();
		int yresult;
		
		xresult = x.nextInt(x_high-x_low) + x_low;
		yresult = y.nextInt(x_high-x_low) + x_low;
			
		//Generation of the cell population set to 0
		int generation = 0;

		//Creation of the mother cell
		Cell firstCell = new Cell();
		firstCell.setPos_x(xresult);
		firstCell.setPos_y(yresult);
		listCell.add(firstCell);
		firstCell.setCanMut(false);
			
		//Display the first cell
		grid.fillCell(firstCell.getPos_x(), firstCell.getPos_y());
				
		while (running) {
			
			cleanButton.addActionListener(new ActionListener(){  
				    public void actionPerformed(ActionEvent e){  
				           
				    	temperatureSlider.setValue(100);
				    }  
				    });  
					
			//Display the generation
			//System.out.printf("Generation " + generation + "\n");
			//System.out.print(temperatureSlider.getValue());
			
			temperature.setText("Temperature : " + temperatureSlider.getValue() + "°C");
			nutriment.setText("Nutriment : " + nutrimentSlider.getValue() + "%");
			
			if(listCell.size() > 0) {
				
				//Check every cell on the list
				for(int i = 0 ; i < listCell.size() ; i++) {
					
					//Ask a random value between 0 and 100
					result = r.nextInt(high-low) + low;
					
					listCell.get(i).setProbOfMut(nutrimentSlider.getValue());
				
					while(temperatureSlider.getValue() == -50) {
						
						try {
							
							TimeUnit.MILLISECONDS.sleep(1000);
							
						} catch (InterruptedException e) {

							
							e.printStackTrace();
						}
						
						generationAge.setText("Generation " + generation);
						generation++;
						
					}
					
					//If tempature is equal to 100, delete the cell
					if(temperatureSlider.getValue() == 100 || listCell.size() == 0) {
						
						running = false;
						break;
					
					}
					
						//Comparing the probability of the cell to do a mitosis with the random value
				
						//System.out.println(listCell.get(i).getPos_x());
						if(listCell.get(i).getProbOfMut() >= result)  {
								
							//Autozize the mutation
							listCell.get(i).setCanMut(true);
								
						}
						
						
						//Set the new probability in function of the nutriments
						//listCell.get(i).setProbOfMut(nutrimentSlider.getValue());
						
						//Display some characteristic of the cell
						//listCell.get(i).Display(i);
						
						//Age of the cell plus one
						listCell.get(i).happyBirthday();
						
						//Cell die after 3 generation
						if(listCell.get(i).ripCell()) {
							
							//Display a dead cell (black square) and remove the cell form the list
							grid.deadCell(listCell.get(i).getPos_x(),listCell.get(i).getPos_y());
							grid.removeCell(i);
							deadCell.add(listCell.get(i));
							listCell.remove(i);
						
						} else {
							
							//Ask for mitosis
							try {
								
								if(temperatureSlider.getValue() != 100 || generation > 0) {
									grid = mitosis(listCell.get(i), grid,i);
								}
								
								
							} catch (Exception e) {
								
								e.printStackTrace();
						}
					}
	
				}
				
				//Display, increment generation and go to the next one
				generationAge.setText("Generation " + generation);
				generation++;
				
			}
			
			//Display the population size at this generation
			//System.out.printf("size = %d\n\n" , listCell.size());
			sizeOfTheList.setText("Size : " + listCell.size());
			
			if(temperatureSlider.getValue() < 39) {
				
				//Wait some seconds
				try {
					
					TimeUnit.MILLISECONDS.sleep(40-temperatureSlider.getValue() * 15);
					
				} catch (InterruptedException e) {
	
					
					e.printStackTrace();
				}
			}else if (temperatureSlider.getValue() >= 39 && temperatureSlider.getValue() != 100){
				
					try {
					
					TimeUnit.MILLISECONDS.sleep(temperatureSlider.getValue() * 7);
					
				} catch (InterruptedException e) {
	
					
					e.printStackTrace();
				}
					
			} 
			
			//Wait some seconds
			try {
				
				TimeUnit.MILLISECONDS.sleep(2250 - speedSlider.getValue());
				
			} catch (InterruptedException e) {

				
				e.printStackTrace();
			}	
		}
					
		for(int i = 0 ; i < listCell.size() ; i++) {
			grid.deadCell(listCell.get(i).getPos_x(),listCell.get(i).getPos_y());
			listCell.get(i).setCanMut(false);
			listCell.remove(listCell.get(i));
			
			//Wait some seconds
			try {
				
				TimeUnit.MILLISECONDS.sleep(500);
				
			} catch (InterruptedException e) {

				
				e.printStackTrace();
			}
		}
		
		listCell.clear();
		deadCell.clear();
		
		sizeOfTheList.setText("Size : " + listCell.size());
		
	}

	private static Grid mitosis(Cell cell, Grid grid, int i) {
		
		//Randomization of the cells spawns
		Random x = new Random();
		Random y = new Random();
		int l = 0;
		int h = 2;
		int newX;
		int newY;
		int newMappedX;
		int newMappedY;
		
		boolean collision1, collision2, deadCell1, deadCell2;
		
		//Do the mitosis if the cell can mutate
		if(cell.canMut() == true) {
			
			//Remove the current cell from the list
			grid.removeCell(i);
			deadCell.add(listCell.get(i));
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
			deadCell1 = checkDeadCell(newCell);
			
			//Collision newCell 2
			collision2 = checkCollision(newCell2);
			deadCell2 = checkDeadCell(newCell2);
			
			if(collision1 || deadCell1) {
				
				//Draw the new Cell
				grid.fillCell(newCell.getPos_x(), newCell.getPos_y());
				
				//Add the child to the list
				listCell.add(listCell.size(), newCell);			
				
			}
			
			if(collision2 == true || deadCell2) {
				
				//Draw the new Cell
				grid.fillCell(newCell2.getPos_x(), newCell2.getPos_y());
				
				//Add the child to the list
				listCell.add(listCell.size(), newCell2);	
				
			}

			//Clean the garbage
			//cell = null;
			System.gc();
		}
		return grid;
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
	
	private static boolean checkDeadCell(Cell newCell) {
		
		for( int i = 0; i < deadCell.size(); i++) {
			
			if(
					(newCell.getPos_x() == deadCell.get(i).getPos_x() + 1 && newCell.getPos_y() == deadCell.get(i).getPos_y() + 1)
					||  (newCell.getPos_x() == deadCell.get(i).getPos_x() + 1 && newCell.getPos_y() == deadCell.get(i).getPos_y() - 1)
					||  (newCell.getPos_x() == deadCell.get(i).getPos_x() - 1 && newCell.getPos_y() == deadCell.get(i).getPos_y() + 1)
					||  (newCell.getPos_x() == deadCell.get(i).getPos_x() - 1 && newCell.getPos_y() == deadCell.get(i).getPos_y() - 1)
					||  (newCell.getPos_x() == deadCell.get(i).getPos_x() && newCell.getPos_y() == deadCell.get(i).getPos_y() + 1)
					||  (newCell.getPos_x() == deadCell.get(i).getPos_x() && newCell.getPos_y() == deadCell.get(i).getPos_y() - 1)
					||  (newCell.getPos_x() == deadCell.get(i).getPos_x() + 1 && newCell.getPos_y() == deadCell.get(i).getPos_y())
					||  (newCell.getPos_x() == deadCell.get(i).getPos_x() - 1 && newCell.getPos_y() == deadCell.get(i).getPos_y())
					||  (newCell.getPos_x() == deadCell.get(i).getPos_x() && newCell.getPos_y() == deadCell.get(i).getPos_y())	
					
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