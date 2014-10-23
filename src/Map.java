import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Map {
	private int nome;
	private Socket sockect;
	
	PrintStream envia;
	Scanner recebe;
	
	public Map(Socket socket) {
		this.sockect = socket;
		
		try {
			envia = new PrintStream(sockect.getOutputStream());
			recebe = new Scanner(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enviar(String texto){
		envia.println(texto);
	}
	
	public String receber(){
		return recebe.nextLine();
	}
	
	public int getNome() {
		return nome;
	}

	public void setNome(int nome) {
		this.nome = nome;
	}
	
	public Socket getSockect() {
		return sockect;
	}

	public void setSockect(Socket sockect) {
		this.sockect = sockect;
	}
}
