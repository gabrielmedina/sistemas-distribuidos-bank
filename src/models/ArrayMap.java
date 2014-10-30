package models;

import java.util.ArrayList;

public class ArrayMap {
	private ArrayList<Map> arrayMap;

	public ArrayMap() {
		this.arrayMap = new ArrayList<Map>();
	}
	
	public void add(Map map){
		this.arrayMap.add(map);
	}
	
	public void listar(){
		if(arrayMap.size() > 0){
			System.out.println("\nLista de caixas");
			
			for(Map map : this.arrayMap){
				System.out.println(" " + map.getNome());
			}
		} else {
			System.out.println("\nNenhum caixa conectado!\n");
		}
	}
	
	public String deletar(String nome){
		for(Map map : this.arrayMap){
			if(map.getNome().equals(nome)){
				this.arrayMap.remove(map);
				return "true";
			}
		}
		
		return "false";
	}
	
	public String desativar(String nome){
		for(Map map : this.arrayMap){
			if(map.getNome().equalsIgnoreCase(nome)){
				map.enviar("false");
				
				this.arrayMap.remove(map);
				
				System.out.println("\n" + nome + " desativado com sucesso!\n");
				
				return "true";
			}
		}
		
		return "false";
	}
}
