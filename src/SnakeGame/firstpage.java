package SnakeGame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class firstpage extends JFrame implements ActionListener {
	ImageIcon background00=new ImageIcon("firstpage.jpg");
	ImageIcon denglu=new ImageIcon("µÇÂ¼.jpg");
	ImageIcon qingchu=new ImageIcon("Çå³ý.jpg");
	JLabel jlabel00=new JLabel(background00);
private static final long serialVersionUID = 1L;
	 static JFrame frame=new JFrame();
private JPanel jp=new JPanel();

private JLabel[] jlArray={new JLabel("ÓÃ»§Ãû"),

new JLabel("ÃÜ¡¡Âë"),new JLabel("")};
JButton button01=new JButton("",denglu);
private JButton[] jbArray={button01,

new JButton("",qingchu)};
JTextField jtxtName =new JTextField();

private JPasswordField jtxtPassword= new JPasswordField();

public firstpage(){

jp.setLayout(null);

/*for(int i=0;i<2;i++){

jlArray[i].setBounds(340, 20+i*50, 80, 26);

jbArray[i].setBounds(340+i*110, 130, 80,26);

jp.add(jlArray[i]);

jp.add(jbArray[i]);

jbArray[i].addActionListener(this);

}*/
jbArray[0].setBounds(370,465, 60,25);
jbArray[1].setBounds(523,465, 60,25);
jbArray[0].addActionListener(this);
jbArray[1].addActionListener(this);
jtxtName.setBounds(370,373,215,35);
jp.add(jbArray[0]);
jp.add(jbArray[1]);
jp.add(jtxtName);

jtxtName.addActionListener(this);

jtxtPassword.setBounds(370,425,215,35);

jp.add(jtxtPassword);

jtxtPassword.setEchoChar('*');

jtxtPassword.addActionListener(this);

jlArray[2].setBounds(10, 180, 300, 30);

jp.add(jlArray[2]);

this.add(jp);
jp.add(jlabel00);
this.setTitle("µÇÂ½");

this.setResizable(false);

this.setBounds(0,0,905,750);

this.setVisible(true);
jlabel00.setBounds(0,0,900,720);

}
public void actionPerformed(ActionEvent e) {    //µÇÂ½ÊÂ¼þ
		if(e.getSource()==jtxtName){

			jtxtPassword.requestFocus();

			}else if(e.getSource()==jbArray[1]){

			jlArray[2].setText("");

			jtxtName.setText("");

			jtxtPassword.setText("");

			jtxtName.requestFocus();

			}else{

			if(jtxtName.getText().equals("1")&&String.valueOf(jtxtPassword.getPassword()).equals("1")){
				dispose();
				new SnakePanel(){};
				}
			else{

				jlArray[1].setText("µÇÂ½´íÎó");

				}
			}
		
	}
	
}
