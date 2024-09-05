import javax.swing.JOptionPane;

public class MatrizAdjacenciaGrafo {

    public static void main(String[] args) {
        String inputVertices = JOptionPane.showInputDialog("Digite o número de vértices:");
        String inputArestas = JOptionPane.showInputDialog("Digite o número de arestas:");

        if (inputVertices == null || inputArestas == null) {
            JOptionPane.showMessageDialog(null, "Entrada cancelada. Encerrando o programa.");
            System.exit(0);
        }

        int numVertices = Integer.parseInt(inputVertices);
        int numArestas = Integer.parseInt(inputArestas);

        int[][] matrizAdjacencia = new int[numVertices][numVertices];

        for (int i = 0; i < numArestas; i++) {
            String input = JOptionPane.showInputDialog("Digite a aresta " + (i + 1) + " (v1 v2):");
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Entrada cancelada. Encerrando o programa.");
                System.exit(0);
            }
            String[] partes = input.split(" ");
            if (partes.length != 2) {
                JOptionPane.showMessageDialog(null, "Entrada inválida para a aresta. Tente novamente.");
                i--;
                continue;
            }
            int v1 = Integer.parseInt(partes[0]) - 1;
            int v2 = Integer.parseInt(partes[1]) - 1;

            if (v1 < 0 || v1 >= numVertices || v2 < 0 || v2 >= numVertices) {
                JOptionPane.showMessageDialog(null, "Vértices fora dos limites. Tente novamente.");
                i--;
                continue;
            }

            matrizAdjacencia[v1][v2] = 1;
            matrizAdjacencia[v2][v1] = 1;
        }

        StringBuilder matrizBuilder = new StringBuilder("Matriz de Adjacência:\n");
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                matrizBuilder.append(matrizAdjacencia[i][j]).append(" ");
            }
            matrizBuilder.append("\n");
        }

        StringBuilder grauBuilder = new StringBuilder("\nGrau de cada vértice:\n");
        for (int i = 0; i < numVertices; i++) {
            int grau = 0;
            for (int j = 0; j < numVertices; j++) {
                grau += matrizAdjacencia[i][j];
            }
            grauBuilder.append("Vértice ").append(i + 1).append(": Grau = ").append(grau).append(", Simples = ")
                    .append(grau == 1 ? "Sim" : "Não").append("\n");
        }

        JOptionPane.showMessageDialog(null, matrizBuilder.toString() + grauBuilder.toString());
    }
}

/*
 * 1-) impelmentar a matriz com valores fornecidos pelo usuario
 * perguntar quantos vértices e quantas arestas
 * 
 * 2-) Imprimir a matriz
 * 
 * 3-) Determinar o grau de cada vértice e se ele é símples ou não
 */