package algoritmos.av1.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Fornece a abstração de grafo
 * @author igMoreira
 *
 */
public class Graph {
	// O grafo montado
	private Map<String, Node> tree = new HashMap<String, Node>();
	
	/**
	 * Constructor
	 * @param nodes: lista de vértices
	 * @param edges: lista de arestas
	 */
	public Graph(List<String> nodes, List<String> edges)
	{
		buildTree(nodes, edges);
	}

	/**
	 * Constructor
	 * @param nodes: lista de vértices
	 * @param distances: Tabela de arestas e suas distancias
	 */
	public Graph(List<String> nodes, HashMap<String, Integer> distances) {
		for (String node : nodes)
			tree.put(node, new Node(node));
		for (String key : distances.keySet()) {
			if(key.length() != 2)
			{
				System.out.println("A aresta \""+ key + "\" contém um erro, somente é possível definir uma aresta para um par de nós.");
				System.exit(0);
			}
			else
			{
				Node node1 = tree.get(key.substring(0, 1));
				Node node2 = tree.get(key.substring(1, 2));
				if(( node1 != null) && ( node2 != null))
					node1.addAresta(node2, distances.get(key));
				else
				{
					System.out.println("A aresta \""+ key +"\" contém um nó não declarado!!!");
					System.exit(0);
				}
			}
		}
	}

	/**
	 * Getter para recuperar o grafo
	 * @return
	 */
	public Map<String, Node> getTree() {
		return tree;
	}
	
	/**
	 * Constrói a árvore do grafo com base na lista de strings fornecida
	 * @param nodes: lista de vértices
	 * @param edges: lista de arestas
	 */
	private void buildTree(List<String> nodes, List<String> edges)
	{
		for (String node : nodes)
			tree.put(node, new Node(node));
		
		for (String aresta : edges) {
			if(aresta.length() != 2)
			{
				System.out.println("A aresta \""+ aresta + "\" contém um erro, somente é possível definir uma aresta para um par de nós.");
				System.exit(0);
			}
			else
			{
				Node node1 = tree.get(aresta.substring(0, 1));
				Node node2 = tree.get(aresta.substring(1, 2));
				if(( node1 != null) && ( node2 != null))
					node1.addAresta(node2);
				else
				{
					System.out.println("A aresta \""+ aresta +"\" contém um nó não declarado!!!");
					System.exit(0);
				}
			}
		}
	}
}
