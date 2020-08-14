package conex.socket.jframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.*;

public class frmcliente extends JFrame implements ActionListener {
JTextField txtmensaje;
JButton btnenviar;
	 
	
	
	public frmcliente() {
		// agregando el texto al formulario
		txtmensaje= new JTextField();
		txtmensaje.setBounds(10,10,200,20);
		add(txtmensaje);
		
		btnenviar = new JButton("enviar");
		btnenviar.addActionListener(this);
		btnenviar.setBounds(10,40,150,20);
		add(btnenviar);
		setLayout(null);
		setSize(400,400);
		setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
	}

	public static void main(String[] args) {
	new frmcliente();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()== btnenviar)
		{
			try {
				Socket cli=new Socket("127.0.0.1", 9090);
				DataOutputStream flujo=new DataOutputStream(cli.getOutputStream());
				flujo.writeUTF(txtmensaje.getText());
				cli.close();
				
			} catch (Exception e) {

			JOptionPane.showMessageDialog(null,"error en cliente"+e.getMessage());
			}
			
		
		}
		
	}

}
