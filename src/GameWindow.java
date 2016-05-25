import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameWindow extends JFrame{

	public static GraphicsDevice mDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	public static final int CANVAS_WIDTH  = mDevice.getDisplayMode().getWidth() / 2;
	public static final int CANVAS_HEIGHT = mDevice.getDisplayMode().getHeight() / 2;
   
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
	
	public void RegisterKeyListener(KeyListener k){
		this.addKeyListener(k); 
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
