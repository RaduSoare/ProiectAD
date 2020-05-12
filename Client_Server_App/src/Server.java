import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Server {

	public static void main(String[] args) throws IOException {
		String answear = null;
		ServerSocket server_sock = new ServerSocket(8081);
		Socket sock = server_sock.accept();
		Scanner socketReader = new Scanner(sock.getInputStream());
		PrintStream socketEmitter = new PrintStream(sock.getOutputStream());
		
		Question question1 = new Question("Care este cel mai inalt punct de pe Pamant?", 
			"7500m", "8000m", "9808m", "8848m", "8848m");
		Question question2 = new Question("Aproximativ in ce procent se gaseste Oxigenul in aer?", 
				"21 procente", "15 procentem", "16 procente", "75 procente", "21 procente");
		Question question3 = new Question("Care a fost capitala Imperiului Bizantin ?", 
				"Roma", "Ierusalim", "Atena", "Constantinopol", "Constantinopol");
		Question question4 = new Question("Care a fost primul aparat care putea inregistra si reproduce sunetele?", 
				"casetofonul", "fonograful", "gramofonul", "diapazonul", "fonograful");
		
		ArrayList<Question> questions = new ArrayList<Question>();
		questions.add(question1); questions.add(question2); questions.add(question3); questions.add(question4);
		 
		// get the output stream from the socket.
        OutputStream outputStream = sock.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        for(int i = 0; i < questions.size(); i++) {
        	objectOutputStream.writeObject(questions.get(i));
        	answear = socketReader.nextLine();
        	
        	if(answear.equals(questions.get(i).getcorrectAnswear())) {
        		objectOutputStream.writeObject("ok");
        	} else {
        		objectOutputStream.writeObject("incorrect");
        		break;
        	}
        }
        
        
        
		
		server_sock.close();
		sock.close();
		socketReader.close(); 
		socketEmitter.close(); 
		

	} 

}



/*	public static void main(String[] args) throws IOException {
		int number, temp;
		ServerSocket server_sock = new ServerSocket(8082);
		Socket sock = server_sock.accept();
		Scanner sc = new Scanner(sock.getInputStream());
		number = sc.nextInt();
		temp = number * 2;
		PrintStream p = new PrintStream(sock.getOutputStream());
		p.println(temp);
		
		server_sock.close();
		sock.close();
		sc.close();
		

	} */