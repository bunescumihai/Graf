import java.util.ArrayList;
import java.util.List;

public class Edges {
    private List<Edge> listOfEdges = new ArrayList<Edge>();
    private int sortingIterations = 0;

    public Edges(Graf graf){

        List<List<Integer>> list = graf.getAdjacencyList();

        for(int i = 0; i < graf.getNumberOfVertices(); i++){
            List<Integer> line = list.get(i);
            while (!line.isEmpty()){
                int vertex = line.get(0);
                this.listOfEdges.add(new Edge(i, vertex));
                line.remove(0);
                (list.get(vertex)).remove(Integer.valueOf(i));
            }
        }

    }

    public List<Edge> getListOfEdges() {
        return this.listOfEdges;
    }

    public List<Edge> getSortedListOfEdgesByWeight(){
        List<Edge> list = this.listOfEdges;
        for(int i = 0; i < list.size()-1; i++){
            this.sortingIterations++;
            for(int j = 0; j < list.size()-1-i; j++){
                this.sortingIterations++;
                if((list.get(j)).getWeight() > (list.get(j+1)).getWeight()){
                    this.sortingIterations +=3;
                    Edge aux = new Edge(list.get(j));
                    list.set(j, list.get(j+1));
                    list.set(j+1, aux);
                }
            }
        }
        return list;
    }

    public int getSortingIterations() {
        return this.sortingIterations;
    }
}
