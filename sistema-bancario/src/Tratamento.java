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
					
					texto = recebe.nextLine();
					String resultado[] = texto.split("-");
					
					int opcao = Integer.parseInt(resultado[0]);
					
					while(opcao != 9){						
						switch(opcao){
							// depositar 
							case 1:
								envia.println(depositar(resultado[1], Double.parseDouble(resultado[2]), banco));
								break;
							// sacar
							case 2:
								envia.println(sacar(resultado[1], Double.parseDouble(resultado[2]), banco));
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
		
		return "false-Ops! Sua autenticação não pode ser realizada.";
	}
	
	private String depositar(String numero, double valor, Banco banco){		
		if(valor > 0){
			for (Conta conta : banco.getContas()){
				if(conta.getNumero().equalsIgnoreCase(numero)){
					conta.setSaldo(conta.getSaldo() + valor);
					
					//return "true-" + valor + "-" + conta.getTitular();
				}
			}
			
			for (Conta conta : banco.getContas()){
				if(conta.getNumero().equalsIgnoreCase(numero)){
					System.out.println("Teste: " + conta.getSaldo());
				}
			}
		}
		
		return "false-Ops! O depósito não foi efetuado.";
	}
	
	private String sacar(String numero, double valor, Banco banco){		
		if(valor > 0){
			for (Conta conta : banco.getContas()){
				if(conta.getNumero().equalsIgnoreCase(numero)){
					if(conta.getSaldo() < valor){
						return "false-Ops! Saldo insuficiente.";
					} else {
						conta.setSaldo(conta.getSaldo() - valor);						
						return "true-" + conta.getSaldo() + "-" + conta.getTitular();
					}
				}
			}
		}
		
		return "false-Ops! O saque não foi efetuado.";
	}
}
