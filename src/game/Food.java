package game;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Food extends Point {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void CreateFood() {
	}
	//蛇是否吃到食物
		public boolean isEatbysnake(Snake snake,GamePanel gamePanel) {
			Point head =snake.getHead();
			if(this.equals(head)){
				gamePanel.score+=10;
				return true;
				}
			return false;
		}
		public void draw(Graphics g) {
			g.setColor(Color.blue);
			g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE,
					Global.CELL_SIZE, Global.CELL_SIZE, true);
		}
		
		public void addfood(Point p) {
			this.x=p.x;
			this.y=p.y;
		}
	
}

