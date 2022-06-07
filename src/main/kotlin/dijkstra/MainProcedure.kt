package dijkstra

fun main() {
    val graph = SquareGraph(3, 3);
    println(graph);

    println(graph.dijkstra());
}