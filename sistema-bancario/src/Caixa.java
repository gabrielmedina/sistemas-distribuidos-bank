import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Caixa {
   public static void main(String[] args) throws UnknownHostException, IOException {
     
	 Socket caixa = new Socket("127.0.0.1", 12345);
     System.out.println("O caixa se conectou ao servidor!");
     
     Scanner teclado = new Scanner(System.in);
     PrintStream saida = new PrintStream(caixa.getOutputStream());
     
     System.out.print("Conta: ");
     String conta = teclado.nextLine();
     
     System.out.print("Senha: ");
     String senha = teclado.nextLine();
     
     saida.println(conta + "-" + senha);
     
     BufferedReader verificacao = new BufferedReader(new InputStreamReader(caixa.getInputStream()));
     System.out.println(verificacao.readLine());
     
     
     
//     System.out.println("Menu -----------------");
//     System.out.println("1 - Depositar");
//     System.out.println("2 - Sacar");
//     System.out.println("3 - Saldo");
//     System.out.println("4 - Extrato");
//     System.out.println("5 - Sair");
//     System.out.println("9 - Ajuda");
//     
//     int opcao = teclado.nextInt();
//     
//     while (teclado.hasNextLine()) {
//    	 saida.println(opcao);
//       
//         BufferedReader teste = new BufferedReader(new InputStreamReader(caixa.getInputStream()));
//         System.out.println(teste.readLine());
//     }
//     
     saida.close();
     teclado.close();
     caixa.close();
   }
}