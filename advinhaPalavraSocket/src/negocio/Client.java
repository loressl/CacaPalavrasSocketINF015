package negocio;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			Socket client = new Socket("", 1234);
			Scanner entrada = new Scanner(client.getInputStream());
			Scanner entradaUsuario = new Scanner(System.in);
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			System.out.println(entrada.nextLine());
			
			while (true) {
				System.out.println("Digite uma letra: ");
				out.println(entradaUsuario.nextLine());
				System.out.println(entrada.nextLine());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
