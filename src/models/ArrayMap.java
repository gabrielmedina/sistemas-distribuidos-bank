package models;

import java.util.ArrayList;

public class ArrayMap {
	ArrayList<Map> arrayMap;

	public ArrayMap() {
		arrayMap = new ArrayList<Map>();
	}
	
	public void add(Map map){
		arrayMap.add(map);
	}
	
	public void list(){
		System.out.println("\n*************************\n" + arrayMap.size() + " Caixa(s) conectado(s).");
		for(Map map : arrayMap){
			System.out.println("  -> " + map.getNome());
		}
		System.out.println("*************************\n");
	}
	
	public void delete(String nome){
		Map temp = null;
		for(Map map : arrayMap){
			if(map.getNome().equals(nome)){
				temp = map;
			}
		}
		if(temp != null){
			arrayMap.remove(temp);
		}else{
			System.out.println("NULO");
		}
	}
	
	public void desativar(String nome){
		for(Map map : arrayMap){
			if(map.getNome().equals(nome)){
				map.enviar("Desativando Caixa");
				arrayMap.remove(map);
				System.out.println("\n*******************************\n" + nome +
						" Desativado com Sucesso\n*******************************\n");
			}
		}
	}
}
