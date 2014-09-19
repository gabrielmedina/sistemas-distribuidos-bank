import java.util.ArrayList;


public class Teste {
	private ArrayList<Conta> contas;
	
	public Teste() {
		this.contas = new ArrayList<Conta>();		
		this.contas.add(new Conta("1", "1", 0));
	}
	
	public void teste(){
		for (Conta conta : contas) {
			System.out.println(conta.getNumero());
		}
	}
}
