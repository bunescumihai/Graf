public class Edge{
    private int firstVertex;
    private int secondVertex;
    private int weight = 1;

    public Edge(int firstVertex, int secondVertex){
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public Edge(int firstVertex, int secondVertex, int weight){
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
        this.weight = weight;
    }

    public Edge(Edge edge){
        this.firstVertex = edge.getFirstVertex();
        this.secondVertex = edge.getSecondVertex();
        this.weight = edge.getWeight();
    }

    public int getFirstVertex() {
        return firstVertex;
    }

    public int getSecondVertex() {
        return secondVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setFirstVertex(int firstVertex) {
        this.firstVertex = firstVertex;
    }

    public void setSecondVertex(int secondVertex) {
        this.secondVertex = secondVertex;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString(){
        return "(" + this.firstVertex +
                ", " + this.secondVertex +
                ", " + this.weight+")";
    }

}
