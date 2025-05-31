package Idan_Lachovitz_Idan_Pekler_Part2;

import java.util.Arrays;
import java.util.Scanner;

public class College {

    private Scanner s = new Scanner(System.in);
    private String collegeName;
    private Lecturer[] lecturer;
    private int numOfLecturer;
    private Committees[] committees;
    private int numOfCommittee;
    private Department[] departments;
    private int numOfDepartment;

    public College(String collegeName) {
        this.collegeName = collegeName;
        lecturer = new Lecturer[0];
        committees = new Committees[0];
        departments = new Department[0];
    }

    public void addLecturer(String name, String id, Lecturer.eDegree degree, int salary, String nameOfDegree) throws LecturerException{
        if (Util.isExist(name, lecturer, numOfLecturer)) {
            throw new LecturerException(eStatus.LECTURER_EXISTS.toString());
        }
        if (numOfLecturer == lecturer.length) {
            lecturer = Arrays.copyOf(lecturer, numOfLecturer == 0 ? 2 : numOfLecturer * 2);
        }
        lecturer[numOfLecturer++] = new Lecturer(name, id, degree, salary, nameOfDegree);
    }

    public void addLecturer(String name, String id, Lecturer.eDegree degree, int salary, String nameOfDegree, String[] articles) throws LecturerException{
        if (Util.isExist(name, lecturer, numOfLecturer)) {
            throw new LecturerException(eStatus.LECTURER_EXISTS.toString());
        }
        if (numOfLecturer == lecturer.length) {
            lecturer = Arrays.copyOf(lecturer, numOfLecturer == 0 ? 2 : numOfLecturer * 2);
        }
        lecturer[numOfLecturer++] = new Doctor(name, id, degree, salary, nameOfDegree, articles);
    }

    public void addLecturer(String name, String id, Lecturer.eDegree degree, int salary, String nameOfDegree, String[] articles, String professorshipName) throws LecturerException{
        if (Util.isExist(name, lecturer, numOfLecturer)) {
            throw new LecturerException(eStatus.LECTURER_EXISTS.toString());
        }
        if (numOfLecturer == lecturer.length) {
            lecturer = Arrays.copyOf(lecturer, numOfLecturer == 0 ? 2 : numOfLecturer * 2);
        }
        lecturer[numOfLecturer++] = new Professor(name, id, degree, salary, nameOfDegree, articles, professorshipName);
    }


    public void addCommittee(String committeeName, String chairman) throws CommitteeException, LecturerException{
        if (Util.getLecturerFromName(chairman, lecturer) == null){
            throw new LecturerException(eStatus.LECTURER_DONT_EXIST.toString());
        }
        if (Util.isExist(committeeName, committees, numOfCommittee)) {
            throw new CommitteeException(eStatus.COMMITTEE_EXIST.toString());
        }
        if (Util.getLecturerFromName(chairman, lecturer) instanceof Doctor || Util.getLecturerFromName(chairman, lecturer) instanceof Professor) {
            if (numOfCommittee == committees.length) {
                committees = Arrays.copyOf(committees, numOfCommittee == 0 ? 2 : numOfCommittee * 2);
            }
            committees[numOfCommittee++] = new Committees(committeeName, Util.getLecturerFromName(chairman, lecturer));

        } else{
            throw new LecturerException(eStatus.LECTURER_NOT_DOC_OR_PRO.toString());
        }
    }


    public void addLecturerToCommittee(String committeeName, String name) throws CommitteeException, LecturerException{
        if (!Util.isExist(committeeName, committees, numOfCommittee)) {
            throw new CommitteeException(eStatus.COMMITTEE_DONT_EXIST.toString());
        }else if (!Util.isExist(name,lecturer,numOfLecturer)) {
            throw new LecturerException(eStatus.LECTURER_DONT_EXIST.toString());
        }else if (Util.isExist(name, Util.getCommitteeFromName(committeeName, committees).getLecturers(), Util.getCommitteeFromName(committeeName, committees).getNumofLecturer())) {
            throw new CommitteeException(eStatus.LECTURER_EXISTS_IN_COMMITTEE.toString());
        }else if (Util.getCommitteeFromName(committeeName,committees).getChairman().getName().equals(name)){
            throw new CommitteeException(eStatus.LECTURER_IS_THE_CHAIRMAN.toString());
        } else {
            Util.getCommitteeFromName(committeeName, committees).addLecturerToCommittee(Util.getLecturerFromName(name, lecturer));
        }
    }


    public void updateChairman(String committeeName, String newChairMan) throws CommitteeException, LecturerException{
        if (!Util.isExist(newChairMan, lecturer, numOfLecturer)) {
            throw new LecturerException(eStatus.LECTURER_DONT_EXIST.toString());
        } else {
            if (!(Util.getLecturerFromName(newChairMan, lecturer) instanceof Doctor || Util.getLecturerFromName(newChairMan, lecturer) instanceof Professor)){
                throw new CommitteeException(eStatus.LECTURER_NOT_DOC_OR_PRO.toString());
            }else{
                if (Util.getCommitteeFromName(committeeName, committees) != null) {
                    Util.getCommitteeFromName(committeeName, committees).setChairman(Util.getLecturerFromName(newChairMan, lecturer));
                    if (Util.isExist(newChairMan, Util.getCommitteeFromName(committeeName, committees).getLecturers(), Util.getCommitteeFromName(committeeName, committees).getNumofLecturer())){
                        removeFromCommittee(newChairMan, committeeName);
                    }
                } else {
                    throw new CommitteeException(eStatus.COMMITTEE_DONT_EXIST.toString());
                }
            }
        }
    }


    public void removeFromCommittee(String name, String committeeName) throws CommitteeException, LecturerException{
        if (!Util.isExist(name, lecturer, numOfLecturer)) {
            throw new LecturerException(eStatus.LECTURER_DONT_EXIST.toString());
        } else if (!Util.isExist(committeeName,committees,numOfCommittee)){
            throw new CommitteeException(eStatus.COMMITTEE_DONT_EXIST.toString());
        }
        if (Util.isExist(name, Util.getCommitteeFromName(committeeName, committees).getLecturers(), Util.getCommitteeFromName(committeeName, committees).getNumofLecturer())){
            Util.getCommitteeFromName(committeeName, committees).removeLecturerByName(name);
        } else{
            throw new CommitteeException(eStatus.LECTURER_NOT_EXISTS_IN_COMMITTEE.toString());
        }
    }


    public void addDepartment(String depName) throws DepartmentException{
        if (Util.isExist(depName, departments, numOfDepartment)) {
            throw new DepartmentException(eStatus.DEPARTMENT_EXIST.toString());
        } else {
            if (numOfDepartment == departments.length) {
                departments = Arrays.copyOf(departments, numOfDepartment == 0 ? 2 : numOfDepartment * 2);
            }
            departments[numOfDepartment++] = new Department(depName);
        }
    }


    public void addLecturerToDepartment(String lectName, String deptName) throws LecturerException, DepartmentException{
        if (!Util.isExist(lectName, lecturer, numOfLecturer)) {
            throw new LecturerException(eStatus.LECTURER_DONT_EXIST.toString());
        } else if (!Util.isExist(deptName, departments, numOfDepartment)) {
            throw new DepartmentException(eStatus.DEPARTMENT_DONT_EXIST.toString());
        } else if (Util.isExist(lectName, Util.getDepartmentFromName(deptName,departments).getArrLecturer(), Util.getDepartmentFromName(deptName,departments).getNumOfLecturer())) {
            throw new DepartmentException(eStatus.LECTURER_EXISTS_IN_DEPARTMENT.toString());
        }else {
            Util.getDepartmentFromName(deptName,departments).setArrLecturer(Util.getLecturerFromName(lectName, lecturer));
            Util.getLecturerFromName(lectName,lecturer).setDepartment(deptName);
        }
    }


    public float showAverageProfessorsSalary() throws ArithmeticException{
        float sum = 0;
        if (numOfLecturer != 0){
            for (int i = 0; i < numOfLecturer; i++) {
                sum += lecturer[i].getSalary();
            }
            return sum/numOfLecturer;
        } else {
            throw new ArithmeticException("Error: Arithmetic Exception");
        }
    }


    public float showAverageSalaryFromSpecificDepartment(String deptName) throws DepartmentException{
        float sum = 0;
        if (!Util.isExist(deptName, departments, numOfDepartment)){
            throw new DepartmentException(eStatus.DEPARTMENT_DONT_EXIST.toString().toString());
        } else if (Util.getDepartmentFromName(deptName, departments).getNumOfLecturer() == 0){
            throw new DepartmentException(eStatus.LECTURER_NOT_EXISTS_IN_DEPARTMENT.toString());
        }
        for (int i = 0; i < Util.getDepartmentFromName(deptName,departments).getNumOfLecturer(); i++) {
            sum += Util.getDepartmentFromName(deptName,departments).getArrLecturer()[i].getSalary();
        }
        return sum/Util.getDepartmentFromName(deptName,departments).getNumOfLecturer();
    }


    public void doctorsCompare() {
        Doctor[] d = new Doctor[numOfLecturer];
        int numOfDoc = 0;
        for (int i = 0; i < numOfLecturer; i++) {
            if (lecturer[i] instanceof Doctor doc){
                d[numOfDoc++] = doc;
            }
        }
        Arrays.sort(d);
        for (int i = 0; i < numOfDoc; i++) {
            System.out.println(d[i]);
        }
    }


    public void professorsCompare() {
        Professor[] p = new Professor[numOfLecturer];
        int numOfProf = 0;
        for (int i = 0; i < numOfLecturer; i++) {
            if (lecturer[i] instanceof Professor prof){
                p[numOfProf++] = prof;
            }
        }
        Arrays.sort(p);
        for (int i = 0; i < numOfProf; i++) {
            System.out.println(p[i]);
        }
    }


    public void copyCommittee(Committees c) throws CommitteeException{
        if (!Util.isExist(c.getNameofCommittees(), committees, numOfCommittee)) {
            throw new CommitteeException(eStatus.COMMITTEE_DONT_EXIST.toString());
        }
        if (numOfCommittee == committees.length) {
            committees = Arrays.copyOf(committees, numOfCommittee == 0 ? 2 : numOfCommittee * 2);
        }
        try{
            Committees newCommittee = c.clone();
            newCommittee.setNameofCommittees(newCommittee.getNameofCommittees() + "-new");
            committees[numOfCommittee++] = newCommittee;
        } catch (CloneNotSupportedException e){
            System.out.println(e.getMessage());
            throw new CommitteeException(eStatus.COMMITTEE_DONT_EXIST.toString());
        }

    }


    public String getCollegeName() {
        return collegeName;
    }


    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }


    public Lecturer[] getLecturer() {
        return lecturer;
    }


    public void setLecturer(Lecturer[] lecturer) {
        this.lecturer = lecturer;
    }


    public int getNumOfLecturer() {
        return numOfLecturer;
    }


    public void setNumOfLecturer(int numOfLecturer) {
        this.numOfLecturer = numOfLecturer;
    }


    public Committees[] getCommittees() {
        return committees;
    }


    public void setCommittees(Committees[] committees) {
        this.committees = committees;
    }


    public int getNumOfCommittee() {
        return numOfCommittee;
    }


    public void setNumOfCommittee(int numOfCommittee) {
        this.numOfCommittee = numOfCommittee;
    }


    public Department[] getDepartments() {
        return departments;
    }


    public void setDepartments(Department[] departments) {
        this.departments = departments;
    }


    public int getNumOfDepartment() {
        return numOfDepartment;
    }


    public void setNumOfDepartment(int numOfDepartment) {
        this.numOfDepartment = numOfDepartment;
    }


    private static String str(Object[] arr, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]);
            if (i < size-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


}
