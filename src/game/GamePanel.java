package game;



import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
//²Ù×÷½çÃæ
public class GamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int score=0;
	private Snake snake;   
	private Food food;
	private Ground ground;
	
	public void display(Snake snake,Food food ,Ground ground) {
		this.snake=snake;
		this.food=food;
		this.ground=ground;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(snake!=null&&food!=null&&ground!=null) {
			snake.draw(g);
			food.draw(g);
			ground.createGround(g);
		}
		g.setFont(new Font("",Font.BOLD,30));
		g.drawString("score:"+score,15,100);
		
	}
		
	
	

}

