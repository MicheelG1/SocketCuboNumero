package edu.ieu.socket.cuboNumero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CuboNumeroServer {

	public void iniciar() {
        ServerSocket servidor;
        boolean fin = false;
        try {
        servidor = new ServerSocket(6666);
        System.out.println("Servidor escuchando en el puerto 6666");
        Socket socketManejoCliente = servidor.accept();
        
        DataInputStream in= new DataInputStream(socketManejoCliente.getInputStream());
        DataOutputStream out = new DataOutputStream(socketManejoCliente.getOutputStream());
        do {
        Double mensaje =0d;
        mensaje = in.readDouble();
        System.out.println("El servidor recibio del cliente:"+ mensaje);
       out.writeDouble(mensaje*mensaje*mensaje);
        if (mensaje.equals("fin")) {
        fin= true;
        System.out.println("El servidor se apagara y no recibira mas mensajes:");
        }
        }while(!fin);
        in.close();
        out.close();
        socketManejoCliente.close();
        servidor.close();
    }catch (IOException e) {
        	e.printStackTrace();
        }
	}
	public static void main(String args[]) {
		CuboNumeroServer servidor = new CuboNumeroServer();
		servidor.iniciar();
} 
	} 



