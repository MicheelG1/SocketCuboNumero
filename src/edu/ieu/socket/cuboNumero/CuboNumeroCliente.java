package edu.ieu.socket.cuboNumero;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CuboNumeroCliente {

			private BufferedReader entradaTeclado = new BufferedReader(new InputStreamReader(System.in));
			private Socket socket;
			private DataOutputStream out;
			private DataInputStream in;	
			
			public void conectar() {
		byte[]mensaje_bytes =  new byte[256];
		Double mensaje = 0d;
		
		try {
			socket = new Socket ("localhost",6666);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			System.out.println("Conectado al servidor en el puerto 6666");
			do {
				System.out.println("Escribe el numero al que deseas elevar al cubo:");
				mensaje =Double.parseDouble(entradaTeclado.readLine()) ;
				
				out.writeDouble(mensaje);//SE ENVIA EL MENSAJE AL CLIENTE
				System.out.println("Introduciste el numero: " + mensaje);
				Double respuesta = in.readDouble();//LEE LA RESPUESTA DEL SERVIDOR
				System.out.println("El cubo del numero que introduciste es: " + respuesta);
			}while(!mensaje.equals("fin") );
			in.close();
			out.close();
			socket.close();
			System.out.println("Conexion cerrada");
		} catch (IOException e) {
			e.printStackTrace();
		}
				}
				public static void main (String args[]) {
					CuboNumeroCliente cliente = new CuboNumeroCliente();
					cliente.conectar();
				}
}

