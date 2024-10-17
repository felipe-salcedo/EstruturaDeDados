import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        // Da valores para as variáveis inputVertices e inputArestas
        String inputVertices = JOptionPane.showInputDialog("Digite o número de vértices:");
        String inputArestas = JOptionPane.showInputDialog("Digite o número de arestas:");

        // Verifica se uma das variáveis está vazia, caso esteja, mostra a mensagem
        // "Vértices ou arestas vazias."
        if (inputVertices == null || inputArestas == null) {
            JOptionPane.showMessageDialog(null, "Vértices ou arestas vazias.");
            System.exit(0);
        }

        /*
         * passa os valores das variaveis inputVertices e inputArestas
         * para as variáveis numVertices e numArestas
         */
        int numVertices = Integer.parseInt(inputVertices);
        int numArestas = Integer.parseInt(inputArestas);

        int[][] matrizAdjacencia = new int[numVertices][numVertices]; // cria a estrutura da matriz juntando dois
                                                                      // vértices
        int numLaços = 0;
        int numArestasParalelas = 0;

        /*
         * O for processa a entrada de cada aresta e atualiza a matriz, laços e arestas
         * paralelas
         * que foram requisitadas nas variáveis acima
         */
        for (int i = 0; i < numArestas; i++) {
            /*
             * O usuário insere os vértices de origem e destino, por exemplo "1 2",
             * separados por espaço
             */
            String input = JOptionPane.showInputDialog("Digite a aresta " + (i + 1) + " (origem destino):");
            // verifica se o usuário clicou no botao "Cancelar" e exibe a mensagem "Entrada
            // cancelada. Encerrando o programa."
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Entrada cancelada.");
                System.exit(0);
            }
            /*
             * Verifica se o usuário separou os vértices de
             * entrada e destino por espaços, caso não tenha separado
             * desse jeito, exibe a mensagem
             * "Os vértices precisam ser separados por um espaço. Tente novamente." e
             * volta a esta etapa para o usuário digitar corretamente
             */
            String[] partes = input.split(" ");

            if (partes.length != 2) {
                JOptionPane.showMessageDialog(null,
                        "Os vértices precisam ser separados por um espaço. Tente novamente.");
                i--;
                continue;
            }

            // Converte o texto (string) para números (int)
            int origem = Integer.parseInt(partes[0]) - 1;
            int destino = Integer.parseInt(partes[1]) - 1;

            // Verifica se o valor das variáveis são menores que zero. Caso sejam, pede ao
            // usuário que faça novamente
            if (origem < 0 || origem >= numVertices || destino < 0 || destino >= numVertices) {
                JOptionPane.showMessageDialog(null, "Vértices menores que zero. Tente novamente.");
                i--;
                continue;
            }

            // Verifica se existe um laço no grafo. Isso ocorre caso os vértices de origem e
            // destino forem os mesmos
            if (origem == destino) {
                numLaços++;
            }

            // Contar arestas paralelas
            if (matrizAdjacencia[origem][destino] > 0) {
                numArestasParalelas++;
            }

            // Adiciona uma aresta entre os vértices de origem e destino cada vez que é
            // executada
            matrizAdjacencia[origem][destino]++;
        }

        // Cria a StringBuilder para o Título
        StringBuilder matrizBuilder = new StringBuilder("Matriz de Adjacência para Dígrafo:\n");

        // Loop for para percorrer as linhas da matriz
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            // Loop for para percorrer as colunas da matriz
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                matrizBuilder.append(matrizAdjacencia[i][j]).append(" "); // Adiciona cada elemento ao StringBuilder seguido de um espaço para separar os números
            }
            /** Adiciona uma quebra de linha após percorrer os elementos de uma linha
             * para começar a proxima linha da matriz na linha de baixo
             */
            matrizBuilder.append("\n");
        }

        // Cria a StringBuilder para o Título
        StringBuilder grauBuilder = new StringBuilder("\nGrau de cada vértice:\n");
        // Loop para percorrer os vértices do grafo
        for (int i = 0; i < numVertices; i++) {
            int grauEntrada = 0; // Armazena o grau de entrada do vértice
            int grauSaida = 0; // Armazena o grau de saída do vértice
            // Percorre os outros vértices para calcular o grau de entrada e saída do vértice atual
            for (int j = 0; j < numVertices; j++) {
                /** soma o valor da célula matrizAdjacencia[j][i] à variável grauEntrada e GrauSaida. 
                 * Esse valor indica se há uma aresta do vértice j para o vértice i. 
                 * Se o valor for diferente de 0, significa que existe uma aresta de 
                 * entrada no vértice i a partir de j, e incrementa o grau de entrada e de saída.*/
                grauEntrada += matrizAdjacencia[j][i];
                grauSaida += matrizAdjacencia[i][j];
            }

            // Depois de calcular os graus de entrada e saida, adiciona a string "Vértice " seguida do número do vértice ao StringBuilder.
            grauBuilder.append("Vértice ").append(i + 1).append(": Grau de Entrada = ").append(grauEntrada)
                    .append(", Grau de Saída = ").append(grauSaida).append("\n");
        }

        // Verifica se é um dígrafo simples
        String digrafoSimples = (numLaços == 0 && numArestasParalelas == 0) ? "Sim" : "Não";

        // Solicitaa vértice para análise dos vizinhos, caso o valor seja nulo, a entrada é cancelada
        String inputVertice = JOptionPane.showInputDialog("Digite o vértice que deseja analisar para vizinhos:");
        if (inputVertice == null) {
            JOptionPane.showMessageDialog(null, "Entrada cancelada. Campo vazio.");
            System.exit(0);
        }
        int verticeAnalisado = Integer.parseInt(inputVertice) - 1;

        // Caso o vértice seja menor que zero ou o vertice a ser analisado for maior ou igual ao numero de vértices, mostra a mensagem de vértice fora dos limites
        if (verticeAnalisado < 0 || verticeAnalisado >= numVertices) {
            JOptionPane.showMessageDialog(null, "Vértice fora dos limites. Encerrando o programa.");
            System.exit(0);
        }

        // Mostra os vértices vizinhos
        StringBuilder vizinhosBuilder = new StringBuilder("\nVizinhos do vértice " + (verticeAnalisado + 1) + ":\n");
        for (int j = 0; j < numVertices; j++) {
            if (matrizAdjacencia[verticeAnalisado][j] > 0) {
                vizinhosBuilder.append("Vértice ").append(j + 1).append("\n");
            }
        }

        // Informa quantidade de laços e arestas paralelas em um Resumo
        StringBuilder laçosParalelasBuilder = new StringBuilder("\nResumo:\n");
        laçosParalelasBuilder.append("Dígrafo simples: ").append(digrafoSimples).append("\n")
                .append("Quantidade de laços: ").append(numLaços).append("\n")
                .append("Quantidade de arestas paralelas: ").append(numArestasParalelas).append("\n");

        JOptionPane.showMessageDialog(null, matrizBuilder.toString() + grauBuilder.toString()
                + vizinhosBuilder.toString() + laçosParalelasBuilder.toString());
    }
}
