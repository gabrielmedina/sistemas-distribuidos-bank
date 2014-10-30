package models;

public class Conta {
	// Atributos
	private String titular;
	private String numero;
	private String senha;
	private double saldo;
	private String extrato;
	
	// Contrutor
	public Conta(String titular, String numero, String senha, double saldo){
		this.titular = titular;
		this.numero = numero;
		this.senha = senha;
		this.saldo = saldo;
		this.extrato = "";
	}
	
	// Getters e Setters
	public String getTitular() {
		return titular;
	}

	public String getExtrato() {
		return extrato;
	}

	public void setExtrato(String extrato) {
		this.extrato = extrato;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
