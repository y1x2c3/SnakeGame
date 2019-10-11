package game;



import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

//控制器类

public  class Controller extends KeyAdapter implements SnakeListener{
	
	private Snake snake;
    private Food food;
    private Ground ground;
    private GamePanel gamePanel;
   
    public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
		super();
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel =gamePanel;
    }
	


public void keyPressed(KeyEvent e) {
	
	boolean pause=false;
	int keycode= e.getKeyCode();
	switch(keycode)
	{ case KeyEvent.VK_UP:
		snake.changeDirection(Snake.UP);
		break;
	case KeyEvent.VK_DOWN:
		snake.changeDirection(Snake.DOWN);
		break;
	case KeyEvent.VK_LEFT:
		snake.changeDirection(Snake.LEFT);
		break;
	case KeyEvent.VK_RIGHT:
		snake.changeDirection(Snake.RIGHT);
		break;
	case KeyEvent.VK_SPACE:
		if(!pause)//暂停
		  {
		    pause = true;
			snake.setLife(false);
		 }
		 else//开始
			 {
			    pause = false;
			   snake.setLife(true);
			 }
         break;
		}
		 }
	
  

public static final int UP=1;
public static final int DOWN=-1;
public static final int LEFT=3;
public static final int RIGHT=-3;

@Override
public void snakeMoved(Snake snake){
	//显示身体。食物。障碍物
	gamePanel.display(snake, food, ground);
	if(food.isEatbysnake(snake, gamePanel)) {
		snake.EatFood(food);
		food.addfood(ground.getPoint());
	}
	if(ground.isEatbysnake(snake, gamePanel)) {
		snake.setLife(false);
		
		int x=snake.body.getFirst().x;
		int y=snake.body.getFirst().y;
		switch(snake.olddirection) {
		case UP : y++;snake.body.addLast(snake.tail);
		if(y<0)y=Global.HEIGHT-1;
		break;		  
		case DOWN: y--;snake.body.addLast(snake.tail);
		if(y>=Global.HEIGHT) y=0;
		break;
		case LEFT:x++;snake.body.addLast(snake.tail);
		if(x<0) x=Global.WIDTH-1;
		break;
		case RIGHT : x--;snake.body.addLast(snake.tail);
		if(x>=Global.WIDTH) x=0;
		break;
		}
		snake.body.addFirst(new Point(x,y));
		
		int end=JOptionPane.showConfirmDialog(null, "GameOver",null,JOptionPane.YES_NO_OPTION);
		if(end==0){
			System.exit(0);
		}else
		{	
			
		}
	}
	if(snake.isEatself(gamePanel)) {
		snake.setLife(false);
		int x=snake.body.getFirst().x;
		int y=snake.body.getFirst().y;
		switch(snake.olddirection) {
		case UP : y++;snake.body.addLast(snake.tail);
		if(y<0)y=Global.HEIGHT-1;
		break;		  
		case DOWN: y--;snake.body.addLast(snake.tail);
		if(y>=Global.HEIGHT) y=0;
		break;
		case LEFT:x++;snake.body.addLast(snake.tail);
		if(x<0) x=Global.WIDTH-1;
		break;
		case RIGHT : x--;snake.body.addLast(snake.tail);
		if(x>=Global.WIDTH) x=0;
		break;
		}
		snake.body.addFirst(new Point(x,y));
		int result=JOptionPane.showConfirmDialog(null, "GameOver",null, JOptionPane.YES_NO_OPTION);
		if(result==0){
			System.exit(0);
		}else
		{ 
			
		}
	}
}





public void startGame() {
	snake.start();
	food.addfood(ground.getPoint());		
}

@Override
public void snakeMoved() {
	// TODO Auto-generated method stub
	
}

     
}
	
	
