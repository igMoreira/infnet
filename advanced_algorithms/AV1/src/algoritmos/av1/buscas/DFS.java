package algoritmos.av1.buscas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algoritmos.av1.model.Graph;
import algoritmos.av1.model.Node;

/**
 * Fornece a implementação do algoritmo
 * Depth-First Search
 * @author igMoreira
 *
 */
public class DFS {
	// Grafo a ser utilizado na busca
	private Graph grafo;
	
	// Lista de vértices já visitados pelo algoritmo
	private List<String> visited = new ArrayList<String>();
	
	// Contador utilizado nos cáculos de Pré e Post visit
	private int counter = 0;

	/**
	 * Constructor
	 * @param grafo: Grafo a ser explorado
	 */
	public DFS(Graph grafo) {
		this.grafo = grafo;
	}

	/**
	 * Executa o algoritmo de busca no grafo fornecido
	 * Mandando Logs de execução para a saída padrão de vídeo.
	 */
	public void executa()
	{
		// Ordena o grafo para ter certeza que o no raiz sera o correto
		List<Node> nodeList = new ArrayList<Node>(grafo.getTree().values());
		Collections.sort(nodeList, Node.NodeValueComparator);

		// Seta as informações de vértice visitado
		visited.clear();

		// para cada vértice da lista executa a busca se não houver sido visitado anteriormente
		for (Node node : nodeList) {
			// verifica se o vértice foi explorado
			if(!isVisited(node.getValue())) 
				explore(node);
		}
		
		counter = 0;
	}

	public void explore(Node node)
	{
		// Adiciona o vértice na lista de visitados
		visited.add(node.getValue());
		
		// Calcula o previsit
		preVisit(node);
		
		// Ordena as arestas para ter certeza que o vértice correto seja explorado primeiro
		List<Node> arestas = new ArrayList<Node>(node.getArestas());
		Collections.sort(arestas, Node.NodeValueComparator);
		
		// Para cada aresta saindo do vértice u 
		for (Node aresta : arestas) {
			// Verifica se o vértice V já foi visitado
			if(!isVisited(aresta.getValue())) 
				explore(aresta);
			else
				consulta(node, aresta); // Uma consulta foi realizada
		}
		
		postVisit(node);
	}

	/**
	 * Verifica se um vértice está na lista de visitados
	 * @param nodeValue: Vértice a ser visitado
	 * @return
	 */
	public boolean isVisited(String nodeValue)
	{
		return visited.contains(nodeValue);
	}

	/**
	 * Calcula o valor de previsit e exibe um log
	 * @param node
	 */
	private void preVisit(Node node)
	{
		System.out.println("[Node"+ node.getValue() +"] [PREVISIT = "+ (++counter) +"]");
	}

	/**
	 * Calcula o valor de postvisit e exibe um log
	 * @param node
	 */
	private void postVisit(Node node) {
		System.out.println("[Node"+ node.getValue() +"] [POSTVISIT = "+ (++counter) +"]");
	}
	
	/**
	 * Exibe um log da consulta realizada
	 * @param origem
	 * @param destino
	 */
	private void consulta(Node origem, Node destino)
	{
		System.out.println("[CONSULTA][DE NODE "+origem.getValue()+"] [PARA NODE "+destino.getValue() +"] [NODE "+destino.getValue()+" JÁ FOI VISITADO]");
	}

}
