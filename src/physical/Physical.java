package physical;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import main.MainFrame;
import result.ResultPanel;
import score.ScorePanel;

public class Physical extends JLayeredPane {
	private ImageIcon lifeBar_image;
	private ImageIcon lifeMinus_image;
	private int life;
	private int cnt;
	private List<JLabel> lifeList;
	private JLabel lifeMinus;
	private Thread physicalTimeLow;
	private boolean doseNotDecreaseLife;
	private boolean jellyEat;
	private boolean healing;
	private int jumpStatus;

	public Physical() {
		setLayout(null);
		lifeList = new ArrayList<>();
		doseNotDecreaseLife = false;
		jellyEat = false;
		healing = false;
		life = 515;
		cnt = 0;
		jumpStatus = 0;
		
		lifeMinus_image = new ImageIcon(".\\img\\lifebar\\lifeMinus.png");
		lifeBar_image = new ImageIcon(".\\img\\lifebar\\lifeBar.png");
		
		// x = 540 체력 풀, x = 65 체력 없음
		JLabel lifebar = new JLabel();
		lifebar.setBounds(0, 0, 560, 80);
		lifebar.setIcon(lifeBar_image);
		add(lifebar, new Integer(0));
		setSize(560, 80);
		physicalTimeLow = new Thread(new physicalTimeLows());
		physicalTimeLow.start();
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public boolean isDoseNotDecreaseLife() {
		return doseNotDecreaseLife;
	}

	public void setDoseNotDecreaseLife(boolean doseNotDecreaseLife) {
		this.doseNotDecreaseLife = doseNotDecreaseLife;
	}

	public int getJumpStatus() {
		return jumpStatus;
	}

	public void setJumpStatus(int jumpStatus) {
		this.jumpStatus = jumpStatus;
	}
	
	public boolean isJellyEat() {
		return jellyEat;
	}

	public void setJellyEat(boolean jellyEat) {
		this.jellyEat = jellyEat;
	}

	public boolean isHealing() {
		return healing;
	}

	public void setHealing(boolean healing) {
		this.healing = healing;
	}
	
	public void lifeMinus(int x) {
		if (!(x <= 40)) {
			lifeMinus = new JLabel();
			lifeMinus.setBounds(x, 34, 25, 21);
			lifeMinus.setSize(25, 21);
			lifeMinus.setIcon(lifeMinus_image);
			lifeList.add(lifeMinus);
			add(lifeList.get(cnt), new Integer(1));
			cnt++;
			life -= 25;
		}
	}
	
	public void lifePlus() {
		if (!(cnt <= 0)) {
			cnt--;
			lifeList.get(cnt).remove(lifeMinus);
			add(lifeList.get(cnt), new Integer(1));
			lifeList.remove(cnt);
			life += 25;
		}
	}
	
	private class physicalTimeLows implements Runnable {
		@Override
		public void run() {
			try {
				while (life >= 40) {
					Thread.sleep(4500);
					lifeMinus(life);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
