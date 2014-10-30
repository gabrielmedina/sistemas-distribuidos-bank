package views;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import models.Map;
import threads.TratamentoCaixa;

public class Caixa {
   public static void main(String[] args) throws UnknownHostException, IOException {
     
	 // Criando um obj Socket
	 Socket socketServidor = new Socket("127.0.0.1", 12345);
	 Socket socketControlador = new Socket("127.0.0.1", 23456);
	 
	 // Criando um obj Map, para mapear os eventos Sockets
	 Map mapBanco = new Map(socketServidor);
	 Map mapControlador = new Map(socketControlador);
	 
	 TratamentoCaixa tratamentoCaixa = new TratamentoCaixa(socketControlador);
	 tratamentoCaixa.start();
     
     // Criando obj Scanner para leitura de dados via console
     Scanner teclado = new Scanner(System.in);
     
     boolean ativo = true;
	 
	 while(ativo == true){     
	     System.out.println("Acesse sua conta -----------------");
	     
	     System.out.print("Número: ");
	     String conta = teclado.nextLine();
	     
	     System.out.print("Senha: ");
	     String senha = teclado.nextLine();
	     
	     // Enviando mensagem contendo o número da conta e senha para o servidor
	     mapBanco.enviar(conta + "-" + senha);
	     
	     // Recebendo mensagem contendo o retorno do método de autenticação
	     String [] autenticacao = mapBanco.receber().split("-"); 
	     
	     // Caso a primeira mensagem retornada, divididas de acordo com a ocorrência "-", seja true
	     if(autenticacao[0].equals("true")){
	    	 System.out.println("\nSeja bem-vindo(a), " + autenticacao[1] + ".");
	    	 
	    	 int opcao = 0;
   
	    	 while(opcao != 9){
		    	 System.out.println("\nMenu -----------------");
		         System.out.println("1 - Depositar");
		         System.out.println("2 - Sacar");
		         System.out.println("3 - Saldo");
		         System.out.println("4 - Extrato");
		         System.out.println("8 - Ajuda");
		         System.out.println("9 - Sair");
		         
		         System.out.print("\nDigite o número da opção desejada: ");
		         opcao = Integer.parseInt(teclado.nextLine());
		         
		         switch(opcao){
		         	case 1:
		         		System.out.println("\nDepósito --------------------");
		         		System.out.print("Digite o número da conta: ");
		         		String depositoConta = teclado.nextLine();
		         		
		         		double depositoValor = -1;
		         		
		         		while(depositoValor < 0){
		         			System.out.print("Digite o valor do depósito: ");
		         			depositoValor = Double.parseDouble(teclado.nextLine());
		         		}
		         		
		         		// Enviando mensagem contendo a opção escolhida, o número da conta a ser depositado e o valor
		         		mapBanco.enviar(opcao + "-" + depositoConta + "-" + depositoValor);
		         		
		         		// Recebendo mensagem contendo o retorno do método depositar
		         		String depositoResultado[] = mapBanco.receber().split("-");
		         		
		         		if(depositoResultado[0].equalsIgnoreCase("true")){
		         			System.out.println("\nDepósito realizado com sucesso! -----------");
		         			System.out.println("Titular: " + depositoResultado[2]);
		         			System.out.println("Depositado: " + depositoResultado[1]);
		         		} else {
		         			System.out.println("\n" + depositoResultado[1]);
		         		}
		         		
		         		break;
		         		
		         	case 2:
		         		System.out.println("\nSacar --------------------");
		         		System.out.print("Digite o número da conta: ");
		         		String saqueConta = teclado.nextLine();
		         		
		         		double saqueValor = -1;
		         		
		         		while(saqueValor < 0){
			         		System.out.print("Digite o valor do saque: ");
			         		saqueValor = Double.parseDouble(teclado.nextLine());
		         		}
		         		
		         		// Enviando mensagem contendo a opção escolhida, o número da conta a ser sacado e o valor
		         		mapBanco.enviar(opcao + "-" + saqueConta + "-" + saqueValor);
		         		
		         		// Recebendo mensagem contendo o retorno do método sacar
		         		String saqueResultado[] = mapBanco.receber().split("-");
		         		
		         		if(saqueResultado[0].equalsIgnoreCase("true")){
		         			System.out.println("\nSaque realizado com sucesso! -----------");
		         			System.out.println("Titular: " + saqueResultado[2]);
		         			System.out.println("Saldo: " + saqueResultado[1]);
		         		} else {
		         			System.out.println("\n" + saqueResultado[1]);
		         		}
		         		
		         		break;
		         		
		         	case 3:
		         		System.out.println("\nSaldo --------------------");
		         		
		         		// Enviando mensagem contendo a opção escolhida e o número da conta logada
		         		mapBanco.enviar(opcao + "-" + autenticacao[2]);
		         		
		         		// Recebendo mensagem contendo o retorno do método saldo
		         		String saldoResultado[] = mapBanco.receber().split("-");
		         		
		         		if(saldoResultado[0].equalsIgnoreCase("true")){
		         			System.out.println("Saldo: " + saldoResultado[1]);
		         		} else {
		         			System.out.println("\n" + saldoResultado[1]);
		         		}
		         		
		         		break;
		         		
		         	case 4:
		         		System.out.println("\nExtrato --------------------");
		         		
		         		// Enviando mensagem contendo a opção escolhida e o número da conta logada
		         		mapBanco.enviar(opcao + "-" + autenticacao[2]);
		         		
		         		// Recebendo mensagem contendo o retorno do método extrado
		         		String extratoResultado[] = mapBanco.receber().split("-");
		         		
		         		if(extratoResultado[0].equalsIgnoreCase("true")){
		         			for(int i = 1; i < extratoResultado.length; i++){
		         				System.out.println(extratoResultado[i]);
		         			}
		         		} else {
		         			System.out.println("\n" + extratoResultado[1]);
		         		}
		         		
		         		break;
		         		
		         	case 9:
		         		System.out.println("\nOpa! Você desconectou sua conta\n\n");
		         		
		         		// Enviando mensagem contendo a opção escolhida
		         		mapBanco.enviar(String.valueOf(opcao));
		         		
		         		break;
		         }
	    	 }
	     }
     }
     
//     recebe.close();
//     teclado.close();
//     caixa.close();
   }
}