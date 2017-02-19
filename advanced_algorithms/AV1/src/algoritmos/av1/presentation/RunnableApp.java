package algoritmos.av1.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import algoritmos.av1.buscas.BFS;
import algoritmos.av1.buscas.DFS;
import algoritmos.av1.buscas.Djkstra;
import algoritmos.av1.model.Graph;

public class RunnableApp {
	private static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {

		while(true)
		{
			System.out.println("Digite a opção desejada:");
			System.out.println();
			System.out.println("1. BFS");
			System.out.println("2. DFS");
			System.out.println("3. Dijkstra");
			System.out.println("4. Sair da aplicação");
			int op = 0;

			while(!input.hasNextInt()) {
				input.nextLine();
				System.out.println("Somente números são válidos!!!");
			}

			op = input.nextInt();
			input.nextLine();
			switch (op) {
			case 1:
				doBFS();
				break;
			case 2:
				doDFS();
				break;
			case 3:
				doDijkstra();
				break;
			case 4:
				System.out.println("Application Terminated");
				System.exit(0);
				break;
			default:
				System.out.println("Opção inválida, por favor digite novamente");
				break;
			}
		}
	}

	private static void doDijkstra() {
		System.out.println("Digite a lista de vértices separadas por vírgula (Ex.: \"A,B,C,D,E\")");
		System.out.println("PS.: Os valores SÃO case sentive, isto é o vértice A=a");
		String vertices = input.nextLine();
		System.out.println("Digite as arestas separadas por vírgula (Ex.: \"AB,BA,CD,EC\")");
		String arestas = input.nextLine();
		System.out.println("Digite a distância das arestas na mesma ordem descrito acima: ");
		System.out.println("PS.: o conteúdo deve ser separadado por vígulas. (Ex.: \"1,3,2,7\")");
		String distancias = input.nextLine();
		System.out.println("Deseja um grafo direcional (Y/N) ?");
		String direcional = input.nextLine();
		
		List<String> verticeList = Arrays.asList(vertices.split(","));
		List<String> arestaList = Arrays.asList(arestas.split(","));
		List<String> distanciaList = Arrays.asList(distancias.split(","));
		HashMap<String, Integer> distanceTable = new HashMap<String, Integer>();
		for (int i =0; i < arestaList.size(); i++) {
			distanceTable.put(arestaList.get(i), Integer.parseInt(distanciaList.get(i)));
		}
		
		boolean direcFlag = direcional.equalsIgnoreCase("Y");
		if (direcFlag) {
			distanceTable = processEdges(arestaList, distanciaList);
		}
		
		Djkstra algoritmo = new Djkstra(new Graph(verticeList, distanceTable));
		System.out.println("-------------------Dijsktra Seach----------------------------");
		algoritmo.executa();
		System.out.println("-------------------------------------------------------------");
	}

	private static void doDFS() {
		System.out.println("Digite a lista de vértices separadas por vírgula (Ex.: \"A,B,C,D,E\")");
		System.out.println("PS.: Os valores SÃO case sentive, isto é o vértice A=a");
		String vertices = input.nextLine();
		System.out.println("Digite as arestas separadas por vírgula (Ex.: \"AB,BA,CD,EC\")");
		String arestas = input.nextLine();
		System.out.println("Deseja um grafo direcional (Y/N) ?");
		String direcional = input.nextLine();
		
		List<String> verticeList = Arrays.asList(vertices.split(","));
		List<String> arestaList = Arrays.asList(arestas.split(","));
		boolean direcFlag = direcional.equalsIgnoreCase("Y");
		if (direcFlag) {
			arestaList = processEdges(arestaList);
		}
		
		DFS algoritmo = new DFS(new Graph(verticeList, arestaList));
		System.out.println("-------------------Depth-First Seach----------------------------");
		algoritmo.executa();
		System.out.println("----------------------------------------------------------------");
	}

	private static void doBFS() {
		System.out.println("Digite a lista de vértices separadas por vírgula (Ex.: \"A,B,C,D,E\")");
		System.out.println("PS.: Os valores SÃO case sentive, isto é o vértice A=a");
		String vertices = input.nextLine();
		System.out.println("Digite as arestas separadas por vírgula (Ex.: \"AB,BA,CD,EC\")");
		String arestas = input.nextLine();
		System.out.println("Deseja um grafo direcional (Y/N) ?");
		String direcional = input.nextLine();
		
		List<String> verticeList = Arrays.asList(vertices.split(","));
		List<String> arestaList = Arrays.asList(arestas.split(","));
		boolean direcFlag = direcional.equalsIgnoreCase("Y");
		if (direcFlag) {
			arestaList = processEdges(arestaList);
		}
		
		BFS algoritmo = new BFS(new Graph(verticeList, arestaList));
		System.out.println("-------------------Breadth-First Seach----------------------------");
		algoritmo.executa();
		System.out.println("------------------------------------------------------------------");
	}

	private static List<String> processEdges(List<String> arestaList) {
		List<String> aux = new ArrayList<>(arestaList);
		List<String> newList = new ArrayList<>(arestaList);
		for (String aresta : aux) {
			String reversed = new StringBuilder(aresta).reverse().toString();
			newList.add(reversed);
		}
		Set<String> unique = new HashSet<>(aux);
		newList.clear();
		newList.addAll(unique);
		return newList;
	}
	
	private static HashMap<String, Integer> processEdges(List<String> arestaList, List<String> distanciaList) {
		List<String> aux = new ArrayList<>(arestaList);
		HashMap<String, Integer> table = new HashMap<>();
		for (int i= 0; i < aux.size(); i++) {
			String reversed = new StringBuilder(aux.get(i)).reverse().toString();
			table.put(aux.get(i), Integer.parseInt(distanciaList.get(i)));
			table.put(reversed, Integer.parseInt(distanciaList.get(i)));
		}
		return table;
	}
}
