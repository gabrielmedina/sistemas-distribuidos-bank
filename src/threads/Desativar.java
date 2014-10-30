package threads;

import java.util.Scanner;

import models.ArrayMap;
import models.Map;

public class Desativar extends Thread {
	private ArrayMap caixas;
	
	public Desativar(ArrayMap caixas) {
		this.caixas = caixas;
	}
	
	public void envia(){		
		try {
			Scanner teclado = new Scanner(System.in);
			
			System.out.println("\nMenu -----------------");
			System.out.println("1 - Listar");
	        System.out.println("2 - Desativar");
	       
	        System.out.print("\nDigite o número da opção desejada: ");
			int opcao = Integer.parseInt(teclado.nextLine());
			
			switch (opcao) {			
				case 1:
					caixas.listar();					
					break;
					
				case 2:					
					System.out.print("\nDigite o nome do caixa para desativar: ");
					String n = teclado.nextLine();
					caixas.desativar(n);
					break;

				default:
					break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public void run(){		
		while(true){
			envia();
		}
	}
}
