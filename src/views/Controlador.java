package views;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import models.ArrayMap;
import models.Map;
import threads.Desativar;

public class Controlador {
	public static void main(String[] args) throws IOException {	
		
		// Criando um obj ServerSocket
		 ServerSocket servidor = new ServerSocket(23456);
		 System.out.println("Controlador pronto!");
		 
		 // Criando um ArrayMap
		 ArrayMap caixas = new ArrayMap();
			
		 int i = 1;
		 
		 while (true) {
			// Criando um obj Socket
			Socket caixa = servidor.accept();
			
			// Criando um obj Map para caixa
			Map mapCaixa = new Map(caixa);
			mapCaixa.setNome("caixa-" + i);
			
			// Adicionando o Map criado ao ArrayMap
			caixas.add(mapCaixa);
			
			// Criando um obj Desativar
			Desativar desativar = new Desativar(caixas);
			// Executando a thread
			desativar.start();			
					
			i++;
		}		
	}	
	
	//	servidor.close();
	//	cliente.close();
}
