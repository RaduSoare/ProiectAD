import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.prefs.BackingStoreException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.StyleConstants;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Gui {

	private JFrame frame;
	private int questionNumber = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException  {
		Socket sock = new Socket("127.0.0.1", 9091);
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
	
	public void activateAnswers(JButton answer1, JButton answer2, 
			JButton answer3, JButton answer4, boolean enabled) {
		if(enabled == true) {
			  answer1.setEnabled(true);
			  answer2.setEnabled(true);
			  answer3.setEnabled(true);
			  answer4.setEnabled(true);
		} else {
			  answer1.setEnabled(false);
			  answer2.setEnabled(false);
			  answer3.setEnabled(false);
			  answer4.setEnabled(false);
		}
	}
	
	
	public void renderQuestion(Question question, JButton answer1, JButton answer2, 
			JButton answer3, JButton answer4, JTextArea questionArea) {
		answer1.setText(question.getAnswear1());
		answer2.setText(question.getAnswear2());
		answer3.setText(question.getAnswear3());
		answer4.setText(question.getAnswear4());
		questionArea.setText(question.getQuestion());
	}
	
	public void backgroundUpdater(int questionNumber, JLabel backgroundLabel) {
		ImageIcon icon = null;
		  switch(questionNumber) {
		  case 1:
		  		icon  = new ImageIcon("Images/q1.jpg");
		  		break;
		  	case 2:
		  		icon  = new ImageIcon("Images/q2.jpg");
		  		break;
		  	case 3:
		  		icon  = new ImageIcon("Images/q3.jpg");
		  		break;
		  	case 4:
		  		icon  = new ImageIcon("Images/q4.jpg");
		  		break;
		  	case 5:
		  		icon  = new ImageIcon("Images/q5.jpg");
		  		break;
		  		
		  }
		  backgroundLabel.setIcon(icon);
	}
	
	public void initialize(Socket sock) throws UnknownHostException, IOException, ClassNotFoundException {

		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton answer1 = new JButton("Option1");
		answer1.setOpaque(false);
		answer1.setContentAreaFilled(false);
		answer1.setBorderPainted(true);
		answer1.setForeground(Color.WHITE);
		answer1.setBounds(185, 487, 215, 60);
		frame.getContentPane().add(answer1);
		answer1.setEnabled(false);
		
		JButton answer2 = new JButton("Option2");
		answer2.setOpaque(false);
		answer2.setContentAreaFilled(false);
		answer2.setBorderPainted(true);
		answer2.setForeground(Color.WHITE);
		answer2.setBounds(425, 487, 215, 60);
		frame.getContentPane().add(answer2);
		answer2.setEnabled(false);
		
		JButton answer3 = new JButton("Option3");
		answer3.setOpaque(false);
		answer3.setContentAreaFilled(false);
		answer3.setBorderPainted(true);
		answer3.setForeground(Color.WHITE);
		answer3.setBounds(185, 567, 215, 60);
		frame.getContentPane().add(answer3);
		answer3.setEnabled(false);
		
		JButton answer4 = new JButton("Option4");
		answer4.setOpaque(false);
		answer4.setContentAreaFilled(false);
		answer4.setBorderPainted(true);
		answer4.setForeground(Color.WHITE);
		answer4.setBounds(425, 567, 215, 60);
		frame.getContentPane().add(answer4);
		answer4.setEnabled(false);
		
		JTextArea questionArea = new JTextArea();
		questionArea.setEditable(false);
		questionArea.setFont(new Font("Segoe UI", Font.BOLD, 20));
		questionArea.setLineWrap(true);
		questionArea.setWrapStyleWord(true);
		
		questionArea.setOpaque(false);
		questionArea.setForeground(Color.WHITE);
		
		questionArea.setBounds(185, 387, 450, 80);
		frame.getContentPane().add(questionArea);
		
		
		JButton exitButton = new JButton("Exit");
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(true);
		exitButton.setForeground(Color.WHITE);
		exitButton.setBounds(1040, 577, 140, 40);
		frame.getContentPane().add(exitButton);
		
		JButton startButton = new JButton("Start");
		startButton.setOpaque(false);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(true);
		startButton.setForeground(Color.WHITE);

		startButton.setBounds(880, 577, 140, 40);
		frame.getContentPane().add(startButton);
		
		JButton nextQuestionButton = new JButton("Next question");
		nextQuestionButton.setOpaque(false);
		nextQuestionButton.setContentAreaFilled(false);
		nextQuestionButton.setBorderPainted(true);
		nextQuestionButton.setForeground(Color.WHITE);
		nextQuestionButton.setBounds(676, 604, 155, 23);
		frame.getContentPane().add(nextQuestionButton);
		nextQuestionButton.setEnabled(false);
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setBounds(0, 0, 1280, 720);
		ImageIcon icon  = new ImageIcon("Images/background.jpg");
		backgroundLabel.setIcon(icon);
		frame.getContentPane().add(backgroundLabel);
		
		
		PrintStream answearEmitter = new PrintStream(sock.getOutputStream());
        InputStream inputStream = sock.getInputStream();
		// create a DataInputStream so we can read data from it.
        ObjectInputStream  objectInputStream = new ObjectInputStream(inputStream);
        
        
		
		answer1.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  answearEmitter.println(answer1.getText());
				  nextQuestionButton.setEnabled(true);
				  activateAnswers(answer1, answer2, answer3, answer4, false);
				  String serverM = null;
				  try {
					  serverM = (String)objectInputStream.readObject();
					  System.out.println(serverM);
					  if(serverM.equals("incorrect")) {
						  JOptionPane.showMessageDialog(null, "Mai bine mergeai la munca");
						  TimeUnit.SECONDS.sleep(1);
						  nextQuestionButton.setEnabled(false);
						  System.exit(0);
					  }
					  
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				  
				  
			  } 
			} );
		answer2.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  answearEmitter.println(answer2.getText());
				  nextQuestionButton.setEnabled(true);
				  activateAnswers(answer1, answer2, answer3, answer4, false);
				  String serverM = null;
				  try {
					  serverM = (String)objectInputStream.readObject();
					  System.out.println(serverM);
					  if(serverM.equals("incorrect")) {
						  JOptionPane.showMessageDialog(null, "Mai bine mergeai la munca");
						  TimeUnit.SECONDS.sleep(1);
						  nextQuestionButton.setEnabled(false);
						  System.exit(0);
					  }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  
			  } 
			} );
		answer3.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  answearEmitter.println(answer3.getText());
				  nextQuestionButton.setEnabled(true);
				  activateAnswers(answer1, answer2, answer3, answer4, false);
				  String serverM = null;
				  try {
					  serverM = (String)objectInputStream.readObject();
					  System.out.println(serverM);
					  if(serverM.equals("incorrect")) {
						  JOptionPane.showMessageDialog(null, "Mai bine mergeai la munca");
						  TimeUnit.SECONDS.sleep(1);
						  nextQuestionButton.setEnabled(false);
						  System.exit(0);
					  }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  
			  } 
			} );
		answer4.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  answearEmitter.println(answer4.getText());
				  nextQuestionButton.setEnabled(true);
				  String serverM = null;
				  activateAnswers(answer1, answer2, answer3, answer4, false);
				  try {
					  serverM = (String)objectInputStream.readObject();
					  System.out.println(serverM);
					  if(serverM.equals("incorrect")) {
						  JOptionPane.showMessageDialog(null, "Mai bine mergeai la munca");
						  TimeUnit.SECONDS.sleep(1);
						  nextQuestionButton.setEnabled(false);
						  System.exit(0);
					  }
					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 
				  
			  } 
			} );
		startButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  backgroundUpdater(questionNumber, backgroundLabel);
				  startButton.setEnabled(false);
				  Question question = null;
				try {
					question = (Question)objectInputStream.readObject();
					System.out.println("citit prima intrebare");
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  renderQuestion(question, answer1, answer2, answer3, answer4, questionArea); 
				  activateAnswers(answer1, answer2, answer3, answer4, true);
				  
			  } 
			} );
		exitButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  System.exit(1);
			  } 
			} );
		nextQuestionButton.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  if(startButton.isEnabled() == false) {

					  Question question = null;
						try {
							 
							question = (Question)objectInputStream.readObject();
							 System.out.println(question.getQuestion());
						} catch (EOFException e2) {
							JOptionPane.showMessageDialog(null, "Ai castigat marele premiu");
							try {
								TimeUnit.SECONDS.sleep(1);
							} catch (InterruptedException interruptedException) {
								interruptedException.printStackTrace();
							}
							nextQuestionButton.setEnabled(false);
							System.exit(0);

						} catch (ClassNotFoundException | IOException e1) {
							e1.printStackTrace();
						}
						renderQuestion(question, answer1, answer2, answer3, answer4, questionArea); 
						  answer1.setEnabled(true);
						  answer2.setEnabled(true);
						  answer3.setEnabled(true);
						  answer4.setEnabled(true);
						  nextQuestionButton.setEnabled(false);
						  questionNumber++;

						  backgroundUpdater(questionNumber, backgroundLabel);
				  }
			  } 
			} );
		
		
		
		
		
		
	}
}
