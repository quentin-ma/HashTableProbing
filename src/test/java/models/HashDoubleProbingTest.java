package models;

import exceptions.KeyDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void test() {
        int i = 0;
        for (char r : R) {
            map.put(r, i++);
        }
        System.out.println(Arrays.toString(map.keys()));
        System.out.println(Arrays.toString(map.values()));
    }

    @Test
    void getTest() {
        init();

    }

    @Test
    void removeTest() throws KeyDoesNotExistException {
        init();
        System.out.println(Arrays.toString(map.keys()));
        System.out.println(Arrays.toString(map.values()));
        map.remove('O');
        System.out.println(Arrays.toString(map.keys()));
        System.out.println(Arrays.toString(map.values()));
    }
}