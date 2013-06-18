//import java.awt.*;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import jhook.Keyboard;
import jhook.KeyboardListener;

public class Interact extends JFrame implements KeyListener,KeyboardListener {
	JTextField enterExp;
	JLabel cpu;
	JLabel mem;
//    JButton button1 = newer JButton("Process");
    Parse parseExpr = Parse.getInstance();
    
    static Interact g;
    
//    Timer time = new Timer(8,this);
    
    public Interact()
    {
//    	time.start();
    	
    	
        JPanel myPanel = new JPanel();
        this.add(myPanel);
        myPanel.setLayout(new GridLayout(2, 1));
         
        // this.pack();
        Font font = new Font("Arial",Font.BOLD,36);
        
        enterExp = new JTextField();
        enterExp.setFont(font);
        myPanel.add(enterExp);
//        myPanel.add(button1);
//        button1.addActionListener(this);
        
        enterExp.addKeyListener(this);
//        this.setFocusable(true);
		enterExp.requestFocus();
		this.setFocusTraversalKeysEnabled(true);
		
		cpu = new JLabel("CPU: "+" MEM: ");
		cpu.setFont(new Font("Arial", Font.PLAIN, 20));
//		mem = new JLabel("MEM Usage: ");
		
		myPanel.add(cpu);
//		myPanel.add(mem);
		
        Keyboard kb=new Keyboard();
        kb.addListener(this);
        
        Thread activity = new Thread(new Activity());
        activity.start();
        
        new Network().startReceive();
        
    }
    
//    public void actionPerformed(ActionEvent e)
//    {
//        if (e.getSource() == button1) {       	
//        	try{
//            String expression = enterExp.getText(); //extracts the expression entered
//            parseExpr.parse(expression);
//            } catch(Exception exc){
//                exc.printStackTrace();
//            }           
//        }    
//    }

        
//        new Network().startReceive();
       
//    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("hello");
		if (e.getKeyCode()==KeyEvent.VK_ENTER){
//			System.out.println("hi");
			String expression = enterExp.getText(); //extracts the expression entered
            parseExpr.parse(expression);
		}
		else if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
			g.setState(Frame.ICONIFIED);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(boolean keydown, int vk) {
		// TODO Auto-generated method stub
//		System.out.println(vk);
		if (keydown==false && vk==KeyEvent.VK_R){
			g.setState(Frame.NORMAL);
			g.toFront();
		}
	}
	
	
    public static void main(String args[])
    {
        g = new Interact();
        // g.setLocation(10, 10);
        // g.setSize(300, 300);
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.setSize(600,150);
        g.setLocationRelativeTo(null); 
        
        try{
        	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e ){
        }
    }
}
