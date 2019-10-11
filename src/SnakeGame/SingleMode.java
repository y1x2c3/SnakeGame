package SnakeGame;




import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SingleMode extends JPanel implements KeyListener,ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon title=new ImageIcon("title.jpg");
	ImageIcon food=new ImageIcon("food.png");
	ImageIcon left=new ImageIcon("left.png");
	ImageIcon right=new ImageIcon("right.png");
	ImageIcon up=new ImageIcon("up.png");
	ImageIcon down=new ImageIcon("down.png");
	ImageIcon body=new ImageIcon("body.png");
	ImageIcon big=new ImageIcon("big.png");
	ImageIcon background=new ImageIcon("jiu.jpg");
	Timer time=new Timer(100,this);	
	int snakex[]=new int[700];
	 int snakey[]=new int[700];
	 int length;
	 int score=0;
	 Random rand=new Random();
	 int foodx=rand.nextInt(34)*25+25;
	 int foody=rand.nextInt(24)*25+85;
	 String direction="R";
	 boolean isstarted=false;
	 boolean isover=false;
	 public SingleMode(){
		this.setFocusable(true);
		this.addKeyListener(this);
		time.start();
		setup();
	}
	public void paint(Graphics g){
		title.paintIcon(this, g, 25, 11);
		 ImageIcon img = new ImageIcon("jiu.jpg");
		  g.drawImage(img.getImage(), 25, 85, null);
		
		g.setFont(new Font("arial", Font.BOLD ,18));
		g.setColor(Color.red);
	
		g.setFont(new Font("arial", Font.BOLD ,30));
	    g.drawString("score:"+score, 100, 50);
	    g.setColor(Color.RED);
	
	if(!isstarted){
	g.setColor(Color.white);
	g.setFont(new Font("arial", Font.BOLD ,30));
	g.drawString("Press space to start", 300, 300);
	
	}
		if(isover){
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD ,50));
			g.drawString("Game over", 350, 300);
			g.drawString("press space to continue", 200, 400);
			
		}
		//初始化蛇头
		if(direction.equals("U")){
			up.paintIcon(this, g, snakex[0], snakey[0]);
		}
        if(direction.equals("R")){
        	right.paintIcon(this, g, snakex[0], snakey[0]);
		}
        if(direction.equals("D")){
        	down.paintIcon(this, g, snakex[0], snakey[0]);
        }
        if(direction.equals("L")){
        	left.paintIcon(this, g, snakex[0], snakey[0]);
        }
        //初始化蛇身
        for(int i=1;i<length;i++){
        	body.paintIcon(this, g, snakex[i], snakey[i]);
        }
        if(score%100==0&&score!=0){
        	big.paintIcon(this, g, foodx, foody);
        }else{
        	food.paintIcon(this, g, foodx, foody);
        }
        
	}
	public void setup(){
		isstarted=false;
		 length=3;
		direction="R";
		snakex[0]=100;
		snakey[0]=110;
		snakex[1]=75;
		snakey[1]=110;
		snakex[2]=50;
		snakey[2]=110;
		
	}
public void keyPressed(KeyEvent e) {
	int KeyCode=e.getKeyCode();
	//游戏开始以后，按S键暂停游戏
	if(KeyCode==KeyEvent.VK_S){
		time.stop();
	}
	//暂停以后想要继续就按C键
	if(KeyCode==KeyEvent.VK_C){
		time.start();
	}
	
	//来个外挂，当蛇太长怎么办，偷偷把蛇身减少，但是分数不减
	if(KeyCode==KeyEvent.VK_L){
		if(length>20)
		length-=3;
	}
	if(KeyCode==KeyEvent.VK_SPACE){
		if(isover){
			setup();
			score=0;
			foodx=rand.nextInt(34)*25+25;
			foody=rand.nextInt(24)*25+85;
			isover=false;
			
		}else{
			isstarted=true;
			
			}
	}
	if(KeyCode==KeyEvent.VK_UP&&direction!="D"){
		direction="U";
	}
if(KeyCode==KeyEvent.VK_DOWN&&direction!="U"){
	direction="D";	
	}
if(KeyCode==KeyEvent.VK_RIGHT&&direction!="L"){
	direction="R";
}
if(KeyCode==KeyEvent.VK_LEFT&&direction!="R"){
	direction="L";
}

	}
public void keyTyped(KeyEvent e) {

	}

public void actionPerformed(ActionEvent e) {
	time.start();
	//如果开始就开始移动
	//移动身体
	if(isstarted&&!isover){
		for(int i=length;i>0;i--){
			snakex[i]=snakex[i-1];
			snakey[i]=snakey[i-1];
		}
		//移动头部
		 if(direction.equals("R")){
			snakex[0]+=25;
			if(snakex[0]>850){
				isover=true;
				snakex[0]-=25;
				for(int i=1;i<length;i++){
					snakex[i]=snakex[i+1];
					snakey[i]=snakey[i+1];
				}
			}
		}
		else if(direction.equals("L")){
			snakex[0]-=25;
			if(snakex[0]<25){
				isover=true;
				snakex[0]+=25;
				for(int i=1;i<length;i++){
					snakex[i]=snakex[i+1];
					snakey[i]=snakey[i+1];
				}
			}
		}
		else if(direction.equals("D")){
			snakey[0]+=25;
			if(snakey[0]>660){
				isover=true;
				snakey[0]-=25;
				for(int i=1;i<length;i++){
					snakex[i]=snakex[i+1];
					snakey[i]=snakey[i+1];
				}
			}
        }
		else if(direction.equals("U")){
			snakey[0]-=25;
			if(snakey[0]<85){
				isover=true;
				snakey[0]+=25;
				for(int i=1;i<length;i++){
					snakex[i]=snakex[i+1];
					snakey[i]=snakey[i+1];
				}
			}
        }
	}
	if(snakex[0]==foodx&&snakey[0]==foody){
		
		foodx=rand.nextInt(34)*25+25;
	    foody=rand.nextInt(24)*25+85;
		length+=1;
		if(score%100==0&&score!=0){
			score+=40;
		}else{
			score+=20;
		}
		
		
		
	}
	for(int i=1;i<length;i++){
		if(snakex[0]==snakex[i]&&snakey[0]==snakey[i]){
			isover=true;
		}
	}
	repaint();
}

	public void keyReleased(KeyEvent e) {
		
	}	
}
