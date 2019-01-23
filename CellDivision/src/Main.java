import java.util.Vector;
import java.util.concurrent.TimeUnit;





public class Main {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector<Cell> listCell = new Vector();
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		int generation = 0;

		Cell firstCell = new Cell();
		listCell.add(firstCell);
		firstCell.setCanMut(false);		
		
		while (true) {
			
			System.out.printf("Generation " + generation + "\n");
			
			
			
			for(int i = 0 ; i < listCell.size() ; i++) {
				
				
				
				try {
					mitosis(listCell.get(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(listCell.size() > 0) {
					
					System.out.printf("cell : " + i + "\n");
					System.out.printf(" x =  " +  listCell.get(i).getPos_x() + "\n");
					System.out.printf("Can Mut : " + listCell.get(i).canMut() + "\n");
					
					
				}
				
				

			}
			
			System.out.printf("size = %d\n\n" , listCell.size());
			
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
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
		
		if(cell.canMut() == true) {
			
			listCell.remove(cell);
			
			cell.setCanMut(false);
			
			Cell newCell = (Cell) cell.clone();
			Cell newCell2 = (Cell) cell.clone();
			newCell.setPos_x(cell.getPos_x()+1);
			newCell.setPos_y(cell.getPos_y()+1);
			newCell2.setPos_x(cell.getPos_x()-1);
			newCell2.setPos_y(cell.getPos_y()-1);
			
			listCell.add(newCell);

			listCell.add(newCell2);
			
		}
	}
}
