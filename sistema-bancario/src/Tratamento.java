import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Tratamento extends Thread {
	private Socket caixa;
	private String name;
	private ArrayList<Conta> contas;
	
	public Tratamento(Socket caixa, ArrayList<Conta> contas, String name) {
		this.caixa = caixa;
		this.name = name;
		this.contas = contas;
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
			
			String texto = new String();		   	
			texto = s.nextLine();
			
		   	PrintStream saida;
		   	
		   	try {
				saida = new PrintStream(caixa.getOutputStream());
				saida.println(verificarLogin(texto));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   	
//			try {
//				int num = Integer.parseInt(texto.substring(0,1));
//				String msg = texto.substring(1,texto.length());
//				
//				saida = new PrintStream(caixa.getOutputStream());
				
//				switch(num){
//					case 1:
//						
//						break;
//				}
				
//				if(num == Menu.MAIUSCULO.getValor()){
//					saida.println(msg.toUpperCase());
//			   	} else {
//			   		saida.println(msg.toLowerCase());
//			   	}
				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	// Métodos	
	private String verificarLogin(String texto){		
		String[] dados = texto.split("-");
		
		System.out.println(this.contas);
		
//		for (Conta conta : this.contas) {
//			System.out.println(conta.getNumero());
//			
//			if(conta.getNumero().equals(dados[0]) && conta.getSenha().equals(dados[1])){
//				System.out.println("OPA!!");
//				return "true";
//			}
//		}
		
		return "false";
	}
}
