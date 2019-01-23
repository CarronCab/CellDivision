import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

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
				System.out.println(result);
				
				//Display some characteristic of the cell
				System.out.printf("cell : " + i + "\n");
				System.out.printf(" x =  " +  listCell.get(i).getPos_x() + "\n");
				System.out.printf(" y =  " +  listCell.get(i).getPos_y() + "\n");
				System.out.printf("Can Mut : " + listCell.get(i).canMut() + "\n");
					
				//Check for a mitosis
				try {
					mitosis(listCell.get(i));
					listCell.get(i).setCanMut(false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Comparing the probability of the cell to do a mitosis with the random value
				if(listCell.get(i).getProbOfMut() > result) {
					
					//Autozize the mutation
					listCell.get(i).setCanMut(true);
				}

			}
			
			//Display the population size at this generation
			System.out.printf("size = %d\n\n" , listCell.size());
			
			//Wait some seconds
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Increment generation and go to the next one
			generation++;
		
		}

		
		
		/*
		Cell firstCell = new Cell();
		Cell secondCell = new Cell();

		listCell.add(firstCell);
		listCell.add(secondCell);

		listCell.get(0).getX();
		
		System.out.println(firstCell);
		System.out.println(secondCell);
		System.out.println(listCell);
		
		Window myWindow = new Window();
		myWindow.setVisible(true);
		myWindow.setTitle("Cell Division");
		*/

	}

	private static void mitosis(Cell cell) {
		
		//Randomization of the cells spawns
		Random x = new Random();
		Random y = new Random();
		int l = 0;
		int h = 2;
		int newX;
		int newY;
		int newMappedX;
		int newMappedY;
		
		//Do the mitosis if the cell can mutate
		if(cell.canMut() == true) {
			
			//Remove the current cell from the list
			listCell.remove(cell);
			
			//Disable the mutation so its child can not mutate yet
			cell.setCanMut(false);
			
			//Get a number between 0 and 2 for the x and y position
			newX = x.nextInt(h-l) + l;
			newY = y.nextInt(h-l) + l;
			
			//Change the range of these numbers from "0 to 2" to "-1 to 1" 
			newMappedX = map(newX, l, h, -1, 1);
			newMappedY = map(newY, l, h, -1, 1);
			
			//Avoid creating a cell in at the same position as its mother
			if((newMappedX == 0) && (newMappedY == 0)) {
				newMappedY++;
				newMappedX++;
			}
			
			//Clone the current cell to two new child
			Cell newCell = (Cell) cell.clone();
			Cell newCell2 = (Cell) cell.clone();
			
			//Set the characteristic of the new cells 
			newCell.setPos_x(cell.getPos_x()+newMappedX);
			newCell.setPos_y(cell.getPos_y()+newMappedY);
			newCell.setCanMut(false);
			
			newCell2.setPos_x(cell.getPos_x()-newMappedX);
			newCell2.setPos_y(cell.getPos_y()-newMappedY);
			newCell2.setCanMut(false);
			
			//Add the child to the list
			listCell.add(newCell);

			listCell.add(newCell2);
			
			System.gc();
		}
	}
	
	//Change the range of a value
	private static int map(int x, int in_min, int in_max, int out_min, int out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
