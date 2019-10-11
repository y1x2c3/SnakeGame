package game;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    boolean life=true;
	LinkedList<Point> body=new LinkedList<Point>(); //用链表保存蛇的身体节点
	private SnakeListener snakeListener;
	public static final int UP=1;
	public static final int DOWN=-1;
	public static final int LEFT=3;
	public static final int RIGHT=-3;
	public int olddirection,newdirection;
	
	//public int direction; //存储当前方向
	public Point tail;//存储尾巴
	
	
  public Snake() {
		Init();
	}
	private void Init() {
		int x=8;
		int y=2;
		for(int i=0; i<3;i++) {
			body.add(new Point(x-i,y));
		}
		this.olddirection=this.newdirection=RIGHT;
		
	}
	//移动
	public void Move() {
		//去尾
		tail=body.removeLast();
		//获得当前头部
		int x=body.getFirst().x;
		int y=body.getFirst().y;
		
		if(this.olddirection+this.newdirection!=0)
			this.olddirection=this.newdirection;
		switch(olddirection) {
		case UP : y--;
		if(y<0)y=Global.HEIGHT-1;
		break;		  
		case DOWN: y++;
		if(y>=Global.HEIGHT) y=0;
		break;
		case LEFT:x--;
		if(x<0) x=Global.WIDTH-1;
		break;
		case RIGHT : x++;
		if(x>=Global.WIDTH) x=0;
		break;
		}
		body.addFirst(new Point(x,y));
	}
	
	//吃食物
	public void EatFood(Food food) {
		body.addLast(tail);
	}
	
	//改变方向
	public void changeDirection(int direction) {
		this.newdirection=direction;
	}
	//画出蛇
	public void draw(Graphics g) {
	
		for(Point p:body) {
			 if(p.equals(body.getFirst())){
				 //当是蛇头的时候，就设置颜色为橘色
				 g.setColor(Color.red);
				 }else{
				 g.setColor(Color.orange);
				 }

			g.fill3DRect(p.x*Global.CELL_SIZE, p.y
					*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE,
					true);
		}
		
		
	}
	
	//是否吃到自己
	public boolean isEatself(GamePanel panel) {
		for(int i=1;i<body.size();i++) {
			if(body.get(i).equals(getHead())) {
				panel.score=0;
				return true;
			}
		}
		return false;
	}
	
	
	public void addSnakeListener(SnakeListener snakeListener) {
		if(snakeListener!=null)
			this.snakeListener=snakeListener;
	}
	
	public void snakeMoved(Snake snake) {
		
	}
	
	
	public void start() {
		new SnakeDriver().start();
		
	}
	//获取蛇头
	public Point getHead() {
		return body.getFirst();
	}
	
	public void setLife(boolean life) {
		this.life=life;
	}
	
	private class SnakeDriver extends Thread{
		public void run() {
			while(life) {
				Move();
				snakeListener.snakeMoved(Snake.this);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	

}
