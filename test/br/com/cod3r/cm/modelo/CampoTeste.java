package br.com.cod3r.cm.modelo;

import br.com.cod3r.cm.excecao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CampoTeste {

    private Campo campo;
    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }
    @Test
    void testeVizinhoDistancia1Esquerda() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1Direta() {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1EmCima() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia1EmBaixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistancia2() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(5, 5);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }
    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }
    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }
    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();

        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1() {
        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2() {
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 2);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }

    @Test
    void testeReiniciar() {
        campo.reiniciar();
        assertFalse(campo.isAberto() && campo.isMarcado() && campo.isAberto());
    }

    @Test
    void testeSetAberto() {
        campo.setAberto(true);
        boolean resultado = campo.isAberto();
        assertTrue(resultado);
    }

    @Test
    void testeGetLinha() {
        boolean resultado = campo.getLinha() == 3;
        assertTrue(resultado);
    }

    @Test
    void testeGetColuna() {
        boolean resultado = campo.getColuna() == 3;
        assertTrue(resultado);
    }

    @Test
    void testeObjetivoAlcancadoMinado() {
        campo.minar();
        campo.alternarMarcacao();
        boolean resultado = campo.objetivoAlcancado();
        assertTrue(resultado);
    }

    @Test
    void testeObjetivoAlcancadoNaoMinado() {
        campo.abrir();
        boolean resultado = campo.objetivoAlcancado();
        assertTrue(resultado);
    }

    @Test
    void testeMinasNaVizinhaca() {
        Campo campo32 = new Campo(3, 4);
        Campo campo43 = new Campo(4, 3);
        campo32.minar();
        campo.adicionarVizinho(campo32);
        campo.adicionarVizinho(campo43);

        int minas = (int) campo.minasNaVizinhanca();
        boolean resultado = minas == 1;
        assertTrue(resultado);
    }
}
