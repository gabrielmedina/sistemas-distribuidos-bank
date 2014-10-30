package threads;

import java.net.Socket;

import models.Map;

public class TratamentoCaixa extends Thread {
	private Socket caixa;
	private Map map;
	private Boolean status;
	
	public TratamentoCaixa(Socket caixa) {
		this.caixa = caixa;
		this.map = new Map(caixa);
		this.status = true;
	}
	
	public void ouvir(){
		try {
			String msg = map.receber();
			status = false;
			System.out.println("*******************************\nCaixa encerrado Remotamente.\n*******************************");
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
