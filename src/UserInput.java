import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class UserInput implements KeyListener {

	private Ship mShip;
	private boolean mInputs[];



	private static boolean s_isPressed;
	private static int s_keyHeldCode;

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
			s_isPressed = true;
			s_keyHeldCode = KeyEvent.VK_D;
			System.out.println("Rotate Right");
			break;
		case KeyEvent.VK_A:
			mInputs[KeyEvent.VK_A] = true;
			s_isPressed = true;
			s_keyHeldCode = KeyEvent.VK_A;
			System.out.println("Rotate Left");
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
			s_isPressed = false;
			break;
		case KeyEvent.VK_A:
			mInputs[KeyEvent.VK_A] = false;
			s_isPressed = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void Update() {
		if (mInputs[KeyEvent.VK_W]) {
			mShip.Move(0, -1);
		}
		if (mInputs[KeyEvent.VK_S]) {
			mShip.Move(0, 1);
		}
	

	}

	public static boolean isPressed() {
		return s_isPressed;
	}

	public static void setisPressed(boolean s_isPressed) {
		UserInput.s_isPressed = s_isPressed;
	}

	public static int getkeyHeldCode() {
		return s_keyHeldCode;
	}

	public static void setkeyHeldCode(int s_keyHeldCode) {
		UserInput.s_keyHeldCode = s_keyHeldCode;
	}

}
