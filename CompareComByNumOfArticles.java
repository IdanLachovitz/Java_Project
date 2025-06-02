package Idan_Lachovitz_Idan_Pekler_Part2;

import java.util.Comparator;

public class CompareComByNumOfArticles implements Comparator<Committees> {
    @Override
    public int compare(Committees o1, Committees o2) {
        return Integer.compare(Util.getNumOfArticles(o2), Util.getNumOfArticles(o1));
    }
}
