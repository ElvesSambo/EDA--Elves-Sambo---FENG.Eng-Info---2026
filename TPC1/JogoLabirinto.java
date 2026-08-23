package TPC1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class JogoLabirinto {

    private static final char PAREDE = '#';
    private static final char CAMINHO = ' ';
    private static final char JOGADOR = 'P';
    private static final char SAIDA = 'S';

    //Labirinto do jogo
    private char[][] mapa = {
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', 'P', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#'},
        {'#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#'},
        {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
        {'#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#'},
        {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', 'S', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
    };

    private int linhaJogador = 1;
    private int colunaJogador = 1;
    private PilhaEncadeada historicoPosicoes;

    // Construtor do Jogo
    public JogoLabirinto() {
        this.historicoPosicoes = new PilhaEncadeada();
    }

    // Iniciar o jogo 
    public void iniciar() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean jogando = true;

        System.out.println("====================================");
        System.out.println("|      BEM-VINDO AO LABIRINTO      |");
        System.out.println("====================================");

        while (jogando) {
            imprimirLabirinto();
            System.out.println("Controlos: [W] Cima | [S] Baixo | [A] Esquerda | [D] Direita");
            System.out.println("           [V] Voltar Movimento (Undo) | [Q] Sair");
            System.out.print("Sua jogada: ");
            
            String entrada = br.readLine();
            
            if (entrada == null || entrada.trim().isEmpty()) {
                continue;
            }
            
            entrada = entrada.trim().toUpperCase();
            char comando = entrada.charAt(0);

            switch (comando) {
                case 'W': mover(-1, 0); break;
                case 'S': mover(1, 0); break;
                case 'A': mover(0, -1); break;
                case 'D': mover(0, 1); break;
                case 'V': desfazerMovimento(); break;
                case 'Q':
                    jogando = false;
                    System.out.println("Jogo encerrado!");
                    break;
                default:
                    System.out.println("\n[!] Comando invalido. Tente novamente.");
            }

            if (mapa[linhaJogador][colunaJogador] == SAIDA) {
                imprimirLabirinto();
                System.out.println("===================================================");
                System.out.println("\nPARABENS! Voce encontrou a saida e venceu o jogo!");
                System.out.println("===================================================");
                jogando = false;
            }
        }
        br.close();
    }

    // Impressao do labirinto com tudo como esta actualmente
    private void imprimirLabirinto() {
        System.out.println("\nMovimentos realizados: " + historicoPosicoes.getTamanho());
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void mover(int dLinha, int dColuna) {
        int novaLinha = linhaJogador + dLinha;
        int novaColuna = colunaJogador + dColuna;

        if (novaLinha < 0 || novaLinha >= mapa.length || 
            novaColuna < 0 || novaColuna >= mapa[0].length || 
            mapa[novaLinha][novaColuna] == PAREDE) {
            System.out.println("\n[!] Movimento invalido! Parede no caminho.");
            return;
        }

        historicoPosicoes.push(new Posicao(linhaJogador, colunaJogador));

        mapa[linhaJogador][colunaJogador] = CAMINHO;
        linhaJogador = novaLinha;
        colunaJogador = novaColuna;

        if (mapa[linhaJogador][colunaJogador] != SAIDA) {
            mapa[linhaJogador][colunaJogador] = JOGADOR;
        }
    }

    private void desfazerMovimento() {
        if (historicoPosicoes.isEmpty()) {
            System.out.println("\n[!] Nenhum movimento para desfazer!");
            return;
        }

        Posicao ultimaPosicao = historicoPosicoes.pop();

        mapa[linhaJogador][colunaJogador] = CAMINHO;

        linhaJogador = ultimaPosicao.getLinha();
        colunaJogador = ultimaPosicao.getColuna();
        mapa[linhaJogador][colunaJogador] = JOGADOR;

        System.out.println("\n[<-] Movimento desfeito com sucesso!");
    }
}