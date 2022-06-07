package dijkstra

import org.jgrapht.Graph
import org.jgrapht.GraphPath
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.generate.GridGraphGenerator
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleWeightedGraph
import org.jgrapht.traverse.DepthFirstIterator
import org.jgrapht.util.SupplierUtil
import java.util.function.Supplier

class SquareGraph(var rows: Int = 2, var columns: Int = 2){

    private val vertexSupplier: Supplier<String> = object : Supplier<String> {
        private var id = 0
        override fun get(): String {
            return "Vertex " + id++;
        }
    }

    private val graph = initialize();

    init{
        if(rows < 2) rows = 2;
        if(columns < 2) columns = 2;
    }

    private fun initialize() : Graph<String, DefaultWeightedEdge> {
        var completeGraph =
            SimpleWeightedGraph(vertexSupplier, SupplierUtil.createDefaultWeightedEdgeSupplier());

        // Successivamente si crea un generatore che crea la struttura di `completeGraph`
        var graphGenerator =
            GridGraphGenerator<String, DefaultWeightedEdge>(rows, columns);

        graphGenerator!!.generateGraph(completeGraph)
        return completeGraph;
    }


    fun dijkstra(start : String, end : String): GraphPath<String, DefaultWeightedEdge>? {
        var dijkstra = DijkstraShortestPath(graph);
        return dijkstra.getPath(start, end);
        ;
    }

    fun deleteVerteces(vertices : ArrayList<String>){
        for(v in vertices){
            graph.removeVertex(v);
        }
    }

    override fun toString(): String {
        var str = ""
        val iter: Iterator<String> = DepthFirstIterator(graph)
        while (iter.hasNext()) {
            val vertex = iter.next()
            str += "Vertice `" + vertex + "` connesso a " + graph.edgesOf(vertex).toString() + "\n";
        }
        return str
    }
}