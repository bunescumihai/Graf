import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<>(List.of(
                new ArrayList<>(List.of(1,2)),
                new ArrayList<>(List.of(0,3,4)),
                new ArrayList<>(List.of(0,3)),
                new ArrayList<>(List.of(1,2,4)),
                new ArrayList<>(List.of(1,3))

        ));

        Graf graf = new Graf(list);
        System.out.println(graf.getListOfEdges());

        graf.setEdge(0,1,6);
        graf.setEdge(0,2,2);
        graf.setEdge(1,3,3);
        graf.setEdge(1,4,5);
        graf.setEdge(2,3,4);
        graf.setEdge(3,4,1);

        System.out.println(graf.getListOfEdges());
        graf.setByDefaultSortedListOfEdges();
        System.out.println(graf.getListOfEdges());

        AlgorithmPrimAndKruskal alg = new AlgorithmPrimAndKruskal(graf);

        System.out.println(alg.getKruskalListOfEdges());
        System.out.println(alg.getKruskalMinWeight());

        graf.setEdge(0,1,1);
        graf.setEdge(0,2,2);
        graf.setEdge(1,3,6);
        graf.setEdge(1,4,5);
        graf.setEdge(2,3,3);
        graf.setEdge(3,4,4);

        alg = new AlgorithmPrimAndKruskal(graf);
        System.out.println(alg.getKruskalListOfEdges());
        System.out.println(alg.getKruskalMinWeight());
        System.out.println(alg.getKruskalIterations());

        graf.setEdge(0,1,9);

        alg = new AlgorithmPrimAndKruskal(graf);
        System.out.println(alg.getKruskalListOfEdges());
        System.out.println(alg.getKruskalMinWeight());
        System.out.println(alg.getSortingKruskalIterations());
        System.out.println(alg.getKruskalIterations());

        System.out.println("\n\n\n\n---------------------------------------------");
        System.out.println(alg.getPrimListOfEdges());
        System.out.println(alg.getPrimMinWeight());
        System.out.println(alg.getPrimIterations());


        for(int i = 4; i <= 40; i++){
            graf = new Graf(i,0);
            alg = new AlgorithmPrimAndKruskal(graf);

            System.out.println("----------------- " + i + " --------------------");
            System.out.println("Prim iteratii = " + alg.getPrimIterations());
            System.out.println("Kruskal iteratii = " + alg.getKruskalIterations() + alg.getSortingKruskalIterations());

        }

    }
}
