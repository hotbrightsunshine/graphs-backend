import org.jgrapht.generate.GridGraphGenerator
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleGraph
import org.jgrapht.graph.SimpleWeightedGraph
import org.jgrapht.traverse.DepthFirstIterator
import org.jgrapht.util.SupplierUtil
import java.util.function.Supplier

fun main(){
    val vs = object : Supplier<String> {
        private var id = 0;
        override fun get(): String {
            return "Vertex " + id++;
        }
    }

    var completeGraph = SimpleWeightedGraph<String, DefaultWeightedEdge>(
        vs,
        SupplierUtil.createDefaultWeightedEdgeSupplier()
    );

    var graphGenerator = GridGraphGenerator<String, DefaultWeightedEdge>(3, 3);

    graphGenerator.generateGraph(completeGraph);

    var str = ""
    val iter: Iterator<String> = DepthFirstIterator(completeGraph)
    while (iter.hasNext()) {
        val vertex = iter.next()
        str += "Vertice `" + vertex + "` connesso a " + completeGraph!!.edgesOf(vertex).toString() + "\n";
    }

    println(str);
}