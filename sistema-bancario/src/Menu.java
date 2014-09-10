
public enum Menu {
	MAIUSCULO(1), MINUSCULO(2);
	
	private final int valor;
	
	private Menu(int valor){
		this.valor = valor;
	}
	
	public int getValor(){
		return this.valor;
	}
}
