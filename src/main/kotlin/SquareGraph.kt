import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.generate.GridGraphGenerator
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph
import org.jgrapht.traverse.DepthFirstIterator
import org.jgrapht.util.SupplierUtil
import java.util.function.Supplier

class SquareGraph(var rows: Int = 2, var columns: Int = 2){

    private val vertexSupplier: Supplier<String> = object : Supplier<String> {
        private var id = 0
        override fun get(): String {
            return "Vertex " + id++
        }
    }

    // Prima serve creare l'oggetto grafo
    var completeGraph: Graph<String, DefaultEdge> =
        SimpleWeightedGraph(vertexSupplier, SupplierUtil.createDefaultEdgeSupplier());

    // Successivamente si crea un generatore che crea la struttura di `completeGraph`
    var graphGenerator : GridGraphGenerator<String, DefaultEdge> =
        GridGraphGenerator<String, DefaultEdge>(rows, columns);

    init{
        if(rows < 2) rows = 2;
        if(columns < 2) columns = 2;
        initialize();
    }

    private fun initialize() {
        graphGenerator!!.generateGraph(completeGraph)
    }


    fun dijkstra() : DijkstraShortestPath<String, DefaultEdge> {
        var dijkstra = DijkstraShortestPath(completeGraph);
        return dijkstra;
    }

    override fun toString(): String {
        var str = ""
        val iter: Iterator<String> = DepthFirstIterator(completeGraph)
        while (iter.hasNext()) {
            val vertex = iter.next()
            str += "Vertice `" + vertex + "` connesso a " + completeGraph!!.edgesOf(vertex).toString() + "\n";
        }
        return str
    }
}