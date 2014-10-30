package threads;

import java.net.Socket;

import models.Map;

public class TratamentoCaixa extends Thread {
	// Atributos
	private Socket caixa;
	private Map map;
	private Boolean status;
	
	// Contrutor
	public TratamentoCaixa(Socket caixa) {
		this.caixa = caixa;
		this.map = new Map(this.caixa);
		this.status = true;
	}
	
	// Métodos
	public void ouvir(){
		try {
			String msg = map.receber();			
			this.status = false;			
			System.out.println("\n\nEsse caixa está desativado!\n\n");
			
			System.exit(0);
		} catch (Exception e) {
			
		}
	}
	
	public void run(){
		
		while(true){	
			ouvir();
		}
	}
	
	public Boolean getStatus(){
		return status;
	}
}
