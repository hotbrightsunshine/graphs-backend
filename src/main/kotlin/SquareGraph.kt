import org.jgrapht.Graph
import org.jgrapht.generate.GridGraphGenerator
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleWeightedGraph
import org.jgrapht.traverse.DepthFirstIterator
import org.jgrapht.util.SupplierUtil
import java.util.function.Supplier

class SquareGraph {
    // Not null
    private val rows = 0
    private val columns = 0
    private val vertexSupplier: Supplier<String> = object : Supplier<String> {
        private var id = 0
        override fun get(): String {
            return "Vertex " + id++
        }
    }

    // Prima serve creare l'oggetto grafo
    private val completeGraph: Graph<String, DefaultEdge> =
        SimpleWeightedGraph(vertexSupplier, SupplierUtil.createDefaultEdgeSupplier())

    // Successivamente si crea un generatore che crea la struttura di `completeGraph`
    private val graphGenerator = GridGraphGenerator<String, DefaultEdge>(rows, columns)

    // Creo il grafo
    fun initialize() {
        graphGenerator.generateGraph(completeGraph)
    }

    override fun toString(): String {
        var str = ""
        val iter: Iterator<String> = DepthFirstIterator(completeGraph)
        while (iter.hasNext()) {
            val vertex = iter.next()
            str += "Vertice `" + vertex + "` connesso a " + completeGraph.edgesOf(vertex)
        }
        return str
    }
}