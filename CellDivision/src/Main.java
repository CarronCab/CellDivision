import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;


public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	//List of cells
	public static Vector<Cell> listCell = new Vector();
	
	
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		//Generation of the cell population set to 0
		int generation = 0;

		//Creation of the mother cell
		Cell firstCell = new Cell();
		listCell.add(firstCell);
		firstCell.setCanMut(false);
		
		//Creation of the window
		 Grid grid = new Grid();
         JFrame window = new JFrame();
         window.setSize(840, 560);
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.add(grid);
         window.setVisible(true);
         
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
			

			//Check every cell on the list
			for(int i = 0 ; i < listCell.size() ; i++) {
				
				//Ask a random value between 0 and 100
				result = r.nextInt(high-low) + low;
				
				//Cell die after 10 generation
				if(listCell.get(i).getAge() > 3) {
					
					grid.deadCell(listCell.get(i).getPos_x(),listCell.get(i).getPos_y());
					listCell.remove(i);
					
					
					break;
				}
				
				//Ask for mitosis
				try {
					mitosis(listCell.get(i), grid,i);
					listCell.get(i).setCanMut(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Comparing the probability of the cell to do a mitosis with the random value
				if(listCell.get(i).getProbOfMut() > result)  {
										

					//Autozize the mutation
					listCell.get(i).setCanMut(true);
					
				}
				
				//Display some characteristic of the cell
				System.out.println(result);
				System.out.printf("cell : " + i + "\n");
				System.out.printf(" x =  " +  listCell.get(i).getPos_x() + "\n");
				System.out.printf(" y =  " +  listCell.get(i).getPos_y() + "\n");
				System.out.printf("Can Mut : " + listCell.get(i).canMut() + "\n");
				System.out.printf("Age : " + listCell.get(i).getAge() +"\n");

				listCell.get(i).setAge(listCell.get(i).getAge() +1);
				
				
				
				
			}
			
			//Display the population size at this generation
			System.out.printf("size = %d\n\n" , listCell.size());
			
			//Wait some seconds
			try {
				TimeUnit.MILLISECONDS.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
		
		boolean collision;
		
		//Do the mitosis if the cell can mutate
		if(cell.canMut() == true && listCell.size() < 100) {
			
			//Remove the current cell from the list
			grid.removeCell(cell.getPos_x(),cell.getPos_y(), i);
			listCell.remove(cell);
			
			//Disable the mutation so its child can not mutate yet
			cell.setCanMut(false);
			
			//Get a number between 0 and 2 for the x and y position
			newX = x.nextInt(h-l) + l;
			newY = y.nextInt(h-l) + l;
			
			//Change the range of these numbers from "0 to 2" to "-1 to 1" 
			newMappedX = map(newX, l, h, -1, 1);
			newMappedY = map(newY, l, h, -1, 1);
			
			/*
			//Avoid creating a cell in at the same position as its mother
			if((newMappedX == 0) && (newMappedY == 0)) {
				newMappedY++;
				newMappedX++;
			}
			*/
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
			collision = checkCollision(newCell);
			
			if(collision) {
				
				//Draw the new Cell
				grid.fillCell(newCell.getPos_x(), newCell.getPos_y());
				
				//Add the child to the list
				listCell.add(newCell);
				
			}
			
			//Collision newCell 2
			collision = checkCollision(newCell2);
			
			if(collision == true) {
				
				//Draw the new Cell
				grid.fillCell(newCell2.getPos_x(), newCell2.getPos_y());
				
				//Add the child to the list
				listCell.add(newCell2);
			}
			
			

			//Clean the garbage
			System.gc();
		}
	}
	
	private static boolean checkCollision(Cell newCell) {
	
		for( Cell cell: listCell) {
			
			if( 	
					(newCell.getPos_x() == cell.getPos_x() + 1 && newCell.getPos_y() == cell.getPos_y() + 1)
				||  (newCell.getPos_x() == cell.getPos_x() + 1 && newCell.getPos_y() == cell.getPos_y() - 1)
				||  (newCell.getPos_x() == cell.getPos_x() - 1 && newCell.getPos_y() == cell.getPos_y() + 1)
				||  (newCell.getPos_x() == cell.getPos_x() - 1 && newCell.getPos_y() == cell.getPos_y() - 1)
				||  (newCell.getPos_x() == cell.getPos_x() && newCell.getPos_y() == cell.getPos_y() + 1)
				||  (newCell.getPos_x() == cell.getPos_x() && newCell.getPos_y() == cell.getPos_y() - 1)
				||  (newCell.getPos_x() == cell.getPos_x() + 1 && newCell.getPos_y() == cell.getPos_y())
				||  (newCell.getPos_x() == cell.getPos_x() - 1 && newCell.getPos_y() == cell.getPos_y())
				||  (newCell.getPos_x() == cell.getPos_x() && newCell.getPos_y() == cell.getPos_y())
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
