package Idan_Lachovitz_Idan_Pekler_Part2;

public class Professor extends Doctor implements Comparable<Doctor>{

    private String professorship;

    public Professor(String name, String id, eDegree degree, int salary, String nameOfDegree, String[] articles, String professorshipName) {
        super(name, id, degree, salary, nameOfDegree, articles);
        this.professorship = professorshipName;
    }

    @Override
    public int compareTo(Doctor o) {
        return super.compareTo(o);
    }
}
