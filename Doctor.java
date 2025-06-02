package Idan_Lachovitz_Idan_Pekler_Part2;

import java.util.Arrays;

public class Doctor extends Lecturer implements Comparable<Doctor>{

    protected String[] articles;
    protected int numOfArticles;

    public Doctor(String name, String id, eDegree degree, int salary, String nameOfDegree, String[] articles) {
        super(name, id, degree, salary, nameOfDegree);
        this.articles = articles;
        this.numOfArticles = articles.length;
    }

    public int getNumOfArticles() {
        return numOfArticles;
    }

    public String[] getArticles() {
        return articles;
    }

    @Override
    public int compareTo(Doctor o) {
        return Integer.compare(o.numOfArticles, this.numOfArticles);
    }


    @Override
    public String toString() {
        return "Name: " + super.getName() +
                ", articles=" + Arrays.toString(articles) +
                ", numOfArticles=" + numOfArticles;
    }

}
