import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Caixa {
   public static void main(String[] args) throws UnknownHostException, IOException {
     
	 Socket socketBanco = new Socket("127.0.0.1", 12345);
	 
	 Map mapBanco = new Map(socketBanco);
	 
     System.out.println("O caixa se conectou ao servidor!\n");
     
     // login
     Scanner teclado = new Scanner(System.in);
     
     boolean ativo = true;
	 
	 while(ativo == true){     
	     System.out.println("Acesse sua conta -----------------");
	     
	     System.out.print("Número: ");
	     String conta = teclado.nextLine();
	     
	     System.out.print("Senha: ");
	     String senha = teclado.nextLine();
	     
	     mapBanco.enviar(conta + "-" + senha);
	     
	     String [] autenticacao = mapBanco.receber().split("-"); 
	     
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
		         		
		         		System.out.print("Digite o valor do depósito: ");
		         		double depositoValor = Double.parseDouble(teclado.nextLine());
		         			
		         		mapBanco.enviar(opcao + "-" + depositoConta + "-" + depositoValor);
		         		
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
		         		
		         		System.out.print("Digite o valor do saque: ");
		         		double saqueValor = Double.parseDouble(teclado.nextLine());
		         		
		         		mapBanco.enviar(opcao + "-" + saqueConta + "-" + saqueValor);
		         		
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
		         		mapBanco.enviar(opcao + "-" + autenticacao[2]);
		         		
		         		String saldoResultado[] = mapBanco.receber().split("-");
		         		
		         		if(saldoResultado[0].equalsIgnoreCase("true")){
		         			System.out.println("Saldo: " + saldoResultado[1]);
		         		} else {
		         			System.out.println("\n" + saldoResultado[1]);
		         		}
		         		
		         		break;
		         		
		         	case 4:
		         		System.out.println("\nExtrato --------------------");	         		
		         		mapBanco.enviar(opcao + "-" + autenticacao[2]);
		         		
		         		String extratoResultado[] = mapBanco.receber().split("-");
		         		
		         		if(extratoResultado[0].equalsIgnoreCase("true")){
		         			for(int i = 1; i < extratoResultado.length; i++){
		         				System.out.println(extratoResultado[i]);
		         			}
		         		} else {
		         			System.out.println("\n" + extratoResultado[1]);
		         		}
		         		
		         	case 9:
		         		System.out.println("\nOpa! Você desconectou sua conta\n\n");
		         		mapBanco.enviar(String.valueOf(opcao));
		         }
	    	 }
	     }
     }
     
//     recebe.close();
//     teclado.close();
//     caixa.close();
   }
}