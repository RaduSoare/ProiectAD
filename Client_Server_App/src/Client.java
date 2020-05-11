import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		int number, temp;
		Scanner sc = new Scanner(System.in);
		Socket sock = new Socket("127.0.0.1", 8080);
		Scanner sc1 = new Scanner(sock.getInputStream());
		System.out.println("Enter number");
		number = sc.nextInt();
		PrintStream p = new PrintStream(sock.getOutputStream());
		p.println(number);
	/*	temp = sc1.nextInt();
		System.out.println(temp); */
		
		
		sc.close();
		sock.close();
		sc1.close();
		

	}

}
