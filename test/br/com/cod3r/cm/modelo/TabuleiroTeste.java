package br.com.cod3r.cm.modelo;

import br.com.cod3r.cm.excecao.ExplosaoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TabuleiroTeste {

    private Tabuleiro tabuleiro;

    @BeforeEach
    void iniciarTabuleiro() {
        tabuleiro = new Tabuleiro(6, 6, 3);
    }
    @Test
    void testeAbrir() {
        int linha = 1;
        int coluna = 1;
        tabuleiro.abrir(linha,coluna);
        boolean resultado = tabuleiro.campos.stream()
                .anyMatch(c -> c.getLinha() == linha && c.getColuna() == coluna && c.isAberto());
        assertTrue(resultado);
    }

    @Test
    void testeAbrirException() {
        tabuleiro = new Tabuleiro(1, 1, 1);
        int linha = 0;
        int coluna = 0;
        assertThrows(ExplosaoException.class, () -> {
            tabuleiro.abrir(linha,coluna);
        });
    }

    @Test
    void testeAlternarMarcacao(){
        int linha = 1;
        int coluna = 1;
        tabuleiro.alternarMarcacao(linha,coluna);
        boolean resultado = tabuleiro.campos.stream()
                .anyMatch(c -> c.getLinha() == linha && c.getColuna() == coluna && c.isMarcado());
        assertTrue(resultado);
    }
    @Test
    void testeReiniciar() {
        tabuleiro.reiniciar();
        int fechadoCount = (int) tabuleiro.campos.stream().filter(c -> c.isFechado()).count();
        boolean resultado = fechadoCount == 36;
        assertTrue(resultado);
    }

    @Test
    void testeReiniciarFalso() {
        tabuleiro.reiniciar();
        tabuleiro.abrir(1, 1);
        int fechadoCount = (int) tabuleiro.campos.stream().filter(c -> c.isFechado()).count();
        boolean resultado = fechadoCount == 36;
        assertFalse(resultado);
    }

    @Test
    void testeToString() {
        String esperada = "   0  1  2  3  4  5 \n" +
                          "0  ?  ?  ?  ?  ?  ? \n" +
                          "1  ?  ?  ?  ?  ?  ? \n" +
                          "2  ?  ?  ?  ?  ?  ? \n" +
                          "3  ?  ?  ?  ?  ?  ? \n" +
                          "4  ?  ?  ?  ?  ?  ? \n" +
                          "5  ?  ?  ?  ?  ?  ? \n";
        boolean resultado = esperada.equalsIgnoreCase(tabuleiro.toString());
        assertTrue(resultado);
    }

    @Test
    void testeObjetivoAlcançado() {
        tabuleiro = new Tabuleiro(1, 1, 0);
        tabuleiro.alternarMarcacao(0, 0);
        boolean resultado = tabuleiro.objetivoAlcancado();
        assertTrue(resultado);
    }

}
