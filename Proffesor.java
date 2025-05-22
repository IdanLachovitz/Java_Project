package Idan_Lachovitz_Idan_Pekler_Part2;

public class Proffesor extends Doctor{

    private String professorship;

    public Proffesor(String name, String id, eDegree degree, int salary, String nameOfDegree, String[] articles, String professorshipName) {
        super(name, id, degree, salary, nameOfDegree, articles);
        this.professorship = professorshipName;
    }

}
