package threads;

import java.util.Scanner;

import models.ArrayMap;
import models.Map;

public class Desativar {
	Map map;
	Boolean status;
	ArrayMap caixas;	
	
	public Desativar(ArrayMap caixas) {
		this.caixas = caixas;
		this.status = true;
	}
	
	public void envia(){		
		try {
			Scanner teclado = new Scanner(System.in);
			
			System.out.println("Opera��es Dispon�veis:\n-> 1 - Listar Caixas\n-> 2 - Desativar Caixa");
			String opcao = teclado.nextLine();
			
			switch (opcao) {
			
				case "1":
					caixas.list();
					
					break;
					
				case "2":
					
					System.out.println("\nInforme o nome do caixa para ser desativado: ");
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
