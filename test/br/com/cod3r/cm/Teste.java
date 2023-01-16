package br.com.cod3r.cm;

import org.junit.Test;
import static   org.junit.jupiter.api.Assertions.*;

public class Teste {
    @Test
    public void testarSeIgualDois() {
        int a = 1 + 1;

        assertEquals(2, a);
    }
    @Test
    public void testarSeIgualATres(){
        int x = 2 + 10 - 9;

        assertEquals(3, x);
    }
}
