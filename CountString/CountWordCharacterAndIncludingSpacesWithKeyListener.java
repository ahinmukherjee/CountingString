import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CountWordCharacterAndIncludingSpacesWithKeyListener{
	public static void main(String args[]){
		FrameTest ft= new FrameTest();
		ft.setVisible(true);
		ft.setDefaultCloseOperation(3);   	
	} 
}   
    
class FrameTest extends JFrame{
	public FrameTest(){
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension scrsize = kit.getScreenSize();
		int w = scrsize.width;
		int h = scrsize.height;
		setSize(600,500);
		setLocation(w/5, h/8);
		setTitle("Word counting");
		setResizable(false);
		FramePanels fp = new FramePanels();
		add(fp);
	}
} 
    
class FramePanels extends JPanel implements KeyListener{
	JLabel label1, label2, label3;
	JTextArea area;
	JButton exit, clear;
	JScrollPane scrollpane;
	FramePanels(){
		setLayout(null);
		 setBackground(new Color(20, 25, 45));
		Font resultFont =new Font("Segoe UI", Font.BOLD, 14);

		label1=new JLabel("Word Count");
		label2=new JLabel("Character Count");
		label3=new JLabel("Character With Spaces");

		label1.setFont(resultFont);
		label2.setFont(resultFont);
		label3.setFont(resultFont);

		label1.setForeground(Color.WHITE);
		label2.setForeground(Color.WHITE);
		label3.setForeground(Color.WHITE);

		label1.setBounds(20,10,250,25);
		label2.setBounds(20,30,250,25);
		label3.setBounds(20,50,550,25);

		add(label1);
		add(label2);
		add(label3);

		area= new JTextArea();
		area.setBounds(20,90,550,300);
		area.setFont(new Font("Arial",Font.PLAIN,18));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setMargin(new Insets(10,10,10,10));
		area.addKeyListener(this);
		//add(area);
		
		scrollpane=new JScrollPane(area);
		scrollpane.setBounds(20,90,550,300);
		add(scrollpane);
		
		exit= new JButton("Exit");
		exit.setBounds(200,400,85,50);
		exit.setFont(new Font("Segoe UI", Font.BOLD, 15));
		exit.setBackground(new Color(220, 53, 69));
		exit.setForeground(Color.WHITE);
		exit.setFocusPainted(false);
		add(exit);

		clear= new JButton("Clear");
		clear.setBounds(300,400,85,50);
		clear.setFont(new Font("Segoe UI", Font.BOLD, 15));
		clear.setBackground(Color.YELLOW);
		clear.setForeground(Color.BLACK);
		clear.setFocusPainted(false);
		add(clear);

		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent av){
				System.exit(0);
			}
		});
	
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent av){
				area.setText("");
				label1.setText("Word Count");
				label2.setText("Character Count");
				label3.setText("Character With Spaces");
			}
		});
	}
	
	public void updatedCount(){
		String s=area.getText();
		String words[]=s.split("\\s");

		int letters=s.replace(" ","").length();
		int spaces=s.length()-letters;

		if (s.trim().isEmpty()) {
			label1.setText("Please Enter Text");
			label2.setText("Please Enter Text");
			label3.setText("Please Enter Text");
			return;
			}

		label1.setText("The number of words is: "+words.length);
		label2.setText("The number of characters is: "+s.length());
		label3.setText("The number of characters with spaces is: "+letters+" (letters) + "+spaces+" (spaces) = " +s.length());
	}
	
	public void keyReleased(KeyEvent e){ 
		updatedCount();	
	}
	
	public void keyPressed(KeyEvent e){ }
	public void keyTyped(KeyEvent e){ }	
}
