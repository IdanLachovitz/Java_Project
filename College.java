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

    public eStatus addLecturer(String name, String id, Lecturer.eDegree degree, int salary, String nameOfDegree, String[] articles) {
        if (Util.isExist(name, lecturer, numOfLecturer)) {
            return eStatus.LECTURER_EXISTS;
        }
        if (numOfLecturer == lecturer.length) {
            lecturer = Arrays.copyOf(lecturer, numOfLecturer == 0 ? 2 : numOfLecturer * 2);
        }
        lecturer[numOfLecturer++] = new Doctor(name, id, degree, salary, nameOfDegree, articles);
        return eStatus.SUCCESS;
    }

    public eStatus addLecturer(String name, String id, Lecturer.eDegree degree, int salary, String nameOfDegree, String[] articles, String professorshipName) {
        if (Util.isExist(name, lecturer, numOfLecturer)) {
            return eStatus.LECTURER_EXISTS;
        }
        if (numOfLecturer == lecturer.length) {
            lecturer = Arrays.copyOf(lecturer, numOfLecturer == 0 ? 2 : numOfLecturer * 2);
        }
        lecturer[numOfLecturer++] = new Proffesor(name, id, degree, salary, nameOfDegree, articles, professorshipName);
        return eStatus.SUCCESS;
    }


    public eStatus addCommittee(String committeeName, String chairman) {
        if (Util.getLecturerFromName(chairman, lecturer) == null){
            return eStatus.LECTURER_DONT_EXIST;
        }
        if (Util.isExist(committeeName, committees, numOfCommittee)) {
            return eStatus.COMMITTEE_EXIST;
        }
        if (Util.getLecturerFromName(chairman, lecturer) instanceof Doctor || Util.getLecturerFromName(chairman, lecturer) instanceof Proffesor) {
            if (numOfCommittee == committees.length) {
                committees = Arrays.copyOf(committees, numOfCommittee == 0 ? 2 : numOfCommittee * 2);
            }
            committees[numOfCommittee++] = new Committees(committeeName, Util.getLecturerFromName(chairman, lecturer));
            return eStatus.SUCCESS;
        } else{
            return eStatus.LECTURER_NOT_DOC_OR_PRO;
        }
    }


    public eStatus addLecturerToCommittee(String committeeName, String name) {
        if (!Util.isExist(committeeName, committees, numOfCommittee)) {
            return eStatus.COMMITTEE_DONT_EXIST;
        }else if (!Util.isExist(name,lecturer,numOfLecturer)) {
            return eStatus.LECTURER_DONT_EXIST;
        }else if (Util.isExist(name, Util.getCommitteeFromName(committeeName, committees).getLecturers(), Util.getCommitteeFromName(committeeName, committees).getNumofLecturer())) {
            return eStatus.LECTURER_EXISTS_IN_COMMITTEE;
        }else if (Util.getCommitteeFromName(committeeName,committees).getChairman().getName().equals(name)){
            return eStatus.LECTURER_IS_THE_CHAIRMAN;
        } else {
            Util.getCommitteeFromName(committeeName, committees).addLecturerToCommittee(Util.getLecturerFromName(name, lecturer));
            return eStatus.SUCCESS;
        }
    }


    public eStatus updateChairman(String committeeName, String newChairMan) {
        if (!Util.isExist(newChairMan, lecturer, numOfLecturer)) {
            return eStatus.LECTURER_DONT_EXIST;
        } else {
            if (!(Util.getLecturerFromName(newChairMan, lecturer) instanceof Doctor || Util.getLecturerFromName(newChairMan, lecturer) instanceof Proffesor)){
                return eStatus.LECTURER_NOT_DOC_OR_PRO;
            }else{
                if (Util.getCommitteeFromName(committeeName, committees) != null) {
                    Util.getCommitteeFromName(committeeName, committees).setChairman(Util.getLecturerFromName(newChairMan, lecturer));
                    if (Util.isExist(newChairMan, Util.getCommitteeFromName(committeeName, committees).getLecturers(), Util.getCommitteeFromName(committeeName, committees).getNumofLecturer())){
                        removeFromCommittee(newChairMan, committeeName);
                    }
                    return eStatus.SUCCESS;
                }
                return eStatus.COMMITTEE_DONT_EXIST;
            }
        }
    }


    public eStatus removeFromCommittee(String name, String committeeName) {
        if (!Util.isExist(name, lecturer, numOfLecturer)) {
            return eStatus.LECTURER_DONT_EXIST;
        } else if (!Util.isExist(committeeName,committees,numOfCommittee)){
            return eStatus.COMMITTEE_DONT_EXIST;
        }
        if (Util.isExist(name, Util.getCommitteeFromName(committeeName, committees).getLecturers(), Util.getCommitteeFromName(committeeName, committees).getNumofLecturer())){
            Util.getCommitteeFromName(committeeName, committees).removeLecturerByName(name);
            return eStatus.SUCCESS;
        }
        return eStatus.LECTURER_NOT_EXISTS_IN_COMMITTEE;
    }


    public eStatus addDepartment(String depName) {
        if (Util.isExist(depName, departments, numOfDepartment)) {
            return eStatus.DEPARTMENT_EXIST;
        } else {
            if (numOfDepartment == departments.length) {
                departments = Arrays.copyOf(departments, numOfDepartment == 0 ? 2 : numOfDepartment * 2);
            }
            departments[numOfDepartment++] = new Department(depName);
            return eStatus.SUCCESS;
        }
    }


    public eStatus addLecturerToDepartment(String lectName, String deptName) {
        if (!Util.isExist(lectName, lecturer, numOfLecturer)) {
            return eStatus.LECTURER_DONT_EXIST;
        } else if (!Util.isExist(deptName, departments, numOfDepartment)) {
            return eStatus.DEPARTMENT_DONT_EXIST;
        } else if (Util.isExist(lectName, Util.getDepartmentFromName(deptName,departments).getArrLecturer(), Util.getDepartmentFromName(deptName,departments).getNumOfLecturer())) {
            return eStatus.LECTURER_EXISTS_IN_DEPARTMENT;
        }else {
            Util.getDepartmentFromName(deptName,departments).setArrLecturer(Util.getLecturerFromName(lectName, lecturer));
            Util.getLecturerFromName(lectName,lecturer).setDepartment(deptName);
            return eStatus.SUCCESS;
        }
    }


    public float showAverageProfessorsSalary() {
        float sum = 0;
        if (numOfLecturer != 0){
            for (int i = 0; i < numOfLecturer; i++) {
                sum += lecturer[i].getSalary();
            }
            return sum/numOfLecturer;
        } else {
            return -1;
        }
    }


    public float showAverageSalaryFromSpecificDepartment(String deptName) {
        float sum = 0;
        if (!Util.isExist(deptName, departments, numOfDepartment)){
            return -1;
        } else if (Util.getDepartmentFromName(deptName, departments).getNumOfLecturer() == 0){
            return -2;
        }
        for (int i = 0; i < Util.getDepartmentFromName(deptName,departments).getNumOfLecturer(); i++) {
            sum += Util.getDepartmentFromName(deptName,departments).getArrLecturer()[i].getSalary();
        }
        return sum/Util.getDepartmentFromName(deptName,departments).getNumOfLecturer();
    }


    public eStatus copyCommittee(Committees c) {
        if (!Util.isExist(c.getNameofCommittees(), committees, numOfCommittee)) {
            return eStatus.COMMITTEE_DONT_EXIST;
        }
        if (numOfCommittee == committees.length) {
            committees = Arrays.copyOf(committees, numOfCommittee == 0 ? 2 : numOfCommittee * 2);
        }
        committees[numOfCommittee++] = new Committees(c);
        return eStatus.SUCCESS;
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
