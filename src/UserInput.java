import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInput implements KeyListener {

	private Ship mShip;
	private boolean mInputs[];

	public UserInput(Ship ship) {
		mShip = ship;
		 mInputs = new boolean[128];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W:
			
			mInputs[KeyEvent.VK_W] = true;
			break;
		case KeyEvent.VK_S:
			mInputs[KeyEvent.VK_S] = true;
			break;
		case KeyEvent.VK_D:
			mInputs[KeyEvent.VK_D] = true;
			break;
		case KeyEvent.VK_A:
			mInputs[KeyEvent.VK_A] = true;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W:
			mInputs[KeyEvent.VK_W] = false;
			break;
		case KeyEvent.VK_S:
			mInputs[KeyEvent.VK_S] = false;
			break;
		case KeyEvent.VK_D:
			mInputs[KeyEvent.VK_D] = false;
			break;
		case KeyEvent.VK_A:
			mInputs[KeyEvent.VK_A] = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void Update(){
		if(mInputs[KeyEvent.VK_W]){
			mShip.Move(0, -1);
		}
		if(mInputs[KeyEvent.VK_S]){
			mShip.Move(0, 1);
		}
		if(mInputs[KeyEvent.VK_D]){
			mShip.Move(1, 0);
		}
		if(mInputs[KeyEvent.VK_A]){
			mShip.Move(-1, 0);
		}
		
	}

}
