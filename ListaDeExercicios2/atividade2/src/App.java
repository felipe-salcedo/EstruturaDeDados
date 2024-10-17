import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {

        String inputVertices = JOptionPane.showInputDialog("Digite o número de vértices:");
        String inputArestas = JOptionPane.showInputDialog("Digite o número de arestas:");

        /*
        * Aqui colocamos uma verificação para ver se as entradas são válidas.
        */

        if (inputVertices == null || inputArestas == null) {
            JOptionPane.showMessageDialog(null, "Vértices ou arestas vazias.");
            System.exit(0);
        }

        int numVertices = Integer.parseInt(inputVertices);
        int numArestas = Integer.parseInt(inputArestas);

        int[][] matrizIncidencia = new int[numVertices][numArestas];
        int numLaços = 0;
        int numArestasParalelas = 0;

        /*
        * Nesse trecho processamos as arestas fornecidas e construímos a matriz de incidência
        */

        for (int i = 0; i < numArestas; i++) {

            /*
            *  Aqui solicitamos com um input para o usuário escrever as vértices de origem e destino
            */

            String input = JOptionPane.showInputDialog("Digite a aresta " + (i + 1) + " (origem destino):");

            if (input == null) {
                JOptionPane.showMessageDialog(null, "Entrada cancelada. Encerrando o programa.");
                System.exit(0);
            }

            String[] partes = input.split(" ");
            if (partes.length != 2) {
                JOptionPane.showMessageDialog(null, "Os vértices precisam ser separados por um espaço. Tente novamente.");
                i--;
                continue;
            }

            int origem = Integer.parseInt(partes[0]) - 1;
            int destino = Integer.parseInt(partes[1]) - 1;

            if (origem < 0 || origem >= numVertices || destino < 0 || destino >= numVertices) {
                JOptionPane.showMessageDialog(null, "Vértices fora dos limites. Tente novamente.");
                i--;
                continue;
            }

            /*
            * Se a origem for igual ao destino, é um laço
            */
            
            if (origem == destino) {
                numLaços++;
            }

            /*
            * Aresta paralela
            */

            boolean arestaParalela = false;
            for (int j = 0; j < i; j++) {
                if (matrizIncidencia[origem][j] == 1 && matrizIncidencia[destino][j] == -1) {
                    arestaParalela = true;
                    break;
                }
            }
            if (arestaParalela) {
                numArestasParalelas++;
            }

            /*
            * Preenchimento para a matriz de incidência para dígrafo
            */

            matrizIncidencia[origem][i] = 1;
            matrizIncidencia[destino][i] = -1;
        }

        /*
        * Aqui nós estamos imprimindo a matriz de incidência na tela
        */

        StringBuilder matrizBuilder = new StringBuilder("Matriz de Incidência para Dígrafo:\n");
        for (int i = 0; i < matrizIncidencia.length; i++) {
            for (int j = 0; j < matrizIncidencia[i].length; j++) {
                matrizBuilder.append(matrizIncidencia[i][j]).append(" ");
            }
            matrizBuilder.append("\n");
        }

        /*
        * Parte de determinar o grau de cada vértice dos grafos e dígrafos
        */

        StringBuilder grauBuilder = new StringBuilder("\nGrau de cada vértice:\n");
        for (int i = 0; i < numVertices; i++) {
            int grauEntrada = 0;
            int grauSaida = 0;
            int grau = 0;

            for (int j = 0; j < numArestas; j++) {
                if (matrizIncidencia[i][j] == 1) {
                    grauSaida++;
                    grau++; // grafos
                } else if (matrizIncidencia[i][j] == -1) {
                    grauEntrada++;
                    grau++; // grafos
                }
            }

            grauBuilder.append("Vértice ").append(i + 1).append(": Grau de Entrada = ").append(grauEntrada)
                    .append(", Grau de Saída = ").append(grauSaida).append(", Grau (grafo) = ").append(grau).append("\n");
        }

        /*
        * Aqui solicitamos o vértice para o usuário para analisar os vizinhos
        */

        String inputVertice = JOptionPane.showInputDialog("Digite o vértice para a análise de vizinhos:");
        if (inputVertice == null) {
            JOptionPane.showMessageDialog(null, "Informação não aceita.");
            System.exit(0);
        }
        int verticeAnalisado = Integer.parseInt(inputVertice) - 1;
        if (verticeAnalisado < 0 || verticeAnalisado >= numVertices) {
            JOptionPane.showMessageDialog(null, "Vértice fora dos limites.");
            System.exit(0);
        }

        /*
        * Fizemos dessa forma para encontrar os vizinhos do vértice
        * Se é origem, deve encontrar destino. Se é destino, deve encontrar origem.
        */

        StringBuilder vizinhosBuilder = new StringBuilder("\nVizinhos do vértice " + (verticeAnalisado + 1) + ":\n");
        for (int j = 0; j < numArestas; j++) {
            if (matrizIncidencia[verticeAnalisado][j] == 1) {
                for (int i = 0; i < numVertices; i++) {
                    if (matrizIncidencia[i][j] == -1) {
                        vizinhosBuilder.append("Vértice ").append(i + 1).append(" (Destino)\n");
                    }
                }
            } else if (matrizIncidencia[verticeAnalisado][j] == -1) {
                for (int i = 0; i < numVertices; i++) {
                    if (matrizIncidencia[i][j] == 1) {
                        vizinhosBuilder.append("Vértice ").append(i + 1).append(" (Origem)\n");
                    }
                }
            }
        }


        /*
        * Informamos a quantidade de laços e arestas paralelas
        */
        
        StringBuilder laçosParalelasBuilder = new StringBuilder("\nResumo:\n");
        laçosParalelasBuilder.append("Quantidade de laços: ").append(numLaços).append("\n")
                .append("Quantidade de arestas paralelas: ").append(numArestasParalelas).append("\n");

        /*
         * Resultados finais
         */
        JOptionPane.showMessageDialog(null, matrizBuilder.toString() + grauBuilder.toString()
                + vizinhosBuilder.toString() + laçosParalelasBuilder.toString());
    }
}
