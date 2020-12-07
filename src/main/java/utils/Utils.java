package utils;

import models.Constant;

/**
 * TP n°: TP6 V1
 *
 * Titre du TP : Hash Probing II
 *
 * Date : 07/12/2020
 *
 * Nom  : MA
 * Prenom : Quentin
 *
 * email : quentin.ma@etu-u.paris.fr
 *
 * Remarques : This class implement all the methods and functions that
 * are necessary for our models
 */
public class Utils {

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        else if (n <= 3) return true;
        else if (n % 2 == 0 || n % 3 == 0) return false;
        int i = 5;
        while ((i * i) <= n) {
            if (n % i == 0) {
                return  false;
            }
            i += 6;
        }
        return true;
    }

    public static int getCeilPrimerNumber(int m) {
        while (!isPrime(m)) {
            m++;
        }
        return m;
    }

    public static int getSmallestPrimeNumber(int m) {
        while (m != 2) {
            if (isPrime(m)) {
                return m;
            }
            m--;
        }
        return -1;
    }

    public static int getRandomPrimeNumber() {
        boolean isPrime;
        int MIN = 0;
        int r;
        do {
            r = (int) (Math.random() * (Constant.M - MIN) + MIN);
            isPrime = isPrime(r);
        } while (!isPrime);
        return r;
    }

}
