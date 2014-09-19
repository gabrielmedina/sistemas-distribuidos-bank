import java.util.ArrayList;


public class Banco {
	// atributos
	private ArrayList<Conta> contas;
	
	// construtor
	public Banco() {
		this.contas = new ArrayList<Conta>();
		
		// adicionando algumas contas default
		adicionarConta(new Conta("Gabriel Medina", "1", "1", 100));
		adicionarConta(new Conta("Luiz Picolo", "2", "2", 200));
		adicionarConta(new Conta("Rick Lima", "3", "3", 300));
		adicionarConta(new Conta("Ronan Miotto", "4", "4", 400));
	}
	
	// adicionar conta
	public void adicionarConta(Conta conta){
		this.contas.add(conta);
	}
	
	// pegar contas
	public ArrayList<Conta> getContas(){
		return this.contas;
	}
}
