package TPC1;

public class No {
    private Posicao posicao;
    private No proximo;

    public No(Posicao posicao) {
        this.posicao = posicao;
        this.proximo = null;
    }

    public Posicao getPosicao() { return posicao; }
    public No getProximo() { return proximo; }
    public void setProximo(No proximo) { this.proximo = proximo; }
}