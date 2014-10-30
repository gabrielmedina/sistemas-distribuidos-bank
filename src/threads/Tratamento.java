package threads;
import java.net.Socket;

import models.Banco;
import models.Conta;
import models.Map;


public class Tratamento extends Thread {
	// Atributos
	private Socket caixa;
	private String name;
	private Banco banco;
	
	// Construtor
	public Tratamento(Socket caixa, String name, Banco banco) {
		this.caixa = caixa;
		this.name = name;
		this.banco = banco;
	}
	
	// Métodos
	public void run(){
		System.out.println(this.name + ": " + caixa.getInetAddress().getHostAddress() + " conectou-se.");
		
		// Criando um obj Map, para mapear o Socket
		Map mapCaixa = new Map(this.caixa);
		
		while(true){		
			String texto = new String();
			
			// Recebendo mensagem contendo o número da conta e a senha
			texto = mapCaixa.receber();
		   	
			// Enviando mensagem contendo o retorno do método verificarLogin
		   	mapCaixa.enviar(verificarLogin(texto, banco));
		   	
		   	if(verificarLogin(texto, banco).split("-")[0].equals("true")){
		   		// Recebendo mensagem contendo os dados, de acordo com a operação escolhida
				texto = mapCaixa.receber();
				
				// Quebrando a mensagem recebida, de acordo com a ocorrencia "-"
				String resultado[] = texto.split("-");
				
				// Recebendo o primeiro item do vetor e transformando-o em um valor inteiro
				int opcao = Integer.parseInt(resultado[0]);
				
				// Se o valor da operação for diferente de 9
				while(opcao != 9){						
					switch(opcao){
						// Depositar 
						case 1:
							// Enviando uma mensagem contendo o retorno do método depositar
							mapCaixa.enviar(depositar(resultado[1], Double.parseDouble(resultado[2]), banco));
							break;
							
						// Sacar
						case 2:
							// Enviando uma mensagem contendo o retorno do método sacar
							mapCaixa.enviar(sacar(resultado[1], Double.parseDouble(resultado[2]), banco));
							break;
							
						// Saldo
						case 3:
							// Enviando uma mensagem contendo o retorno do método saldo
							mapCaixa.enviar(saldo(resultado[1], banco));
							break;
							
						// Extrato
						case 4:
							// Enviando uma mensagem contendo o retorno do método extrato
							mapCaixa.enviar(extrato(resultado[1], banco));
							break;
							
						// Ajuda
						case 8:
							System.out.println("Ajuda");
							break;
							
						// Sair
						case 9:
							System.out.println("Sair");
							break;
					}
					
					if(opcao != 9 && opcao != 8){
						// Recebendo mensagem contendo os dados, de acordo com a operação escolhida
						texto = mapCaixa.receber();
						
						resultado = texto.split("-");
						
						opcao = Integer.parseInt(resultado[0]);
					}
				}
		   	}
		}
	}
	
	// Verificar Login
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
	
	// Depositar
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
	
	// Sacar
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
	
	// Saldo
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
	
	// Extrato
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
