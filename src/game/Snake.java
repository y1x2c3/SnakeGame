package game;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    boolean life=true;
	LinkedList<Point> body=new LinkedList<Point>(); //���������ߵ�����ڵ�
	private SnakeListener snakeListener;
	public static final int UP=1;
	public static final int DOWN=-1;
	public static final int LEFT=3;
	public static final int RIGHT=-3;
	public int olddirection,newdirection;
	
	//public int direction; //�洢��ǰ����
	public Point tail;//�洢β��
	
	
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
	//�ƶ�
	public void Move() {
		//ȥβ
		tail=body.removeLast();
		//��õ�ǰͷ��
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
	
	//��ʳ��
	public void EatFood(Food food) {
		body.addLast(tail);
	}
	
	//�ı䷽��
	public void changeDirection(int direction) {
		this.newdirection=direction;
	}
	//������
	public void draw(Graphics g) {
	
		for(Point p:body) {
			 if(p.equals(body.getFirst())){
				 //������ͷ��ʱ�򣬾�������ɫΪ��ɫ
				 g.setColor(Color.red);
				 }else{
				 g.setColor(Color.orange);
				 }

			g.fill3DRect(p.x*Global.CELL_SIZE, p.y
					*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE,
					true);
		}
		
		
	}
	
	//�Ƿ�Ե��Լ�
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
	//��ȡ��ͷ
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
