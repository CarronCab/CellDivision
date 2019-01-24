
public class Cell implements Cloneable {

	private int pos_x;
	private int pos_y;
	private boolean canMut;
	private int probOfMut = 50;
	private int age = 0;
	
	
	
	public Cell(int age) {
		super();
		this.age = age;
	}


	public Cell() {
		
		newPosition();
		
	}
	

	private void newPosition() {
		
		this.setPos_x(10);
		this.setPos_y(10);

	}
	
	
	
	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	} 
	
	public int getProbOfMut() {
		return probOfMut;
	}


	public void setProbOfMut(int probOfMut) {
		this.probOfMut = probOfMut;
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
