package models;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Map {
	// Atributos
	private String nome;
	private Socket sockect;
	
	PrintStream envia;
	Scanner recebe;
	
	// Contrutor
	public Map(Socket socket) {
		this.sockect = socket;
		
		try {
			envia = new PrintStream(sockect.getOutputStream());
			recebe = new Scanner(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Enviar mensagens
	public void enviar(String texto){
		envia.println(texto);
	}
	
	// Receber mensagens
	public String receber(){
		return recebe.nextLine();
	}
	
	// Fechando o socket
	public void fechar(){
		try {
			sockect.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Socket getSockect() {
		return sockect;
	}

	public void setSockect(Socket sockect) {
		this.sockect = sockect;
	}
}
