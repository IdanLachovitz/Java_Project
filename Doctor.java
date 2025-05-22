package Idan_Lachovitz_Idan_Pekler_Part2;

public class Doctor extends Lecturer{

    private String[] articles;
    private int numOfArticles;

    public Doctor(String name, String id, eDegree degree, int salary, String nameOfDegree, String[] articles) {
        super(name, id, degree, salary, nameOfDegree);
        this.articles = articles;
    }
}
