import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graf {
    private List<List<Integer>> adjacencyList = new ArrayList<>();
    private int numberOfVertices;
    private int numberOfEdges;
    private List<Edge> listOfEdges = new ArrayList<Edge>();
    private Edges edges;

    public Graf(int numberOfVertices, int numberOfEdges){
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = numberOfEdges;

        if(numberOfEdges < numberOfVertices-1)
            this.numberOfEdges = numberOfVertices-1;

        if(numberOfEdges > (numberOfVertices*numberOfVertices - numberOfVertices) /2)
            this.numberOfEdges = (numberOfVertices*numberOfVertices - numberOfVertices) /2;

        generateGraf();

        edges = new Edges(this);
        this.listOfEdges = edges.getListOfEdges();

    }

    public Graf(List<List<Integer>> adjacencyList){
        this.adjacencyList = new ArrayList<>();

        for(List<Integer> i:adjacencyList)
            this.adjacencyList.add(new ArrayList<>(i));

        setNumberOfComponents();

        edges = new Edges(this);
        this.listOfEdges = edges.getListOfEdges();
    }

    public void setAdjacencyList(List<List<Integer>> adjacencyList){
        this.adjacencyList = adjacencyList;
        setNumberOfComponents();
    }

    public void setEdge(int firstVertex, int secondVertex){

        if(firstVertex < 0 || firstVertex >= getNumberOfVertices())
            return;
        if(secondVertex < 0 || secondVertex >= getNumberOfVertices())
            return;
        if((this.adjacencyList.get(firstVertex)).contains(secondVertex))
            return;

        (this.adjacencyList.get(firstVertex)).add(secondVertex);
        (this.adjacencyList.get(secondVertex)).add(firstVertex);
        listOfEdges.add(new Edge(firstVertex,secondVertex));
    }

    public void setByDefaultSortedListOfEdges(){
        this.listOfEdges = edges.getSortedListOfEdgesByWeight();
    }
    public void setEdge(int firstVertex, int secondVertex, int weight){

        if(firstVertex < 0 || firstVertex >= getNumberOfVertices())
            return;
        if(secondVertex < 0 || secondVertex >= getNumberOfVertices())
            return;

        if((this.adjacencyList.get(firstVertex)).contains(secondVertex)) {
            for(Edge i :listOfEdges){
                if(firstVertex == i.getFirstVertex() && secondVertex == i.getSecondVertex()){
                    i.setWeight(weight);
                    return;
                }
            }
        }

        (this.adjacencyList.get(firstVertex)).add(secondVertex);
        (this.adjacencyList.get(secondVertex)).add(firstVertex);
        this.listOfEdges.add(new Edge(firstVertex,secondVertex,weight));
    }
    private void initGraf(List<List<Integer>> list){
        for(int i = 0; i < getNumberOfVertices(); i++){
            list.add(new ArrayList<Integer>());
        }
        Random random = new Random();

        int nextVertex = random.nextInt(getNumberOfVertices()-1)+1;
        list.get(0).add(nextVertex);

        for(int i = 1; i < getNumberOfVertices()-1; i++){
            int temp = nextVertex;
            nextVertex = random.nextInt(getNumberOfVertices());

            while(!((list.get(nextVertex)).isEmpty()) || temp == nextVertex){
                nextVertex = random.nextInt(getNumberOfVertices());
            }

            list.get(temp).add(nextVertex);
        }

        for(int i = 0; i < getNumberOfVertices(); i++){
            List<Integer> line = list.get(i);
            if(line.isEmpty())
                continue;
            int vertex = line.get(0);
            if(!(list.get(vertex)).contains(i))
                (list.get(vertex)).add(i);
        }
    }

    private boolean haveEmptyVertex(List<List<Integer>> list){
        for(List<Integer> i : list){
            if(i.size() == 0)
                return true;
        }
        return false;
    }

    private void generateGraf(){
        List<List<Integer>> list = new ArrayList<>();
        Random random = new Random();
        initGraf(list);

        for(int i = getNumberOfVertices()-1; i< getNumberOfEdges(); i++){
            int randomSelectedVertex = random.nextInt(getNumberOfVertices());
            List<Integer> lineOfSelectedVertex = list.get(randomSelectedVertex);

            while(lineOfSelectedVertex.size() == getNumberOfVertices()-1){
                randomSelectedVertex = random.nextInt(getNumberOfVertices());
                lineOfSelectedVertex = list.get(randomSelectedVertex);
            }

            int randomToSelectedVertex = random.nextInt(getNumberOfVertices());

            while(randomToSelectedVertex == randomSelectedVertex || lineOfSelectedVertex.contains(randomToSelectedVertex))
                randomToSelectedVertex = random.nextInt(getNumberOfVertices());

            lineOfSelectedVertex.add(randomToSelectedVertex);
            (list.get(randomToSelectedVertex)).add(randomSelectedVertex);

        }

        this.adjacencyList = list;
    }

    private void setNumberOfComponents(){
        this.numberOfVertices = adjacencyList.size();
        this.numberOfEdges = 0;
        for(List<Integer> i : adjacencyList){
            this.numberOfEdges += i.size();
        }
    }

    public boolean edgeExists(int firstVertex, int secondVertex){

        if(firstVertex < 0 || firstVertex >= getNumberOfVertices())
            return false;
        if(secondVertex < 0 || secondVertex >= getNumberOfVertices())
            return false;

        for(int i = 0; i < this.listOfEdges.size(); i++){
            if(((this.listOfEdges.get(i)).getFirstVertex() == firstVertex && (this.listOfEdges.get(i)).getSecondVertex() == secondVertex)
                    || ((this.listOfEdges.get(i)).getSecondVertex() == firstVertex && (this.listOfEdges.get(i)).getFirstVertex() == secondVertex)){
                return true;
            }
        }
        return false;
    }

    public void showGraf(){
        for(int i = 0; i < getNumberOfVertices(); i++){
            System.out.println(i + ": " + this.adjacencyList.get(i));
        }
    }

    public int getNumberOfVertices(){
        return this.numberOfVertices;
    }

    public int getNumberOfEdges(){
        return this.numberOfEdges;
    }

    public int getWeight(int firstVertex, int secondVertex){
        if(edgeExists(firstVertex, secondVertex))
            for(Edge i : listOfEdges)
                if((i.getFirstVertex() == firstVertex && i.getSecondVertex() == secondVertex) || (i.getSecondVertex() == firstVertex && i.getFirstVertex() == secondVertex))
                    return i.getWeight();

        return 0;
    }

    public List<List<Integer>> getAdjacencyList() {
        List<List<Integer>> copy = new ArrayList<>();
        for(List<Integer> i : this.adjacencyList)
            copy.add(new ArrayList<>(i));

        return copy;
    }

    public void showListOfEdges(){
        for(Edge i: this.listOfEdges){
            System.out.print("(" + i.getFirstVertex() + "," + i.getSecondVertex() + "), ");
        }
        System.out.println();
    }

    public void showListOfEdgesWithWights(){
        for(Edge i: this.listOfEdges){
            System.out.print("(" + i.getFirstVertex() + "," + i.getSecondVertex() + ", " + i.getWeight() + "), ");
        }
        System.out.println();
    }

    public void removeEdge(int firstVertex, int secondVertex) {

        if (firstVertex < 0 || firstVertex >= getNumberOfVertices())
            return;
        if (secondVertex < 0 || secondVertex >= getNumberOfVertices())
            return;

        for (int i = 0; i < this.listOfEdges.size(); i++) {
            if (((this.listOfEdges.get(i)).getFirstVertex() == firstVertex && (this.listOfEdges.get(i)).getSecondVertex() == secondVertex)
                    || ((this.listOfEdges.get(i)).getSecondVertex() == firstVertex && (this.listOfEdges.get(i)).getFirstVertex() == secondVertex)) {
                this.listOfEdges.remove(i);
                (this.adjacencyList.get(firstVertex)).remove(Integer.valueOf(secondVertex));
                (this.adjacencyList.get(secondVertex)).remove(Integer.valueOf(firstVertex));
            }
        }
    }

    public void setRandomWeights(){
        Random random = new Random();
        for(Edge i : listOfEdges){
            setEdge(i.getFirstVertex(), i.getSecondVertex(), random.nextInt(25)+1);
        }
    }

    public List<Edge> getListOfEdges(){
        return new ArrayList<Edge>(this.listOfEdges);
    }

}
