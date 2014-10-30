package models;
import java.util.ArrayList;

public class Banco {
	// Atributos
	private ArrayList<Conta> contas;
	
	// Construtor
	public Banco() {
		this.contas = new ArrayList<Conta>();
		
		// Contas default
		adicionarConta(new Conta("Gabriel Medina", "1", "1", 100));
		adicionarConta(new Conta("Luiz Picolo", "2", "2", 200));
		adicionarConta(new Conta("Rick Lima", "3", "3", 300));
		adicionarConta(new Conta("Ronan Miotto", "4", "4", 400));
	}
	
	// Adicionar Conta
	public void adicionarConta(Conta conta){
		this.contas.add(conta);
	}
	
	// Pegar ArrayList de Contas
	public ArrayList<Conta> getContas(){
		return this.contas;
	}
}
