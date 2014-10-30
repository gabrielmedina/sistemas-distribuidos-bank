package threads;

import models.ArrayMap;
import models.Map;


public class Desligar extends Thread {
	Map map;
	ArrayMap arrayMap;
	
	
	public Desligar(ArrayMap arrayMap, Map map) {
		this.map = map;
		this.arrayMap = arrayMap;
	}
	
	public void ouvir(){
		try {
			String msg = map.receber();
			arrayMap.delete(map.getNome());
			System.out.println("Mensagem recebida: " + msg);
		} catch (Exception e) {
			
		}	
	}	
	
	public void run(){	
		ouvir();
	}
}
