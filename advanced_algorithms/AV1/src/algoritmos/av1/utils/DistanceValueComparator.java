package algoritmos.av1.utils;

import java.util.Comparator;
import java.util.Map;

import algoritmos.av1.model.Node;

/**
 * Fornece um comparador de distâncias de vértices
 * Importante para verificar qual o vértice com
 * a menor distância em uam coleção.
 * @author igMoreira
 *
 */
public class DistanceValueComparator implements Comparator<Node>{

	// Tabela de distâncias
	private Map<String , Integer> distance;
	
	/**
	 * Constructor
	 * @param distanceTable
	 */
	public DistanceValueComparator(Map<String, Integer> distanceTable) {
		this.distance = distanceTable;
	}
	
	/**
	 * Verifica o menor valor da tabela de distâncias,
	 * se for igual, é analizado o nome do vértice, isto é
	 * o valor.
	 */
	@Override
	public int compare(Node o1, Node o2) {
		Integer distance1 = distance.get(o1.getValue());
		Integer distance2 = distance.get(o2.getValue());
		if(distance1 > distance2)
			return 1;
		if(distance1 < distance2)
			return -1;
		String node1Value = o1.getValue().toUpperCase();
		String node2Value = o2.getValue().toUpperCase();
		return node1Value.compareTo(node2Value);
	}

}
