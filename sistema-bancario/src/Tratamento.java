import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Tratamento extends Thread {
	// atributos
	private Socket caixa;
	private String name;
	private Banco banco;
	
	// contrutor
	public Tratamento(Socket caixa, String name, Banco banco) {
		this.caixa = caixa;
		this.name = name;
		this.banco = banco;
	}
	
	// métodos
	public void run(){
		System.out.println(this.name + ": " + caixa.getInetAddress().getHostAddress() + " conectou-se.");
		
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
								envia.println(saldo(resultado[1], banco));
								break;
							// extrato
							case 4:
								envia.println(extrato(resultado[1], banco));
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
						
						if(opcao != 9 && opcao != 8){
							texto = recebe.nextLine();
							resultado = texto.split("-");
							
							opcao = Integer.parseInt(resultado[0]);
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
		
		if(dados[0].equalsIgnoreCase("")){
			return "false-Ops! Campo conta está em branco";
		}
		
		if(dados[1].equalsIgnoreCase("")){
			return "false-Ops! Campo senha está em branco";
		}
		
		for (Conta conta : banco.getContas()){
			if(conta.getNumero().equalsIgnoreCase(dados[0]) && conta.getSenha().equalsIgnoreCase(dados[1])){
				return "true-" + conta.getTitular() + "-" + conta.getNumero();
			}
		}
		
		return "false-Ops! Sua autenticação não pode ser realizada.";
	}
	
	private String depositar(String numero, double valor, Banco banco){
		if(numero.equalsIgnoreCase("")){
			return "false-Ops! Campo conta está em branco";
		}
		
		if(valor == 0){
			return "false-Ops! Campo valor está em branco";
		}
		
		if(valor < 0){
			return "false-Ops! Valor de depósito negativo";
		}
		
		for(int i = 0; i < banco.getContas().size(); i++){
			Conta conta = banco.getContas().get(i);
			
			if(conta.getNumero().equalsIgnoreCase(numero)){				
				conta.setSaldo(conta.getSaldo() + valor);
				conta.setExtrato(conta.getExtrato() + "-Depósito: " + valor);
				banco.getContas().set(i, conta);
			
				return "true-" + valor + "-" + conta.getTitular();
			}
		}
		
		return "false-Ops! O depósito não foi efetuado.";
	}
	
	private String sacar(String numero, double valor, Banco banco){
		if(numero.equalsIgnoreCase("")){
			return "false-Ops! Campo conta está em branco";
		}
		
		if(valor == 0){
			return "false-Ops! Campo valor está em branco";
		}
		
		if(valor < 0){
			return "false-Ops! Valor de depósito negativo";
		}
		
		if(valor > 0){
			for(int i = 0; i < banco.getContas().size(); i++){
				Conta conta = banco.getContas().get(i);
				
				if(conta.getNumero().equalsIgnoreCase(numero)){
					if(conta.getSaldo() < valor){
						return "false-Ops! Saldo insuficiente.";
					} else {
						conta.setSaldo(conta.getSaldo() - valor);
						conta.setExtrato(conta.getExtrato() + "-Saque: " + valor);
						banco.getContas().set(i, conta);
						
						return "true-" + conta.getSaldo() + "-" + conta.getTitular();
					}
				}
			}
		}
		
		return "false-Ops! O saque não foi efetuado.";
	}
	
	private String saldo(String numero, Banco banco){
		if(numero.equalsIgnoreCase("")){
			return "false-Ops! Campo conta está em branco";
		}
		
		for(int i = 0; i < banco.getContas().size(); i++){
			Conta conta = banco.getContas().get(i);
			
			if(conta.getNumero().equalsIgnoreCase(numero)){
				return "true-" + conta.getSaldo();
			}
		}
		
		return "false-Ops! Algo de errado não está certo";
	}
	
	private String extrato(String numero, Banco banco){
		if(numero.equalsIgnoreCase("")){
			return "false-Ops! Campo conta está em branco";
		}
		
		for(int i = 0; i < banco.getContas().size(); i++){
			Conta conta = banco.getContas().get(i);
			
			if(conta.getNumero().equalsIgnoreCase(numero)){
				return "true" + conta.getExtrato();
			}
		}
		
		return "false-Ops! Algo de errado não está certo";
	}
}
