import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
//	private static ArrayList<Conta> contas;
//	
//	public Servidor(){
//		contas = new ArrayList<Conta>();
//		
//		Conta t = new Conta("1", "1", 50);
//		contas.add(t);
//		contas.add(new Conta("2", "2", 0));
//		contas.add(new Conta("3", "3", 200));
//		contas.add(new Conta("4", "4", 500));
//		contas.add(new Conta("5", "5", 10000));
//	}
	
	public static void main(String[] args) throws IOException {
	   
		ServerSocket servidor = new ServerSocket(12345);
		System.out.println("Porta 12345 aberta!");
		
		Teste t = new Teste();
		t.teste();
     
//		int i = 1;
//     
//		while (true) {
//			Socket cliente = servidor.accept();
//    	 
//			Tratamento tratamento = new Tratamento(cliente, contas, "caixa_" + i);
//			tratamento.start();
//			i++;
//		}
   
//		s.close();
//		servidor.close();
//		cliente.close();
	}
 }