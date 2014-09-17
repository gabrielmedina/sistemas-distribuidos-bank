import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
	private ArrayList<Conta> contas;
	
	public static void main(String[] args) throws IOException {
	   
		ServerSocket servidor = new ServerSocket(12345);
		System.out.println("Porta 12345 aberta!");
     
		int i = 1;
     
		while (true) {
			Socket cliente = servidor.accept();
    	 
			Tratamento tratamento = new Tratamento(cliente, "caixa_" + i);
			tratamento.start();
			i++;
		}
   
//		s.close();
//		servidor.close();
//		cliente.close();
	}
 }