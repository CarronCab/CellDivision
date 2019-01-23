
public class Cell implements Cloneable {

	private int pos_x;
	private int pos_y;
	private boolean canMut;
	
	
	public Cell() {
		
	
		newPosition();
		
		
	}
	

	private void newPosition() {
		
		this.setPos_x(10);
		this.setPos_y(10);

	}
	

	public boolean canMut() {
		return canMut;
	}

	public void setCanMut(boolean canMut) {
		this.canMut = canMut;
	}

	public int getPos_x() {
		return pos_x;
	}

	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}

	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	
	@Override
	public Object clone() 
	   { 
	       Object obj = null; 
	       try 
	       { 
	       	obj = super.clone(); 
	       }  
	       catch (CloneNotSupportedException e)  
	       { 
	           e.printStackTrace(); 
	       } 
        return obj; 
       } 
}
