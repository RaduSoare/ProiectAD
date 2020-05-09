import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.StyleConstants;
import javax.swing.JTextArea;
import java.awt.Component;

public class Gui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException  {
		Socket sock = new Socket("127.0.0.1", 8080);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui(sock);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
/*	sock.close();
	socketScanner.close();
	socketEmitter.close(); */
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws UnknownHostException 
	 */
	public Gui(Socket sock) throws UnknownHostException, ClassNotFoundException, IOException{
		initialize(sock);
		
       
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void initialize(Socket sock) throws UnknownHostException, IOException, ClassNotFoundException {

		PrintStream answearEmitter = new PrintStream(sock.getOutputStream());
        InputStream inputStream = sock.getInputStream();
		// create a DataInputStream so we can read data from it.
        ObjectInputStream  objectInputStream = new ObjectInputStream(inputStream);
        
        ArrayList<Question> questions  = (ArrayList<Question>)objectInputStream.readObject();
	     
	        
	       
        
		
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton answer1 = new JButton("Option1");
		answer1.setBounds(132, 211, 117, 42);
		frame.getContentPane().add(answer1);
		
		
		JButton answer2 = new JButton("Option2");
		answer2.setBounds(282, 211, 117, 42);
		frame.getContentPane().add(answer2);
		
		JButton answer3 = new JButton("Option3");
		answer3.setBounds(132, 265, 117, 42);
		frame.getContentPane().add(answer3);
		
		JButton answer4 = new JButton("Option4");
		answer4.setBounds(282, 265, 117, 42);
		frame.getContentPane().add(answer4);
		
		JTextArea questionArea = new JTextArea();
		questionArea.setEditable(false);
		questionArea.setBounds(73, 106, 408, 73);
		frame.getContentPane().add(questionArea);
		
		JButton questionGenerator = new JButton("Genereaza intrebare");
		questionGenerator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				int randomQuestionIndex = rand.nextInt(questions.size());
			    questionArea.setText(questions.get(randomQuestionIndex).getQuestion());
		        answer1.setText(questions.get(randomQuestionIndex).getAnswear1());
		        answer2.setText(questions.get(randomQuestionIndex).getAnswear2());
		        answer3.setText(questions.get(randomQuestionIndex).getAnswear3());
		        answer4.setText(questions.get(randomQuestionIndex).getAnswear4());
		        
		        
			}
		});
		questionGenerator.setBounds(175, 318, 177, 42);
		frame.getContentPane().add(questionGenerator);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(175, 393, 188, 23);
		exitButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  answearEmitter.println("exit");
				  System.exit(1);
			  } 
			} );
		
		answer2.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  
			  } 
			} );
		frame.getContentPane().add(exitButton);
		
		answer1.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  answearEmitter.println(answer1.getText());
			  } 
			} );
		answer2.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  answearEmitter.println(answer2.getText());
			  } 
			} );
		answer3.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  answearEmitter.println(answer3.getText());
			  } 
			} );
		answer4.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  answearEmitter.println(answer4.getText());
			  } 
			} );
		
		
	}
}
