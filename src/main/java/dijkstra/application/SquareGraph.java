package dijkstra.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Supplier;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.generate.GridGraphGenerator;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.util.SupplierUtil;

public final class SquareGraph {
    private final Supplier vertexSupplier;
    private final Graph graph;
    private int rows;
    private int columns;

    private final Graph initialize() {
        SimpleWeightedGraph completeGraph = new SimpleWeightedGraph(this.vertexSupplier, SupplierUtil.createDefaultWeightedEdgeSupplier());
        GridGraphGenerator graphGenerator = new GridGraphGenerator(this.rows, this.columns);
        graphGenerator.generateGraph((Graph)completeGraph);
        return (Graph)completeGraph;
    }

    public final GraphPath dijkstra(String start, String end) {
        DijkstraShortestPath dijkstra = new DijkstraShortestPath(this.graph);
        return dijkstra.getPath(start, end);
    }

    public final void deleteVerteces( ArrayList vertices) {
        Iterator var3 = vertices.iterator();

        while(var3.hasNext()) {
            String v = (String)var3.next();
            this.graph.removeVertex(v);
        }

    }


    public String toString() {
        String str = "";

        String vertex;
        for(Iterator iter = (Iterator)(new DepthFirstIterator(this.graph));
            iter.hasNext(); str = str + "Vertice `" + vertex +
                "` connesso a " + this.graph.edgesOf(vertex).toString() + "\n")
        {
            vertex = (String)iter.next();
        }

        return str;
    }

    public final int getRows() {
        return this.rows;
    }

    public final void setRows(int var1) {
        this.rows = var1;
    }

    public final int getColumns() {
        return this.columns;
    }

    public final void setColumns(int var1) {
        this.columns = var1;
    }

    public SquareGraph(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.vertexSupplier = (Supplier) (new Supplier() {
            private int id;


            public String get() {
                StringBuilder var10000 = (new StringBuilder()).append("Vertex ");
                int var1;
                this.id = (var1 = this.id) + 1;
                return var10000.append(var1).toString();
            }
        });
        this.graph = this.initialize();
        if (this.rows < 2) {
            this.rows = 2;
        }

        if (this.columns < 2) {
            this.columns = 2;
        }

    }
}
