package algoritmos.av1.buscas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algoritmos.av1.model.Graph;
import algoritmos.av1.model.Node;

/**
 * Fornece uma implementação do algoritmo
 * Breadth-First Search
 * 
 * @author igMoreira
 *
 */
public class BFS {
	//Grafo a ser utilizado na busca
	private Graph grafo;
	
	//Distancia dos vertices na fila key = vertice, value = distancia do vertice
	private Map<String, Integer> distance = new HashMap<String,Integer>();
	
	//Fila preenchida durante a busca
	private List<Node> queue = new ArrayList<Node>();
	
	/**
	 * Constructor
	 * @param grafo: Grafo a ser explorado
	 */
	public BFS(Graph grafo) {
		this.grafo = grafo;
	}
	
	/**
	 * Executa o algoritmo de busca no grafo fornecido
	 * Mandando Logs de execução para a saída padrão de vídeo.
	 */
	public void executa()
	{
		// Seta a distancia de todos os vertices como infinito
		resetDistance();
		
		// Ordena o grafo para ter certeza que o no raiz sera o correto
		List<Node> nodeList = new ArrayList<Node>(grafo.getTree().values());
		Collections.sort(nodeList, Node.NodeValueComparator);
		
		// Para cada vertice da lista executa a busca se já nã houver sido executada
		// Isso garante que vértices desconexos sejam avaliados na busca
		for (Node node : nodeList) {
			if(distance.get(node.getValue()) == Integer.MAX_VALUE)
				search(node);
		}
	}
	
	/**
	 * Core do algoritmo, implementação dos passos
	 * @param root: Vértice raiz para se começar a busca
	 */
	private void search(Node root)
	{	
		// Atribui distancia zero para o vértice raiz
		distance(root, 0);
		
		// Coloca o vértice raiz na fila
		inject(root);
		
		// Enquanto a fila não estiver vazia
		while (!queue.isEmpty()) {
			// Remove o primeiro vértice na fila (FIFO)
			Node u = eject();
			
			// Ordena as arestas para ter certeza que o vértice correto seja explorado primeiro
			List<Node> arestas = new ArrayList<Node>(u.getArestas());
			Collections.sort(arestas, Node.NodeValueComparator);
			
			// Para cada aresta saindo do vértice u 
			for (Node v : arestas) {
				// Verifica se a distância é infinito
				if(distance.get(v.getValue()) == Integer.MAX_VALUE)
				{
					// Insere o vértice na fila
					inject(v);
					// Altera a distância de V como sendo a distância de U + 1
					distance(v, distance.get(u.getValue()) + 1);
				}
			}
		}		
	}
	
	/**
	 * Insere um vértice na fila e exibe um log
	 * @param n: Vértice a ser inserido na fila
	 */
	private void inject(Node n)
	{
		queue.add(n);
		System.out.printf("%-10s %-10s\n","[INJECT]", "[NODE "+n.getValue()+"]");
	}
	
	/**
	 * Remove o primeiro vértice da fila (FIFO) e exibe um log
	 * @return: Vértice removido da fila
	 */
	private Node eject()
	{
		Node u = queue.remove(0);
		System.out.printf("%-10s %-10s\n","[EJECT]", "[NODE "+u.getValue()+"]");
		return u;
	}
	
	/**
	 * Altera a distância de vértice U para o valor = Value
	 * @param u: vértice a ser alterado o valor
	 * @param value: novo valor da distância
	 */
	private void distance(Node u, int value)
	{
		distance.put(u.getValue(), value);
		System.out.printf("%-10s %-10s\n","[NODE "+u.getValue()+"]", "[DISTANCE "+ distance.get(u.getValue()) +"]");
	}
	
	/**
	 * Atribui distância infinita a todos o vértices
	 * do grafo
	 */
	private void resetDistance()
	{
		for (String node : grafo.getTree().keySet()) {
			distance.put(node, Integer.MAX_VALUE);
		}
	}
}
