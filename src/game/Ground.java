package game;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Ground {
	private int[][]roks = new int[Global.WIDTH][Global.HEIGHT];
	
	public Ground(){
	for(int y=0; y<Global.HEIGHT;y++) {
		for(int x=0;x<Global.WIDTH;x++) {
			if(y==7&&x<Global.WIDTH-30||x==30&&y>Global.HEIGHT-10)
				roks[y][x]=1;
			if((x==0||x==Global.WIDTH-1)||(y==0||y==Global.HEIGHT-1)||(y==10&&x>=Global.WIDTH-10))
				roks[y][x]=1;
			if((y==11&&x==30)||(y==12&&x==29)||(y==13&&x==28))
				roks[y][x]=1;
		 }

		}
	}
	
	
	//蛇是否碰到障碍物
	public boolean isEatbysnake(Snake sanke,GamePanel gamePanel) {
		Point head = sanke.getHead();
		for(int y=0;y<Global.HEIGHT;y++) {
			for(int x=0;x<Global.WIDTH;x++) {
				if(roks[y][x]==1&&head.y==y&&head.x==x){
					gamePanel.score=0;
					return true;
				}
					
					
					
			}
		}
		return false;
	}
//创建障碍物
	public void createGround(Graphics g) {
		g.setColor(Color.black);
for(int y=0; y<Global.HEIGHT;y++) {
	for(int x=0;x<Global.WIDTH;x++) {
		if(roks[y][x]==1)
			g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE,
				Global.CELL_SIZE, Global.CELL_SIZE, true);
					
					
			}
		}				
	}
	public Point getPoint() {
		int x,y;
		do {
   	 x=new Random().nextInt(Global.WIDTH-1);
   	 y=new Random().nextInt(Global.HEIGHT-1);
   	
    }while(roks[x][y]==1);
		 return new Point(x,y);	
}
	
}