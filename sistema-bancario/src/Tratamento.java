import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Tratamento extends Thread {
	// atributos
	private Socket caixa;
	private String name;
	
	// contrutor
	public Tratamento(Socket caixa, String name) {
		this.caixa = caixa;
		this.name = name;
	}
	
	// métodos
	public void run(){
		System.out.println(this.name + ": " + caixa.getInetAddress().getHostAddress() + " conectou-se.");
		
		Banco banco = new Banco();
		
		while(true){
			Scanner recebe = null;
			
			try {
				recebe = new Scanner(caixa.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String texto = new String();
			texto = recebe.nextLine();
			
		   	PrintStream envia;
		   	
		   	try {
		   		envia = new PrintStream(caixa.getOutputStream());
		   		envia.println(verificarLogin(texto, banco));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   	
		   	if(verificarLogin(texto, banco).split("-")[0].equals("true")){
		   		try {
					envia = new PrintStream(caixa.getOutputStream());
					
					int opcao = 0;
					
					while(opcao != 9){
						opcao = Integer.parseInt(recebe.nextLine());
						
						switch(opcao){
							// depositar
							case 1:
								System.out.println("Depositar");
								break;
							// sacar
							case 2:
								System.out.println("Sacar");
								break;
							// saldo
							case 3:
								System.out.println("Saldo");
								break;
							// extrato
							case 4:
								System.out.println("Extrato");
								break;
							// ajuda
							case 8:
								System.out.println("Ajuda");
								break;
							// sair
							case 9:
								System.out.println("Sair");
								break;
						}			
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   	}
		}
	}
	
	private String verificarLogin(String texto, Banco banco){
		String[] dados = texto.split("-");
		
		for (Conta conta : banco.getContas()){
			if(conta.getNumero().equals(dados[0]) && conta.getSenha().equals(dados[1])){
				return "true-" + conta.getTitular();
			}
		}
		
		return "false-Sua autenticação não foi realizada.";
	}
}
