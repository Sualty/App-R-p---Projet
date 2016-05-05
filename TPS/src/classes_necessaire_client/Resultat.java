package classes_necessaire_client;

import java.io.Serializable;

/**
 * Created by sualty on 04/04/16.
 */
public class Resultat implements Serializable{
    public transient int i;
    public int k;
    public Resultat() {
        System.out.println("classe resultat "+i+" "+k);
    }

    public String toString() {
        return "coucou ça marche";
    }
}