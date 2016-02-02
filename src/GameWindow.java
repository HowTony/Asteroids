import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	public static final int CANVAS_WIDTH  = 640;
	public static final int CANVAS_HEIGHT = 480;	
   
	private DrawCanvas mCanvas;
	private GameLogic mGameLogic;
	
	public GameWindow() {
		mCanvas = new DrawCanvas();
		mCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		Container cp = getContentPane();
		cp.add(mCanvas);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setTitle("Asteroids");
		this.setVisible(true);	
	}
	
	public void Paint(){
		mCanvas.repaint();
	}
	
	public void SetGameLogic(GameLogic a){
		mGameLogic = a;
	}
	
	private class DrawCanvas extends JPanel {
		// Override paintComponent to perform your own painting
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);     // paint parent's background
			setBackground(Color.BLACK);  // set background color for this JPanel
			
			if(mGameLogic != null){
				mGameLogic.Draw(g);
			}
		}	
	}
}
