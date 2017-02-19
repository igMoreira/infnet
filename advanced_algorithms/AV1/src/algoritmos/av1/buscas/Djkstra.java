package algoritmos.av1.buscas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import algoritmos.av1.model.Graph;
import algoritmos.av1.model.Node;
import algoritmos.av1.utils.DistanceValueComparator;

/**
 * Fornece uma implementação do algoritmo de Dijkstra
 * 
 * @author igMoreira
 *
 */
public class Djkstra {
	
	// Armazena a distancia dos vertices
	private HashMap<String, Integer> distance = new HashMap<String, Integer>();
	
	// Fila preenchida durante a busca
	private List<Node> queue = new ArrayList<Node>();
	
	// Grafo a ser utilizado na busca
	private Graph grafo;
	
	public Djkstra(Graph grafo) {
		this.grafo = grafo;
	}
	
	/**
	 * Executa o algoritmo de busca no grafo fornecido
	 * Mandando Logs de execução para a saída padrão de vídeo.
	 */
	public void executa()
	{	
		for (String node : grafo.getTree().keySet()) {
			distance.put(node, Integer.MAX_VALUE);
		}
		
		// Ordena o grafo para ter certeza que o no raiz sera o correto
		List<Node> nodeList = new ArrayList<Node>(grafo.getTree().values());
		Collections.sort(nodeList, Node.NodeValueComparator);
		
		// recupera o vértice raiz, inseri sua distância e o coloca na fila
		Node s = nodeList.get(0);
		distance(s,0);
		queue.add(s);

		while (!queue.isEmpty()) {
			// Verifica o vértice com o menor valor na tabela de distancias
			Node u = deleteMin();
			
			for (Node v : u.getArestas()) {
				Integer distanceDoPai = this.distance.get(u.getValue());
				Integer distanciaDoFilho = u.getDistance(v);
				if(distance.get(v.getValue()) > ( distanceDoPai + distanciaDoFilho ) )
				{
					distance(v, distanceDoPai + distanciaDoFilho);
					queue.add(v);
				}
			}
		}
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
	 * Retorna o vértice de menor distância
	 * removendo ele da fila.
	 * 
	 * @return
	 */
	private Node deleteMin()
	{
		Node u = Collections.min(queue, new DistanceValueComparator(distance));	
		queue.remove(u);
		return u;
	}
}
