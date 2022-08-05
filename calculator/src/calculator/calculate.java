package calculator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Expression;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class calculate implements ActionListener{

	//buttons
	private static JFrame frame;
	private static JPanel panel;
	private static JButton seven;
	private static JButton eight;
	private static JButton nine;
	private static JButton del;
	private static JButton ac;
	private static JButton four;
	private static JButton five;
	private static JButton six;
	private static JButton mult;
	private static JButton div;
	private static JButton one;
	private static JButton two;
	private static JButton three;
	private static JButton add;
	private static JButton sub;
	private static JButton zero;
	private static JButton dot;
	private static JButton exp;
	private static JButton equals;
	private static JPanel screen;
	private static JLabel label;
	private static JLabel text;
	
	public  static void main(String[] args) {
		
		panel  = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		
		screen = new JPanel();
		screen.setLayout(null);
		screen.setBackground(Color.LIGHT_GRAY);
		screen.setForeground(Color.BLACK);
		screen.setBounds(20,30,290,100);
		panel.add(screen);
		
		label = new JLabel();
		label.setForeground(Color.black);
		label.setFont(new Font("arial",Font.BOLD,30));
		label.setBounds(20,0,290,80);
		screen.add(label);
		
		text = new JLabel();
		text.setForeground(Color.LIGHT_GRAY);
		text.setFont(new Font("serif",Font.BOLD,30));
		text.setBounds(45,400,290,35);
		text.setText("CALCULATOR");
		panel.add(text);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(350,500);
		frame.setResizable(false);
		frame.add(panel);
		
		seven = new JButton();
		seven.setText("7");
		seven.setBounds(20,150,50,50);
		seven.addActionListener(new calculate());
		panel.add(seven);
		
		eight = new JButton();
		eight.setText("8");
		eight.setBounds(80,150,50,50);
		eight.addActionListener(new calculate());
		panel.add(eight);
		
		nine = new JButton();
		nine.setText("9");
		nine.setBounds(140,150,50,50);
		nine.addActionListener(new calculate());
		panel.add(nine);
		
		del = new JButton();
		del.setText("del");
		del.setBounds(200,150,55,50);
		del.addActionListener(new calculate());
		del.setBackground(Color.orange);
		panel.add(del);
		
		ac = new JButton();
		ac.setText("ac");
		ac.setBounds(260,150,50,50);
		ac.addActionListener(new calculate());
		ac.setBackground(Color.orange);
		panel.add(ac);
		
		four = new JButton();
		four.setText("4");
		four.setBounds(20,210,50,50);
		four.addActionListener(new calculate());
		panel.add(four);
		
		five = new JButton();
		five.setText("5");
		five.setBounds(80,210,50,50);
		five.addActionListener(new calculate());
		panel.add(five);
		
		six = new JButton();
		six.setText("6");
		six.setBounds(140,210,50,50);
		six.addActionListener(new calculate());
		panel.add(six);
		
		mult = new JButton();
		mult.setText("*");
		mult.setBounds(200,210,50,50);
		mult.addActionListener(new calculate());
		panel.add(mult);
		
		div = new JButton();
		div.setText("/");
		div.setBounds(260,210,50,50);
		div.addActionListener(new calculate());
		panel.add(div);
		
		one = new JButton();
		one.setText("1");
		one.setBounds(20,270,50,50);
		one.addActionListener(new calculate());
		panel.add(one);
		
		two = new JButton();
		two.setText("2");
		two.setBounds(80,270,50,50);
		two.addActionListener(new calculate());
		panel.add(two);
		
		three = new JButton();
		three.setText("3");
		three.setBounds(140,270,50,50);
		three.addActionListener(new calculate());
		panel.add(three);
		
		add = new JButton();
		add.setText("+");
		add.setBounds(200,270,50,50);
		add.addActionListener(new calculate());
		panel.add(add);
		
		sub = new JButton();
		sub.setText("-");
		sub.setBounds(260,270,50,50);
		sub.addActionListener(new calculate());
		panel.add(sub);
		
		zero = new JButton();
		zero.setText("0");
		zero.setBounds(20,330,50,50);
		zero.addActionListener(new calculate());
		panel.add(zero);
		
		dot = new JButton();
		dot.setText(".");
		dot.setBounds(80,330,50,50);
		dot.addActionListener(new calculate());
		panel.add(dot);
		
		exp = new JButton();
		exp.setText("^");
		exp.setBounds(140,330,55,50);
		exp.addActionListener(new calculate());
		panel.add(exp);
		
		equals = new JButton();
		equals.setText("=");
		equals.setBounds(200,330,110,50);
		equals.setBackground(Color.orange);
		equals.addActionListener(new calculate());
		panel.add(equals);
		
	}
	
	//evaluation function
	public static double eval(final String str) {
	    return new Object() {
	        int pos = -1, ch;
	        
	        void nextChar() {
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }
	        
	        boolean eat(int charch) {
	            while (ch == ' ') nextChar();
	            if (ch == charch) {
	                nextChar();
	                return true;
	            }
	            return false;
	        }
	        
	        double parse() {
	            nextChar();
	            double x = parseExpression();
	            if (pos < str.length()) label.setText("syntax error");
	            return x;
	        }
	        
	        
	        double parseExpression() {
	            double x = parseTerm();
	            for (;;) {
	                if      (eat('+')) x += parseTerm(); // addition
	                else if (eat('-')) x -= parseTerm(); // subtraction
	                else return x;
	            }
	        }
	        
	        double parseTerm() {
	            double x = parseFactor();
	            for (;;) {
	                if      (eat('*')) x *= parseFactor(); // multiplication
	                else if (eat('/')) x /= parseFactor(); // division
	                else return x;
	            }
	        }
	        
	        double parseFactor() {
	            if (eat('+')) return +parseFactor(); // unary plus
	            if (eat('-')) return -parseFactor(); // unary minus
	            
	            double x = 0;
	            int startPos = this.pos;            
	            
	            if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            }
	            
	            else {
	                label.setText("Syntax error");
	            }
	            
	            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
	            
	            return x;
	        }
	    }.parse();
	}
	//end of evaluation function
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "del") {
			String text = label.getText();
			if(text.length() != 0) {
				label.setText(text.substring(0,text.length()-1));
			}
		}
		else if(e.getActionCommand() == "ac") {
			label.setText("");
		}
		else if(e.getActionCommand() == "=") {
			Double result = eval(label.getText());
			label.setText(Double.toString(result));
		}
		else {
			label.setText(label.getText()  +  e.getActionCommand());
		}
		
	}
}
