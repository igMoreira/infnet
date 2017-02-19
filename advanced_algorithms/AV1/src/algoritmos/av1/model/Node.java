package algoritmos.av1.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Fornece a abstração de Vértice de um grafo
 * @author igMoreira
 *
 */
public class Node{
	// Valor do vértice
	private String value;
	
	//Arestas do vértice com seus valores de distância
	private HashMap<Node, Integer> arestas = new HashMap<Node, Integer>();

	/**
	 * Constructor
	 * @param value: valor do vértice
	 */
	public Node(String value) {
		this.value = value;
	}
	
	/**
	 * Recupera todas as arestas saindo deste vértice
	 * @return
	 */
	public List<Node> getArestas() {
		return new ArrayList<Node>(arestas.keySet());
	}
	
	/**
	 * Recupera a distância de uma das arestas deste vértice 
	 * @return
	 */
	public Integer getDistance(Node v)
	{
		return arestas.get(v);
	}

	/**
	 * Retorna o valor deste vértice
	 * @return
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Adiciona uma aresta neste vértice
	 * desconsiderando a distância.
	 * @param node
	 */
	public void addAresta(Node node)
	{
		this.arestas.put(node, 0);
	}
	
	/**
	 * Adiciona uma aresta neste vértice
	 * considerando a distância
	 * @param node
	 * @param distance
	 */
	public void addAresta(Node node, Integer distance)
	{
		this.arestas.put(node, distance);
	}
	
	/**
	 * Fornece um comparator para que possa ser utilizado
	 * em operações de ordenação de Coleções. É importante
	 * para recuperar os vértices em ordem, Ex. A,B,C,D 
	 */
	public static Comparator<Node> NodeValueComparator = new Comparator<Node>() {
		
		@Override
		public int compare(Node node1, Node node2) {
			String node1Value = node1.getValue().toUpperCase();
			String node2Value = node2.getValue().toUpperCase();
			
			return node1Value.compareTo(node2Value);
		}
	};
}
