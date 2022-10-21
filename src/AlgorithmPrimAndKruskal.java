import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgorithmPrimAndKruskal {
    private final Graf graf;
    private int numberOfIterationsOfPrim = 0;
    private int numberOfIterationsOfKruskal = 0;
    private int minWeight = 0;
    private List<Edge> kruskalListOfEdges = new ArrayList<Edge>();
    private List<Edge> primListOfEdges = new ArrayList<Edge>();
    private List<Edge> listOfEdges;

    public AlgorithmPrimAndKruskal(Graf graf){
        this.graf = graf;
        graf.setByDefaultSortedListOfEdges();
        this.listOfEdges = graf.getListOfEdges();
        kruskalAlgorithm();
    }

    private void kruskalAlgorithm(){
        List<Edge> list = new ArrayList<>(this.listOfEdges);
        List<List<Integer>> listOfListsOfVisitedVertices = new ArrayList<>();
        int numberOfVisitedVertices = 0;

        while(numberOfVisitedVertices != graf.getNumberOfVertices()){
            Edge nextEdge = getNextEdge(list);
            boolean isIncluded = false;

            for(List<Integer> i : listOfListsOfVisitedVertices){
                if(i.contains(nextEdge.getFirstVertex()) && i.contains(nextEdge.getSecondVertex())){
                    isIncluded = true;
                    break;
                }
                else if(i.contains(nextEdge.getFirstVertex())){
                    for(List<Integer> j : listOfListsOfVisitedVertices){
                        if(j.contains(nextEdge.getSecondVertex())){
                            i.addAll(j);
                            minWeight += nextEdge.getWeight();
                            kruskalListOfEdges.add(nextEdge);
                            listOfListsOfVisitedVertices.remove(j);
                            numberOfVisitedVertices++;
                            isIncluded = true;
                            break;
                        }

                    }
                    if(isIncluded)
                        break;
                    i.add(nextEdge.getSecondVertex());
                    minWeight += nextEdge.getWeight();
                    kruskalListOfEdges.add(nextEdge);
                    numberOfVisitedVertices++;
                    isIncluded = true;
                    break;
                }
                else if(i.contains(nextEdge.getSecondVertex())){
                    for(List<Integer> j : listOfListsOfVisitedVertices){
                        if(j.contains(nextEdge.getFirstVertex())){
                            i.addAll(j);
                            minWeight += nextEdge.getWeight();
                            kruskalListOfEdges.add(nextEdge);
                            listOfListsOfVisitedVertices.remove(j);
                            numberOfVisitedVertices++;
                            isIncluded = true;
                            break;
                        }
                    }
                    if(isIncluded)
                        break;
                    i.add(nextEdge.getFirstVertex());
                    minWeight += nextEdge.getWeight();
                    kruskalListOfEdges.add(nextEdge);
                    numberOfVisitedVertices++;
                    isIncluded = true;
                    break;
                }
            }

            if(!isIncluded){
                listOfListsOfVisitedVertices.add(new ArrayList<Integer>());
                List<Integer> lastLine = listOfListsOfVisitedVertices.get(listOfListsOfVisitedVertices.size()-1);
                lastLine.add(nextEdge.getFirstVertex());
                lastLine.add(nextEdge.getSecondVertex());
                numberOfVisitedVertices+=2;
                minWeight += nextEdge.getWeight();
                kruskalListOfEdges.add(nextEdge);
            }

        }

        while (listOfListsOfVisitedVertices.size() != 1){
            Edge nextEdge = getNextEdge(list);

            for(List<Integer> i: listOfListsOfVisitedVertices){
                boolean isIncluded = false;
                if(i.contains(nextEdge.getFirstVertex()) && i.contains(nextEdge.getSecondVertex()))
                    isIncluded = true;
                else if(i.contains(nextEdge.getFirstVertex())){
                    for(int j = 0; j < listOfListsOfVisitedVertices.size(); j++){
                        if((listOfListsOfVisitedVertices.get(j)).contains(nextEdge.getSecondVertex())){
                            i.addAll(listOfListsOfVisitedVertices.get(j));
                            kruskalListOfEdges.add(nextEdge);
                            this.minWeight += nextEdge.getWeight();
                            listOfListsOfVisitedVertices.remove(j);
                            isIncluded = true;
                            break;
                        }
                    }
                }
                else if(i.contains(nextEdge.getSecondVertex())){
                    for(int j = 0; j < listOfListsOfVisitedVertices.size(); j++){
                        if((listOfListsOfVisitedVertices.get(j)).contains(nextEdge.getFirstVertex())){
                            i.addAll(listOfListsOfVisitedVertices.get(j));
                            listOfListsOfVisitedVertices.remove(j);
                            kruskalListOfEdges.add(nextEdge);
                            this.minWeight += nextEdge.getWeight();
                            isIncluded = true;
                            break;
                        }
                    }
                }
                if(isIncluded)
                    break;
            }
        }
    }

    private void primAlgorithm(){

    }

    private Edge getNextEdge(List<Edge> list){
        Edge nextEdge = new Edge(list.get(0));
        list.remove(0);
        return nextEdge;
    }

    public int getNumberOfIterationsOfPrim() {
        return numberOfIterationsOfPrim;
    }

    public int getNumberOfIterationsOfKruskal() {
        return numberOfIterationsOfKruskal;
    }

    public int getMinWeight(){
        return this.minWeight;
    }

    public List<Edge> getKruskalListOfEdges() {
        return new ArrayList<>(kruskalListOfEdges);
    }

    public List<Edge> getPrimListOfEdges() {
        return new ArrayList<>(primListOfEdges);
    }
}
