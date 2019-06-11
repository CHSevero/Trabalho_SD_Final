import java.net.Socket;

public class Message {
    private int tipoMensagem;
    private int  idRequisicao;
    //private int referenciaRemota;
    private int idOperacao;
    private Livro livro;



    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setIdOperacao(int idOperacao) {
        this.idOperacao = idOperacao;
    }

    public void setTipoMensagem(int tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    public void setIdRequisicao(int idRequisicao) {
        this.idRequisicao = idRequisicao;
    }



    public int getTipoMensagem() {
        return tipoMensagem;
    }

    public int getIdRequisicao() {
        return idRequisicao;
    }


    public int getIdOperacao() {
        return idOperacao;
    }


}
