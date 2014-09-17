
public enum Menu {
	DEPOSITAR(1), SACAR(2), SALDO(3), EXTRATO(4);
	
	private final int valor;
	
	private Menu(int valor){
		this.valor = valor;
	}
	
	public int getValor(){
		return this.valor;
	}
}