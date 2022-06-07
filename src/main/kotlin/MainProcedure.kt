fun main() {
    val g = SquareGraph(3, 3);
    println(g);

    val sp = g.dijkstra();
    println(sp.getPath("Vertex 0", "Vertex 6"));
}