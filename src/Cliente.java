import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
   public static void main(String[] args) throws UnknownHostException, IOException {
     
	 Socket cliente = new Socket("127.0.0.1", 12345);
     System.out.println("O cliente se conectou ao servidor!");
     
     Scanner teclado = new Scanner(System.in);
     PrintStream saida = new PrintStream(cliente.getOutputStream());
     
     System.out.println("Menu ---------------------");
     System.out.println("1 - Retornar em Maiúsculo.");
     System.out.println("2 - Retornar em Minúscolo.\n");
     
     int valor = teclado.nextInt();
     
     while (teclado.hasNextLine()) {         
         if(valor == Menu.MAIUSCULO.getValor()){
        	 saida.println(Menu.MAIUSCULO.getValor() + teclado.nextLine());
         } else {
        	 saida.println(Menu.MINUSCULO.getValor() + teclado.nextLine());
         }
       
         BufferedReader teste = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
         System.out.println(teste.readLine());
     }
     
     saida.close();
     teclado.close();
     cliente.close();
   }
}