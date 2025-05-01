package sommet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SommetTest {


    @Test
    void testEquals() {
        Sommet s1 = new Sommet(5,4);
        Sommet s2 = new Sommet(5,4);
        Sommet s3 = new Sommet(5,8);
        assertTrue(s1.equals(s2));
        assertFalse(s1.equals(s3));
        assertFalse(s2.equals(s3));
    }

    @Test
    void testToString() {
        Sommet s1 = new Sommet(5,4);
        String s = s1.toString();
        assertTrue(s.contains("5"));
        assertTrue(s.contains("4"));
        assertFalse(s.contains("8"));
        s1.toString();
        System.out.println(s1.getSommet());

    }
}