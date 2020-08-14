import java.io.FileInputStream;
import java.io.IOException;


public class LeerFichero {
	String Ruta;
	FileInputStream Intext;

	public LeerFichero(String Ruta) {
		// leemos el fichero con la ruta especificada.
		this.Ruta=Ruta;
	}
	public String read()
	{
		/* creamos una cadena en la cual se va almacenar lo que leeamos*/
	String texto=""; 	
	try {
		Intext=new FileInputStream(Ruta);
		int ascii;
		/*leermos el archivo asta que nos retorne -1 que significa fin de archivo*/
		while((ascii=Intext.read())!=-1)
		{
			char caracter=(char)ascii;	
			texto+=caracter;	
		}
		// se cierra el archivo
            	Intext.close();
		

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return texto;
	}
	

}
