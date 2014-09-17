import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Tratamento extends Thread {
	private Socket caixa;
	private String name;
	
	public Tratamento(Socket caixa, String name) {
		this.caixa = caixa;
		this.name = name;
	}
	
	public void run(){
		System.out.println(this.name + ": " + caixa.getInetAddress().getHostAddress() + " conectou-se.");
		
		while(true){
			Scanner s = null;
			
			try {
				s = new Scanner(caixa.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			
//			String texto = new String();	        
//		   	
//			texto = s.nextLine();
//		      
//		   	PrintStream saida;
//		   	
//			try {
//				int num = Integer.parseInt(texto.substring(0,1));
//				String msg = texto.substring(1,texto.length());
//				
//				saida = new PrintStream(caixa.getOutputStream());
//				
//				switch(num){
//					case 1:
//						
//						break;
//				}
//				
////				if(num == Menu.MAIUSCULO.getValor()){
////					saida.println(msg.toUpperCase());
////			   	} else {
////			   		saida.println(msg.toLowerCase());
////			   	}
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	// Métodos	
	private String verificarLogin(String texto){		
		String[] dados = texto.split("-");
		
		
		
		return "a";
	}
}
