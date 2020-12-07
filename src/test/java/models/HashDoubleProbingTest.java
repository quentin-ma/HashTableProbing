package models;

import exceptions.KeyDoesNotExistException;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HashDoubleProbingTest {

    private HashDoubleProbing map;

    private final Character[] R = {'B', 'O', 'E', 'P', 'V', 'L', 'X', 'N', 'K', 'M'};

    @BeforeEach
    void setUp() {
        map = new HashDoubleProbing(Constant.M);
    }

    void init() {
        int i = 0;
        for (char r : R) {
            map.put(r,  i++);
        }
        System.out.println(Arrays.toString(map.keys()));
        System.out.println(Arrays.toString(map.values()));
    }

    @Test
    void putTest() {
        int i = 0;
        for (char r : R) {
            map.put(r, i);
            i++;
        }
    }

    @Test
    void getTest() {
        init();
        int i = 0;
        for (char r : R) {
            Assert.assertEquals(map.get(r), i++);
        }
    }

    @Test
    void removeTest() throws KeyDoesNotExistException {
        init();
        for (char r : R) {
            map.remove(r);
            Assert.assertEquals(map.get(r), -1);
        }
    }
}