public class Main {
    public static void main(String[] args) {
        Graf graf = new Graf(5,7);
        graf.setRandomWeights();
        graf.showListOfEdgesWithWights();

        AlgorithmPrimAndKruskal alg = new AlgorithmPrimAndKruskal(graf);
        graf.showListOfEdgesWithWights();
        System.out.println(alg.getKruskalListOfEdges());

    }
}
