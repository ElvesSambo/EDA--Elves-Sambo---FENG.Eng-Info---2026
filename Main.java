package TPC1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            JogoLabirinto jogo = new JogoLabirinto();
            
            jogo.iniciar();
            
        } catch (IOException e) {
            System.out.println("Ocorreu um erro de leitura: " + e.getMessage());
        }
    }
}