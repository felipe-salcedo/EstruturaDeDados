import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        //Da valores para as variáveis inputVertices e inputArestas
        String inputVertices = JOptionPane.showInputDialog("Digite o número de vértices:");
        String inputArestas = JOptionPane.showInputDialog("Digite o número de arestas:");

        //Verifica se uma das variáveis está vazia, caso esteja, mostra a mensagem "Vértices ou arestas vazias. Encerrando o programa."
        if (inputVertices == null || inputArestas == null) {
            JOptionPane.showMessageDialog(null, "Vértices ou arestas vazias. Encerrando o programa.");
            System.exit(0);
        }

        /*  passa os valores das variaveis inputVertices e inputArestas 
            para as variáveis numVertices e numArestas
        */
        int numVertices = Integer.parseInt(inputVertices);
        int numArestas = Integer.parseInt(inputArestas);

        int[][] matrizAdjacencia = new int[numVertices][numVertices]; //cria a estrutura da matriz juntando dois vértices
        int numLaços = 0;
        int numArestasParalelas = 0;

        /* O for processa a entrada de cada aresta e atualiza a matriz, laços e arestas paralelas
         * que foram requisitadas nas variáveis acima
        */
        for (int i = 0; i < numArestas; i++) {
            /*  O usuário insere os vértices de origem e destino, por exemplo "1 2", 
                separados por espaço
            */
            String input = JOptionPane.showInputDialog("Digite a aresta " + (i + 1) + " (origem destino):");
            //verifica se o usuário clicou no botao "Cancelar" e exibe a mensagem "Entrada cancelada. Encerrando o programa."
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Entrada cancelada. Encerrando o programa.");
                System.exit(0);
            }
            /*Verifica se o usuário separou os vértices de 
             * entrada e destino por espaços, caso não tenha separado
             * desse jeito exibe a mensagem "Os vértices precisam ser separados por um espaço. Tente novamente." e 
             * volta a esta etapa para o usuário digitar corretamente
             */
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

            if (origem == destino) {
                numLaços++;
            }

            // Contar arestas paralelas
            if (matrizAdjacencia[origem][destino] > 0) {
                numArestasParalelas++;
            }

            matrizAdjacencia[origem][destino]++;
        }

        // Imprimir a matriz de adjacência
        StringBuilder matrizBuilder = new StringBuilder("Matriz de Adjacência para Dígrafo:\n");
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                matrizBuilder.append(matrizAdjacencia[i][j]).append(" ");
            }
            matrizBuilder.append("\n");
        }

        // Determinar o grau de entrada e saída de cada vértice
        StringBuilder grauBuilder = new StringBuilder("\nGrau de cada vértice:\n");
        for (int i = 0; i < numVertices; i++) {
            int grauEntrada = 0;
            int grauSaida = 0;

            for (int j = 0; j < numVertices; j++) {
                grauEntrada += matrizAdjacencia[j][i];
                grauSaida += matrizAdjacencia[i][j];
            }

            grauBuilder.append("Vértice ").append(i + 1).append(": Grau de Entrada = ").append(grauEntrada)
                    .append(", Grau de Saída = ").append(grauSaida).append("\n");
        }

        // Verificar se é dígrafo simples
        String digrafoSimples = (numLaços == 0 && numArestasParalelas == 0) ? "Sim" : "Não";

        // Solicitar vértice para análise de vizinhos
        String inputVertice = JOptionPane.showInputDialog("Digite o vértice a ser analisado para vizinhos:");
        if (inputVertice == null) {
            JOptionPane.showMessageDialog(null, "Entrada cancelada. Encerrando o programa.");
            System.exit(0);
        }
        int verticeAnalisado = Integer.parseInt(inputVertice) - 1;
        if (verticeAnalisado < 0 || verticeAnalisado >= numVertices) {
            JOptionPane.showMessageDialog(null, "Vértice fora dos limites. Encerrando o programa.");
            System.exit(0);
        }

        StringBuilder vizinhosBuilder = new StringBuilder("\nVizinhos do vértice " + (verticeAnalisado + 1) + ":\n");
        for (int j = 0; j < numVertices; j++) {
            if (matrizAdjacencia[verticeAnalisado][j] > 0) {
                vizinhosBuilder.append("Vértice ").append(j + 1).append("\n");
            }
        }

        // Informar quantidade de laços e arestas paralelas
        StringBuilder laçosParalelasBuilder = new StringBuilder("\nResumo:\n");
        laçosParalelasBuilder.append("Dígrafo simples: ").append(digrafoSimples).append("\n")
                .append("Quantidade de laços: ").append(numLaços).append("\n")
                .append("Quantidade de arestas paralelas: ").append(numArestasParalelas).append("\n");

        JOptionPane.showMessageDialog(null, matrizBuilder.toString() + grauBuilder.toString()
                + vizinhosBuilder.toString() + laçosParalelasBuilder.toString());
    }
}
