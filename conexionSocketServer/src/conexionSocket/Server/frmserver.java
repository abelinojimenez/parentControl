package conexionSocket.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class frmserver extends JFrame implements Runnable{
	JTextArea txtmensajes;
	
	public frmserver() {
	txtmensajes=new JTextArea();	
	txtmensajes.setBounds(10,10,400,400);
	add(txtmensajes);
	setLayout(null);
	setSize(420,420);
	setVisible(true);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	// creamos un hilo para llamar a el metodo ru que es un hilo
	Thread hilo= new Thread(this);
	hilo.start();
	
	}

	public static void main(String[] args) {
	new frmserver();

	}

	@Override
	public void run() {
		try {
			// se asigna el puesto el cual se abrira del servidor
			ServerSocket servidor=new ServerSocket(9090);
			// se crea un socket
			Socket cli;
			/* se abre un puesto servidor een el socket*/
			while(true)
			{
				/* en espera de alguien se conecte*/
				cli= servidor.accept();
				DataInputStream flujo=
					new DataInputStream(cli.getInputStream());
				// luego leo lo que me han enviado.
				String msg=flujo.readUTF();
				txtmensajes.append("\n"+cli.getInetAddress()+":"+msg);
				cli.close();
				if(msg.equalsIgnoreCase("fin"))
				{
					servidor.close();
					break;
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
