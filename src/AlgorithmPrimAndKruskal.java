import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgorithmPrimAndKruskal {
    private final Graf graf;
    private int primIterations = 0;
    private int kruskalIterations = 0;
    private int sortingKruskalIterations;
    private int kruskalMinWeight = 0;
    private int primMinWeight = 0;
    private List<Edge> kruskalListOfEdges = new ArrayList<Edge>();
    private List<Edge> primListOfEdges = new ArrayList<Edge>();
    private List<Edge> listOfEdges;

    public AlgorithmPrimAndKruskal(Graf graf){
        this.graf = graf;
        graf.setByDefaultSortedListOfEdges();
        this.listOfEdges = graf.getListOfEdges();
        this.sortingKruskalIterations = graf.getSortingIterations();
        kruskalAlgorithm();
        primAlgorithm();
    }

    private void kruskalAlgorithm(){
        List<Edge> list = new ArrayList<>(this.listOfEdges);
        List<List<Integer>> listOfListsOfVisitedVertices = new ArrayList<>();
        List<Integer> listOfVisitedVertex = new ArrayList<Integer>();
        kruskalIterations+=6;

        while(listOfVisitedVertex.size() != graf.getNumberOfVertices()){
            Edge nextEdge = getNextEdge(list);
            boolean isIncluded = false;
            kruskalIterations+=3;

            for(List<Integer> i : listOfListsOfVisitedVertices){
                kruskalIterations++;
                if(i.contains(nextEdge.getFirstVertex()) && i.contains(nextEdge.getSecondVertex())){
                    isIncluded = true;
                    kruskalIterations+=4;
                    break;
                }
                else if(i.contains(nextEdge.getFirstVertex())){
                    kruskalIterations+=5;
                    for(List<Integer> j : listOfListsOfVisitedVertices){
                        kruskalIterations+=3;
                        if(j.contains(nextEdge.getSecondVertex())){
                            i.addAll(j);
                            kruskalMinWeight += nextEdge.getWeight();
                            kruskalListOfEdges.add(nextEdge);
                            listOfListsOfVisitedVertices.remove(j);
                            isIncluded = true;
                            kruskalIterations+=8;
                            break;
                        }
                    }

                    if(isIncluded)
                        break;

                    i.add(nextEdge.getSecondVertex());
                    kruskalMinWeight += nextEdge.getWeight();
                    kruskalListOfEdges.add(nextEdge);
                    listOfVisitedVertex.add(nextEdge.getSecondVertex());
                    isIncluded = true;
                    kruskalIterations+=10;
                    break;
                }
                else if(i.contains(nextEdge.getSecondVertex())){
                    kruskalIterations+=6;
                    for(List<Integer> j : listOfListsOfVisitedVertices){
                        kruskalIterations+=3;
                        if(j.contains(nextEdge.getFirstVertex())){
                            i.addAll(j);
                            kruskalMinWeight += nextEdge.getWeight();
                            kruskalListOfEdges.add(nextEdge);
                            listOfListsOfVisitedVertices.remove(j);
                            isIncluded = true;
                            kruskalIterations+=8;
                            break;
                        }
                    }
                    if(isIncluded)
                        break;
                    i.add(nextEdge.getFirstVertex());
                    kruskalMinWeight += nextEdge.getWeight();
                    kruskalListOfEdges.add(nextEdge);
                    listOfVisitedVertex.add(nextEdge.getFirstVertex());
                    isIncluded = true;
                    kruskalIterations+=10;
                    break;
                }
            }

            if(!isIncluded){
                listOfListsOfVisitedVertices.add(new ArrayList<Integer>());
                List<Integer> lastLine = listOfListsOfVisitedVertices.get(listOfListsOfVisitedVertices.size()-1);
                lastLine.add(nextEdge.getFirstVertex());
                lastLine.add(nextEdge.getSecondVertex());
                listOfVisitedVertex.add(nextEdge.getFirstVertex());
                listOfVisitedVertex.add(nextEdge.getSecondVertex());
                kruskalMinWeight += nextEdge.getWeight();
                kruskalListOfEdges.add(nextEdge);
                kruskalIterations+= 18;
            }
            kruskalIterations++;

        }

        while (listOfListsOfVisitedVertices.size() != 1){
            Edge nextEdge = getNextEdge(list);
            kruskalIterations+=2;

            for(List<Integer> i: listOfListsOfVisitedVertices){
                boolean isIncluded = false;
                kruskalIterations+=8;
                if(i.contains(nextEdge.getFirstVertex()) && i.contains(nextEdge.getSecondVertex())){
                    kruskalIterations++;
                    isIncluded = true;
                }
                else if(i.contains(nextEdge.getFirstVertex())){
                    kruskalIterations+=2;
                    for(List<Integer> j: listOfListsOfVisitedVertices){
                        kruskalIterations+=3;
                        if(j.contains(nextEdge.getSecondVertex())){
                            kruskalIterations+=8;
                            i.addAll(j);
                            kruskalListOfEdges.add(nextEdge);
                            this.kruskalMinWeight += nextEdge.getWeight();
                            listOfListsOfVisitedVertices.remove(j);
                            isIncluded = true;
                            break;
                        }
                    }
                }
                else if(i.contains(nextEdge.getSecondVertex())){
                    kruskalIterations+=4;
                    for(List<Integer> j: listOfListsOfVisitedVertices){
                        kruskalIterations+=3;
                        if(j.contains(nextEdge.getFirstVertex())){
                            kruskalIterations+=8;
                            i.addAll(j);
                            listOfListsOfVisitedVertices.remove(j);
                            kruskalListOfEdges.add(nextEdge);
                            this.kruskalMinWeight += nextEdge.getWeight();
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
        List<Integer> listOfVisitedVetices = new ArrayList<Integer>();
        List<Edge> listOfEdges = graf.getListOfEdges();

        Random random = new Random();
        int firstVertex = random.nextInt(graf.getNumberOfVertices());
        listOfVisitedVetices.add(firstVertex);

        primIterations +=8;

        while(listOfVisitedVetices.size() < graf.getNumberOfVertices()){
            Edge edgeWithMinWeight = new Edge(0,0,99999);
            primIterations += 5;

            for(Integer i: listOfVisitedVetices){
                primIterations++;
                for(Edge e : listOfEdges){
                    primIterations+=5;
                    if(e.getFirstVertex() == i || e.getSecondVertex() == i){
                        primIterations+=3;
                        if(e.getWeight() < edgeWithMinWeight.getWeight()){
                            primIterations++;
                            edgeWithMinWeight = e;
                        }
                    }
                }
            }

            primListOfEdges.add(edgeWithMinWeight);
            primMinWeight += edgeWithMinWeight.getWeight();
            primIterations +=8;

            if(!listOfVisitedVetices.contains(edgeWithMinWeight.getFirstVertex()))
                listOfVisitedVetices.add(edgeWithMinWeight.getFirstVertex());
            else
                listOfVisitedVetices.add(edgeWithMinWeight.getSecondVertex());

            listOfEdges.remove(edgeWithMinWeight);
            primIterations++;
        }

    }

    private Edge getNextEdge(List<Edge> list){
        Edge nextEdge = new Edge(list.get(0));
        list.remove(0);
        kruskalIterations+=2;
        return nextEdge;
    }

    public int getPrimIterations() {
        return primIterations;
    }

    public int getKruskalIterations() {
        return kruskalIterations;
    }

    public int getKruskalMinWeight(){
        return this.kruskalMinWeight;
    }

    public int getPrimMinWeight(){
        return this.primMinWeight;
    }

    public int getSortingKruskalIterations() {
        return sortingKruskalIterations;
    }

    public List<Edge> getKruskalListOfEdges() {
        return new ArrayList<>(kruskalListOfEdges);
    }

    public List<Edge> getPrimListOfEdges() {
        return new ArrayList<>(primListOfEdges);
    }
}
