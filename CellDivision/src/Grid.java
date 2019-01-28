import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Grid extends JPanel {

    private List<Point> fillCells;
    private List<Point> deleteCells;

    public Grid() {
        fillCells = new ArrayList<>(25);
        deleteCells = new ArrayList<>(25);
        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(fillCells.size() >= 0) {
            for (int i = 0 ; i < fillCells.size() ; i ++) {
            	
            	
                int cellX = 10 + (fillCells.get(i).x * 10);
                int cellY = 10 + (fillCells.get(i).y * 10);
                g.setColor(Color.RED);
                g.fillRect(cellX, cellY, 10, 10);
                
            }
       
        }
        
        if(deleteCells.size() >= 0) {
            for (int i = 0 ; i < deleteCells.size() ; i ++) {
            	
                int cellX = 10 + (deleteCells.get(i).x * 10);
                int cellY = 10 + (deleteCells.get(i).y * 10);
                g.setColor(Color.BLACK);
                g.fillRect(cellX, cellY, 10, 10);
            }
            
            //After 20 dead cell, we clean the first body so the grid cell becomes spawnable again
            if(deleteCells.size() > 50) {
            	
            	deleteCells.remove(0);
            	
            }
           
        }

        
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 800, 500);

        for (int i = 10; i <= 800; i += 10) {
            g.drawLine(i, 10, i, 510);
        }

        for (int i = 10; i <= 500; i += 10) {
            g.drawLine(10, i, 810, i);
        }
    }

    public void fillCell(int x, int y) {
        fillCells.add(new Point(x, y));
        repaint();
    }
    
    public void removeCell(int x, int y, int i) {
    	fillCells.remove(i);
        repaint();
    	
    }
    
    public void deadCell(int x, int y) {
    	deleteCells.add(new Point(x, y));
        repaint();
    	
    }
    
}
