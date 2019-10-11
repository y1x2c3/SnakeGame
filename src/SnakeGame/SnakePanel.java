package SnakeGame;


import javax.swing.*;

import game.Controller;
import game.Food;
import game.GamePanel;
import game.Ground;
import game.Snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakePanel extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon background=new ImageIcon("images/贪吃蛇.jpg");
	ImageIcon kaishi1=new ImageIcon("images/按钮1.png");
	ImageIcon kaishi2=new ImageIcon("images/按钮2.png");
	ImageIcon kaishi3=new ImageIcon("images/按钮3.png");
	JLabel jlabel=new JLabel(background);
	JButton button1=new JButton("",kaishi1);
	JButton button2=new JButton("",kaishi2);
	JButton button3=new JButton("",kaishi3);
	public SnakePanel(){
		add(button1);
		add(button2);
		add(button3);
		add(jlabel);
		setBounds(0,0,905,750);
		button1.setBounds(47,459,167,53);
		button2.setBounds(356,459,167,53);
		button3.setBounds(670,459,167,53);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		jlabel.setBounds(0,0,900,720);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button1){
			dispose();
			   Snake snake=new Snake();
				  Food food =new Food();
				  Ground ground =new Ground();
				  
				  GamePanel gamePanel=new GamePanel();
				  
				  Controller c=new Controller(snake, food, ground, gamePanel);
			       snake.addSnakeListener(c);
			       gamePanel.addKeyListener(c);
			     
			       JFrame frame=new JFrame("贪吃蛇");
				
					//frame.setLayout(null);
					gamePanel.setLayout(null);
					
					JLabel label1 = new JLabel();
					 label1.setBounds(0, 400, 400, 100);
					 label1.setText("     SPASE=>>PAUSE or AGAIN;   ");
					 label1.setForeground(Color.BLACK);
					 frame.add(label1, BorderLayout.SOUTH);
					 
					  Button button = new Button("重新开始");
				      button.setBounds(500, 0, 100, 15);
				      frame.add(button);
				      button.addActionListener(new ActionListener(){

							public void actionPerformed(ActionEvent e) {
								frame.dispose();
								new SnakePanel();
								
							}
				      });
			       frame.setSize(606,630);
			      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			       frame.setLocationRelativeTo(null);
			      // 让面板获得焦点
			       gamePanel.setFocusable(true);
			     frame.add(gamePanel);
			     gamePanel.setBackground(Color.PINK);
			     frame.setResizable(false);
			     frame.setVisible(true);
			       
			       //启动游戏
			       c.startGame();
			       //显示
		}
		if(e.getSource()==button2){
			dispose();
			JFrame frame=new JFrame();
			Button button6=new Button("返回主菜单");
			button6.setBounds(800, 40, 70, 20);
			frame.add(button6);
			button6.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					new SnakePanel();
				}
			});
			JFrame frame1=new JFrame();
			frame.setBounds(10, 10, 900, 720);
			frame.setResizable(false);
			SingleMode snakepanel=new SingleMode();
			frame.add(snakepanel);
			frame.setVisible(true);
		}
		if(e.getSource()==button3){
			dispose();
			JFrame frame=new JFrame();
			Button button6=new Button("返回主菜单");
			button6.setBounds(800, 40, 70, 20);
			frame.add(button6);
			button6.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					new SnakePanel();
				}
			});
			JFrame frame2=new JFrame();
			frame.setBounds(10, 10, 900, 720);
			frame.setResizable(false);
			frame.add(new DoubleMode());
			frame.setVisible(true);
		}
		
	}
	
}
