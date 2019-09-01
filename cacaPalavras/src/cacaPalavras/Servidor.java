package cacaPalavras;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		try {
			ServerSocket servidor = new ServerSocket(1234);
			System.out.println("Servidor em rede");
			while (true) {
				Socket cliente = servidor.accept();
                                System.out.println("Cliente IP: [" + cliente.getInetAddress().toString().split("/")[1] + "] Porta: " + cliente.getLocalPort() + " - conectado"  );
                                Thread thread = new Thread(new CacaPalavra(cliente));
				thread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
