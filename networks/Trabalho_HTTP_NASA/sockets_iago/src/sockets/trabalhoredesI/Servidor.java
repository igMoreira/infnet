package sockets.trabalhoredesI;


import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Date;
import java.util.Scanner;


public class Servidor extends Thread
{

	private Socket conexao;
	
	public Servidor(Socket conexao)
	{
		this.conexao = conexao;
	}
	
	public static void main(String[] args)
	{
		ServerSocket servidor;
		try
		{
			servidor = new ServerSocket(80);
			
			System.out.println(" Servidor > porta " + servidor.getLocalPort() + " aberta com sucesso");
			
			System.out.println("Servidor > Aguardando clientes...");

			Socket conexao = servidor.accept();
			System.out.println("Servidor > Nova conexao com o cliente " + conexao.getInetAddress().getHostAddress() );
			
			Thread s = new Servidor(conexao);
			s.start();

			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void run()
	{
		
		httpRequisitionView(conexao);
		httpResponseSend(conexao);

		
		try
		{
			conexao.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void httpRequisitionView( Socket cliente )
	{
		//		***********************IMPRIME REQUISIÇÃO HTTP DO CLIENTE**************************************
		
		Scanner entrada = null;
		try
		{
			entrada = new Scanner( cliente.getInputStream() );
			
			String linha ;
			while( !( linha =  entrada.nextLine() ).equals("fim") )
			{
				System.out.println( linha );
			}
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void httpResponseSend( Socket cliente)
	{
		
//		***********************ESCREVE RESPOSTA HTTP**************************************
		PrintStream saida = null;
		try
		{
			saida = new PrintStream( cliente.getOutputStream() );
			
			saida.flush();
			saida.println	("Servidor > HTTP/1.1 200 OK");
			saida.println	("Servidor > Connection: close");
			saida.println	("Servidor > Date: " + new Date().getTime() );
			
			saida.println		("\n\n");
			saida.println		("fim");
			
			saida.flush();
			saida.close();
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}