package views;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import models.ArrayMap;
import models.Map;
import threads.Desativar;
import threads.Desligar;

public class Controlador {
	public static void main(String[] args) throws IOException {
		
		 ServerSocket servidor = new ServerSocket(54321);
		 System.out.println("Controlador pronto!\n");
		 
		 ArrayMap caixas = new ArrayMap();
		 
		 int i = 1;
		 
		 Desativar desativar = new Desativar(caixas);
		 desativar.run();
		 
		 while (true) {
			Socket caixa = servidor.accept();
			
			Map temp = new Map(caixa);
			temp.setNome("caixa" + i);
			caixas.add(temp);
			
			desativar.adicionar(temp);
			
			Desligar desligar = new Desligar(caixas, temp);
			desligar.start();
					
			i++;
		}
		
	}	
}
