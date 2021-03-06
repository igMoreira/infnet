package sockets.trabalhoredesI;

import java.io.IOException;

import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class Cliente extends Thread
{

	/**
	 * @param args
	 */
	
	private Socket conexao;

	
	public Cliente(Socket conexao)
	{
		this.conexao = conexao;
	}
	
	public static void main(String[] args)
	{
		try
		{
			System.out.println("Cliente > O cliente se conectou ao servidor com sucesso");
			Socket conexao = new Socket( "localhost" , 80);
			
			Thread c = new Cliente(conexao);
			c.start();
			
			
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		
		httpRequisitionSend(conexao);
		httpResponseView(conexao);
		
		try
		{
			conexao.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void httpRequisitionSend( Socket cliente )
	{
		//		 *****************MENSAGEM DE REQUISIÇÃO HTTP***************************
		
		PrintStream saida = null;
		try
		{
			saida = new PrintStream( cliente.getOutputStream() );
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		saida.println	("Cliente > GET C:\\Users\\OSTRICH\\aqui.txt HTTP/1.1");
		saida.println	("Cliente > Host: "+ cliente.getLocalAddress() );
		saida.println	("Cliente > Connection: close");
		
		saida.println		("\n\n");
		saida.println		("fim");
		saida.flush();
		
	}
	
	private void httpResponseView( Socket cliente)
	{
		
		//		***************AGUARDA MENSAGEM DE RESPOSTA HTTP************************
		
		
		Scanner entrada = null;
		try
		{
			entrada = new Scanner( cliente.getInputStream() );
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		String linha ;
		while( !( linha =  entrada.nextLine() ).equals("fim") )
		{
			System.out.println( linha );
		}
		
		entrada.close();
		
	}

}
