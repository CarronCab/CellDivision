import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	//List of cells
	public static Vector<Cell> listCell = new Vector();
	public static int speed = 50;
	
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		//Generation of the cell population set to 0
		int generation = 0;

		//Creation of the mother cell
		Cell firstCell = new Cell();
		listCell.add(firstCell);
		firstCell.setCanMut(false);
		
		//Creation of the window
		Grid grid = new Grid();
        Window window = new Window();
        window.add(grid);
        
         //Display the first cell
         grid.fillCell(firstCell.getPos_x(), firstCell.getPos_y());
    
         
        //Randomization of the mitosis
		Random r = new Random();
		int low = 0;
		int high = 100;
		int result;
		
		while (true) {
					
			//Display the generation
			System.out.printf("Generation " + generation + "\n");
			
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
						listCell.get(i).Display(i);
						
						//Age of the cell plus one
						listCell.get(i).happyBirthday();
					}
	
				}
				
			}
			
			//Display the population size at this generation
			System.out.printf("size = %d\n\n" , listCell.size());
			
			//Wait some seconds
			try {
				
				TimeUnit.MILLISECONDS.sleep(speed);
				
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
}
