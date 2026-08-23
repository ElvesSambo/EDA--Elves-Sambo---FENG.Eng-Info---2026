package TPC1;

public class PilhaEncadeada {
    private No topo;
    private int tamanho;

    public PilhaEncadeada() {
        this.topo = null;
        this.tamanho = 0;
    }

    public void push(Posicao pos) {
        No novoNo = new No(pos);
        novoNo.setProximo(topo);
        topo = novoNo;
        tamanho++;
    }

    public Posicao pop() {
        if (isEmpty()) {
            return null;
        }
        Posicao pos = topo.getPosicao();
        topo = topo.getProximo();
        tamanho--;
        return pos;
    }

    public boolean isEmpty() {
        return topo == null;
    }

    public int getTamanho() {
        return tamanho;
    }
}