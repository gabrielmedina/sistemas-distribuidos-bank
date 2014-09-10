import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Tratamento extends Thread {
	private Socket cliente;
	private String name;
	
	public Tratamento(Socket cliente, String name) {
		this.cliente = cliente;
		this.name = name;
	}
	
	public void run(){
		System.out.println(this.name + ": " + cliente.getInetAddress().getHostAddress() + " conectou-se.");
		
		while(true){
			Scanner s = null;
			
			try {
				s = new Scanner(cliente.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}     
			
			String texto = new String();
	        
		   	texto = s.nextLine();
		      
		   	PrintStream saida;
		   	
			try {
				int num = Integer.parseInt(texto.substring(0,1));
				String msg = texto.substring(1,texto.length());
				
				saida = new PrintStream(cliente.getOutputStream());
				
				if(num == Menu.MAIUSCULO.getValor()){
					saida.println(msg.toUpperCase());
			   	} else {
			   		saida.println(msg.toLowerCase());
			   	}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
