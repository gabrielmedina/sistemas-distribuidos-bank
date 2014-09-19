import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Caixa {
   public static void main(String[] args) throws UnknownHostException, IOException {
     
	 Socket caixa = new Socket("127.0.0.1", 12345);
     System.out.println("O caixa se conectou ao servidor!\n");
     
     // login
     Scanner teclado = new Scanner(System.in);
     
     System.out.println("Acesse sua conta -----------------");
     
     System.out.print("Número: ");
     String conta = teclado.nextLine();
     
     System.out.print("Senha: ");
     String senha = teclado.nextLine();
     
     PrintStream envia = new PrintStream(caixa.getOutputStream());     
     envia.println(conta + "-" + senha);
     
     Scanner recebe = new Scanner(caixa.getInputStream());
     String [] autenticacao = recebe.nextLine().split("-"); 
     
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
	         opcao = teclado.nextInt();
	         
	         envia.println(opcao);
    	 }
     } else {
    	 System.out.println("\nOps! -----------------");
    	 System.out.println(autenticacao[1]);
     }
     
     recebe.close();
     teclado.close();
     caixa.close();
   }
}