package negocio;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(1234);
			System.out.println("Servidor em rede");
			while (true) {
				Socket cliente = server.accept();
                                System.out.println("Cliente [" + cliente.getInetAddress() + "] conectado"  );
				Thread thread = new Thread(new AdvinhaPalavra(cliente));
				thread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
