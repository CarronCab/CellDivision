import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {
	Cell cell= new Cell();
	@Test
	public void testripCell()throws Exception {

		
		assertFalse(cell.ripCell());
		cell.setAge(4);
		assertTrue(cell.ripCell());
	}
	@Test
	public void testhappyBirthday()throws Exception {
		cell.setAge(5);
		cell.happyBirthday();
		assertEquals(6,cell.getAge());
	}
	@Test
	public void testsetAge()throws Exception {
		cell.setAge(5);
		assertEquals(5,cell.getAge());
	}
	@Test
	public void testgetAge()throws Exception {
		assertEquals(0, cell.getAge());
	}
	@Test
	public void testgetProbOfMut()throws Exception {
		assertEquals(100, cell.getProbOfMut());
	}
	@Test
	public void testsetProbOfMut()throws Exception {
		cell.setProbOfMut(10);
		assertEquals(10, cell.getProbOfMut());
	}
	@Test
	public void testcanMut()throws Exception {
		assertFalse(cell.canMut());
	}
	@Test
	public void testsetcanMut()throws Exception {
		cell.setCanMut(true);
		assertTrue(cell.canMut());
	}
	@Test
	public void testgetPos_x()throws Exception {
		assertEquals(50,cell.getPos_x());
	}
	@Test
	public void testsetPos_x()throws Exception {
		cell.setPos_x(25);
		assertEquals(25,cell.getPos_x());
	}
	@Test
	public void testgetPos_y()throws Exception {
		assertEquals(25,cell.getPos_y());
	}
	@Test
	public void testsetPos_y()throws Exception {
		cell.setPos_y(30);
		assertEquals(30,cell.getPos_y());
	}
	@Test
	public void testclone()throws Exception {
		Cell cloneablecell=(Cell)cell.clone();
		assertEquals(cloneablecell.getAge(),cell.getAge());
		assertEquals(cloneablecell.getPos_x(),cell.getPos_x());
		assertEquals(cloneablecell.getPos_y(),cell.getPos_y());
		assertEquals(cloneablecell.canMut(),cell.canMut());
		assertEquals(cloneablecell.getProbOfMut(),cell.getProbOfMut());
	}
	
	
	


}
