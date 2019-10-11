package SnakeGame;




import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;



public class DoubleMode extends JPanel implements KeyListener,ActionListener {
	
	 private static final long serialVersionUID = 1L;
	 /*
	  * 将所有要用到的图片添加进来
	  */
	 ImageIcon title=new ImageIcon("title.jpg");
	 ImageIcon food=new ImageIcon("food.png");
	 ImageIcon left=new ImageIcon("left.png");
	 ImageIcon left1=new ImageIcon("left1.png");
	 ImageIcon right=new ImageIcon("right.png");
	 ImageIcon right1=new ImageIcon("right1.png");
	 ImageIcon up=new ImageIcon("up.png");
	 ImageIcon up1=new ImageIcon("up1.png");
	 ImageIcon down=new ImageIcon("down.png");
	 ImageIcon down1=new ImageIcon("down1.png");
	 ImageIcon body=new ImageIcon("body.png");
	 ImageIcon hushe=new ImageIcon("hushe.png");
	 ImageIcon big=new ImageIcon("big.png");
	 ImageIcon background=new ImageIcon("jiu.jpg");
	 
	 
	 Timer time=new Timer(100,this);	
	 /*
	  * 初始化蛇的长度以及初始状态下的分数
	  */
	 int snakex[]=new int[700];
	 int snakey[]=new int[700];
	 int snakex1[]=new int[700];
	 int snakey1[]=new int[700];
	 int length;
	 int length1;
	 int score=0;
	 int score1=0;
	 int a=0;
	 /*
	  * 产生随机数字，让食物随机产生在一个长850，高600的长方形里面。
	  */
	 Random rand=new Random();
	 int foodx=rand.nextInt(34)*25+25;
	 int foody=rand.nextInt(24)*25+85;
	 String direction="R";
	 String direction1="L";                       //游戏开始时，默认蛇的方向
	 boolean isstarted=false;
	 boolean isstarted1=false;
	 boolean isover=false;
	 boolean isover1=false;                      //判断蛇是否开始是否结束，初始化都为false
	 public DoubleMode(){
		this.setFocusable(true);
		this.addKeyListener(this);
		time.start();
		setup();
	}
    /*
     * 既然继承了JPanel类，那么里面就有一个方法，可以传一个画笔，在panel里面操作
     */
	public void paint(Graphics g){
		/*
		 * 初始化游戏界面
		 */
		  title.paintIcon(this, g, 25, 11);
		  ImageIcon img = new ImageIcon("jiu.jpg");
		  g.drawImage(img.getImage(), 25, 85, null);
		  g.setColor(Color.red);
		  g.setFont(new Font("arial", Font.BOLD ,30));
	      g.drawString("score:"+score, 200, 50);
	      g.drawString("score:"+score1, 600, 50);
	    /*
	     * 如果游戏还没有开始，那么就在页面提示开始按钮，如果游戏结束，就要显示获胜的玩家
	     */
	    if(!isstarted&&!isstarted1){
	      g.setColor(Color.white);
	      g.setFont(new Font("arial", Font.BOLD ,30));
	      g.drawString("Press space to start", 300, 300);
	
	     }
	   
	    if(isover==true){
	    	g.setColor(Color.red);
	    	g.setFont(new Font("arial", Font.BOLD ,40));
		    g.drawString("player2 win", 400, 400);
		   time.stop();
		    
	    }	
	    if(isover1==true){
	    	g.setColor(Color.red);
	    	g.setFont(new Font("arial", Font.BOLD ,40));
		    g.drawString("player1 win", 400, 400);
		    time.stop();
	    }
	    if(a==1){
	    	g.setColor(Color.red);
	    	g.setFont(new Font("arial", Font.BOLD ,40));
		    g.drawString("Game over", 400, 400);
		    time.stop();
		    
	    }
	    
		//初始化玩家1蛇头
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
        
        //初始化玩家2的蛇头
        if(direction1.equals("U")){
			up1.paintIcon(this, g, snakex1[0], snakey1[0]);
		}
        if(direction1.equals("R")){
        	right1.paintIcon(this, g, snakex1[0], snakey1[0]);
		}
        if(direction1.equals("D")){
        	down1.paintIcon(this, g, snakex1[0], snakey1[0]);
        }
        if(direction1.equals("L")){
        	left1.paintIcon(this, g, snakex1[0], snakey1[0]);
        }
        
        //初始化玩家1蛇身
        for(int i=1;i<length;i++){
        	body.paintIcon(this, g, snakex[i], snakey[i]);
        }
        
        //初始化玩家2的蛇身
        for(int i=1;i<length1;i++){
        	hushe.paintIcon(this, g, snakex1[i], snakey1[i]);
        }
        
        //当双方分数达到分数时，就产生一个不一样的食物，分数更高
        if((score+score1)%200==0){
        	big.paintIcon(this, g, foodx, foody);
        }
        else{
        	food.paintIcon(this, g, foodx, foody);
        }
        
	}
	
	/*
	 * 对一些数据的初始化，以及根据坐标的位置定义刚开始蛇的位置，方便游戏结束直接调用这个方法重新开始游戏
	 */
	public void setup(){
		isstarted=false;
		isstarted1=false;
		isover=false;
		isover1=false;
		length=3;
		length1=3;
		direction="R";
		direction1="L";
		snakex[0]=100;
		snakey[0]=110;
		snakex[1]=75;
		snakey[1]=110;
		snakex[2]=50;
		snakey[2]=110;
		
		snakex1[0]=775;
		snakey1[0]=635;
		snakex1[1]=800;
		snakey1[1]=635;
		snakex1[2]=825;
		snakey1[2]=635;
		
	}
	
	/*
	 *既然实现了KeyListener的接口，那就必须重写接口的三个方法，在方法KeyEvent里面
	 * 我们可以实现键盘事件
	 */
public void keyPressed(KeyEvent e) {
	int KeyCode=e.getKeyCode();               //通过这个方法获得键盘敲入的键
	
	if(KeyCode==KeyEvent.VK_SPACE){
	    
		if(isover){
			setup();
			score=0;
			score1=0;
			foodx=rand.nextInt(34)*25+25;
			foody=rand.nextInt(24)*25+85;
		}
		else{
			isstarted=true;
			time.start();
			}
		
		
		
		if(isover1){
			setup();
			score1=0;
			score=0;
			foodx=rand.nextInt(34)*25+25;
			foody=rand.nextInt(24)*25+85;
		
		}
		else{
			isstarted1=true;
			time.start();
			
			}
	}
	
	
	    /*
	     * 玩家1通过键盘上的上下左右键来控制蛇运动的方向
	     * 玩家2通过键盘上的W,S,A,D键来通知蛇运动的方向
	     */
	
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
	    
	
	
	   if(KeyCode==KeyEvent.VK_A&&direction1!="R"){
		     direction1="L";
	    }
       if(KeyCode==KeyEvent.VK_W&&direction1!="D"){
	         direction1="U";	
	    }
       if(KeyCode==KeyEvent.VK_S&&direction1!="U"){
	         direction1="D";
        }
       if(KeyCode==KeyEvent.VK_D&&direction1!="L"){
	         direction1="R";
        }
    
    }
  /*
   * 实现了ActionListener的接口，，就要重写它的方法，也就是下面这一个方法
   */

public void actionPerformed(ActionEvent e) {
	time.start();
	//如果开始或者是还没有结束就开始移动
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
				snakex[0]=25;
			}
		 }
		 else if(direction.equals("L")){
			snakex[0]-=25;
			if(snakex[0]<25){
				snakex[0]=850;
			}
		 }
		else if(direction.equals("D")){
			snakey[0]+=25;
			if(snakey[0]>660){
				snakey[0]=85;
			}
         }
		else if(direction.equals("U")){
			snakey[0]-=25;
			if(snakey[0]<85){
				snakey[0]=660;
			}
         }
	 }
	    /*
	     * 对玩家2的蛇进行移动
	     */
	  if(isstarted1&&!isover1&&!isover&&isstarted){
		  //开始移动身体
		 for(int i=length1;i>0;i--){
			snakex1[i]=snakex1[i-1];
			snakey1[i]=snakey1[i-1];
		 }
		 //移动头部
		 if(direction1.equals("R")){
			snakex1[0]+=25;
			if(snakex1[0]>850){
				snakex1[0]=25;
			}
		 }
		else if(direction1.equals("L")){
			snakex1[0]-=25;
			if(snakex1[0]<25){
				snakex1[0]=850;
			}
		}
		else if(direction1.equals("D")){
			snakey1[0]+=25;
			if(snakey1[0]>660){
				snakey1[0]=85;
			}
        }
		else if(direction1.equals("U")){
			snakey1[0]-=25;
			if(snakey1[0]<85){
				snakey1[0]=660;
			}
        }
	}
	  /*
	   * 当蛇头遇到食物时，那么蛇长就要相应增加，分数也要相应变化
	   * 而食物也要重新产生
	   */
	if(snakex[0]==foodx&&snakey[0]==foody){
		
		foodx=rand.nextInt(34)*25+25;
	    foody=rand.nextInt(24)*25+85;
		length+=1;
		if((score+score1)%200==0){
			score+=40;
		}
		else{
			score+=20;
		}
	}
	//玩家2
    if(snakex1[0]==foodx&&snakey1[0]==foody){
		
		foodx=rand.nextInt(34)*25+25;
	    foody=rand.nextInt(24)*25+85;
		length1+=1;
		if((score+score1)%200==0){
			score1+=40;
		}else{
			score1+=20;
		}
	}
    /*
     * 对于该模式，判断死亡的条件是：当一方玩家蛇头碰到另一玩家的蛇身，那么游戏结束
     * 另一方玩家获胜，游戏终止。
     */
	for(int i=1;i<=length1;i++){
		if(snakex[0]==snakex1[i]&&snakey[0]==snakey1[i]){
			isover=true;
			if(direction=="R"){
				snakex[0]-=25;
				for(int j=1;j<length;j++){
					snakex[j]=snakex[j+1];
					snakey[j]=snakey[j+1];
				}
			}
				if(direction=="U"){
					snakey[0]+=25;
					for(int j=1;j<length;j++){
						snakex[j]=snakex[j+1];
						snakey[j]=snakey[j+1];
					}
				}
					if(direction=="L"){
						snakex[0]+=25;
						for(int j=1;j<length;j++){
							snakex[j]=snakex[j+1];
							snakey[j]=snakey[j+1];
						}
					}
						if(direction=="D"){
							snakey[0]-=25;
							for(int j=1;j<length;j++){
								snakex[j]=snakex[j+1];
								snakey[j]=snakey[j+1];
							}
			         }
		}
	}
	for(int j=1;j<=length;j++){
		if(snakex1[0]==snakex[j]&&snakey1[0]==snakey[j]){
			isover1=true;
			if(direction1=="R"){
				snakex1[0]-=25;
				for(int i=1;i<length1;i++){
					snakex1[i]=snakex1[i+1];
					snakey1[i]=snakey1[i+1];
				}
			}
				if(direction1=="U"){
					snakey1[0]+=25;
					for(int i=1;i<length1;i++){
						snakex1[i]=snakex1[i+1];
						snakey1[i]=snakey1[i+1];
					}
				}
					if(direction1=="L"){
						snakex1[0]+=25;
						for(int i=1;i<length1;i++){
							snakex1[i]=snakex1[i+1];
							snakey1[i]=snakey1[i+1];
						}
					}
						if(direction1=="D"){
							snakey1[0]-=25;
							for(int i=1;i<length1;i++){
								snakex1[i]=snakex1[i+1];
								snakey1[i]=snakey1[i+1];
							}
						
				
			         }
		}
	}
			
	if(snakex[0]==snakex1[0]&&snakey[0]==snakey1[0]){
		a=1;
	}
	
	repaint();
}
    /*
     * 以下两个方法是必须重写的，没用到也要写出来
     */
	public void keyReleased(KeyEvent e) {
		
	}	
	public void keyTyped(KeyEvent e) {

	}
}
