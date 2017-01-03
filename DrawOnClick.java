import java.awt.*

;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * Creates an Image of a hockey rink to drop dots on representing goals 
 * Right net is opponenet
 * left is Vermont
 * Know bugs:color options dont show until mouse rolls over
 * 
 * @author depew
 *
 */
public class DrawOnClick extends JFrame implements  MouseListener {
	private int x;
	private int y;
	JPanel control = new JPanel(new BorderLayout());
	private BufferedImage image;
	private DrawOnClick frame;
	Graphics g; 
	private int selected;
	final JRadioButton red= new JRadioButton("red (Uncontested)");
	final JRadioButton blue= new JRadioButton("blue (tip)");
	final JRadioButton green= new JRadioButton("green (rebound)");
	final JRadioButton black= new JRadioButton("black (screen)");
/**
 * method that creates JFrame 
 * imports image to be painted in createImg method 
 * draws Jbuttons for colors 
 * adds mouselistener to get x and y coordinates to drop points
 * @throws IOException
 */
 public DrawOnClick() throws IOException{
	  setSize(1500,800);
	  addMouseListener(this);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setLayout(new BorderLayout());
	  image = ImageIO.read(new File("C:/Users/depew/Documents/HockeyDraw/rink.jpg"));
	  setTitle("GOALS 2016-17 SEASON");
	  setVisible(true);
	  paintComponents(g);
	  }
/**
 * calls main method to set up Jframe 
 * paint image
 * uses event Queque to get mouse clicks 
 * when mouse is clicked run is invoked and Jframe is repainted
 * @param args
 */
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
              public void run() {
            	  
                   DrawOnClick frame;
                   
				try {
					frame = new DrawOnClick();
					frame.setVisible(true);
					frame.createImg();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
                   
              }
        });
    }
    /** 
     * takes in the x and y coordinates by mouse listener so the drawOval knows where to draw
     * finds out color by selected to fill oval
     * @param x
     * @param y
     */
   
    public void drawCircle(int x, int y) {
    	selected = setColor();
    	g.drawOval(x, y, 10, 10);
        if(selected == 3){
        	g.setColor(Color.GREEN);
        	}
        else if(selected == 1){
        	g.setColor(Color.RED);
        	}
        else if(selected == 2){
            g.setColor(Color.BLUE);
            }
        else if(selected == 4){
            g.setColor(Color.BLACK);
            }
        else{ 
        	g.setColor(Color.WHITE);
        }
        g.fillOval(x, y, 10, 10);
       
    }
    public void createImg(){
    	g = this.getGraphics();
  	  	Graphics g2d = image.createGraphics();
        g.drawImage(image, 100, 100, null);
    }

    public void mouseClicked(MouseEvent e) {
        x= e.getX();
        y=e.getY();
        repaint();
    }
    @Override
    public void paint(Graphics g) {
        drawCircle(x, y);
    }
    @Override 
    public void paintComponents(Graphics g){
    
      control.add(buttons());
  	  getContentPane().add(control);
  	  repaint();
    }
    public JPanel buttons(){
      ButtonGroup colors = new ButtonGroup();
      colors.add(red);
      colors.add(blue);
      colors.add(green);
      colors.add(black);
      JPanel button = new JPanel();
  	  button.add(red);
  	  button.add(blue);
  	  button.add(green);
  	  button.add(black);
  	  return button;
    }
    public int setColor(){
    	if(red.isSelected()){
    		return 1;
    	}
    	if(blue.isSelected()){
    		return 2;
    	}
    	if(black.isSelected()){
    		return 4;
    	}
    	if(green.isSelected()){
    		return 3;
    	}
    	else{
    		return 5;
    		}
    	
    }

    	
    

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }
 
}