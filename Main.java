package Idan_Lachovitz_Idan_Pekler_Part2;

import java.util.Arrays;
import java.util.Scanner;
public class Main {
    // idan lachovitz, 322894148 + idan pekler, 207715848
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter college name: ");
        String name = s.nextLine();
        College c = new College(name);
        run(c);
    }

    private static final String[] MENU = {
            "Exit Program",
            "Add Lecturer",
            "Add Committee",
            "add Lecturer To Committee",
            "update Chairman",
            "remove From Committee",
            "add Department",
            "add Lecturer To Department",
            "show Average Lecturers Salary",
            "show Average Salary FromSpecific Department",
            "show Lecturers Info",
            "show Committee Info"

    };

    public static void run(College college) {
        int userChosen;
        do {
            userChosen = showMenu(s);
            switch (userChosen) {
                case 0 -> System.out.println("Done... Bye");
                case 1 -> addLecturer(college);
                case 2 -> addCommittee(college);
                case 3 -> addLecturerToCommittee(college);
                case 4 -> updateChairman(college);
                case 5 -> removeFromCommittee(college);
                case 6 -> addDepartment(college);
                case 7 -> addLecturerToDepartment(college);
                case 8 -> showAverageProfessorsSalary(college);
                case 9 -> showAverageSalaryFromSpecificDepartment(college);
                case 10 -> showInfo(college.getLecturer(), college.getNumOfLecturer());
                case 11 -> showInfo(college.getCommittees(), college.getNumOfCommittee());

                default -> System.out.println("Unexpected value");
            }
        } while (userChosen != 0);
    }

    private static void addLecturer(College college) {
        s.nextLine();
        while (true) {
            System.out.println("Enter lecturer name: (press 0 to return to the menu) ");
            String name = s.nextLine();
            if (name.equals("0")){
                break;
            } else {
                System.out.println("Enter lecturer id: ");
                String id = s.nextLine();
                System.out.println("Enter your degree (the default is 1): \n1- BSc\n2- MSc\n3- doctor\n4- professor");
                int degree = s.nextInt();
                Lecturer.eDegree deg;
                switch (degree){
                    case 1 -> deg = Lecturer.eDegree.BSc;
                    case 2 -> deg = Lecturer.eDegree.MSc;
                    case 3 -> deg = Lecturer.eDegree.DOCTOR;
                    case 4 -> deg = Lecturer.eDegree.PROFESSOR;
                    default -> deg = Lecturer.eDegree.BSc; //default for invalid is first degree
                }
                s.nextLine();
                System.out.println("Enter name of the degree: ");
                String nameOfDegree = s.nextLine();
                System.out.println("Enter your salary: ");
                int salary = s.nextInt();
                s.nextLine();
                while (salary < 0){
                    System.out.println("Salary cant be negative");
                    System.out.println("Enter your salary: ");
                    salary = s.nextInt();
                    s.nextLine();
                }
                eStatus stat = college.addLecturer(name, id, deg, salary, nameOfDegree);
                switch (stat){
                    case SUCCESS -> System.out.println(eStatus.SUCCESS);
                    case LECTURER_EXISTS -> System.out.println(eStatus.LECTURER_EXISTS);
                }
                if (stat == eStatus.SUCCESS){
                    System.out.println(Util.printNames(college.getLecturer(), college.getNumOfLecturer()));
                    break;
                }
            }
        }
    }


    private static void addCommittee(College college) {
        s.nextLine();
        System.out.println("Enter committee name: ");
        String committeeName = s.nextLine();
        System.out.println("Enter chairhead name: ");
        String chairman = s.nextLine();
        eStatus stat = college.addCommittee(committeeName, chairman);
        switch (stat){
            case SUCCESS -> System.out.println(eStatus.SUCCESS);
            case COMMITTEE_EXIST -> System.out.println(eStatus.COMMITTEE_EXIST);
            case LECTURER_NOT_DOC_OR_PRO -> System.out.println(eStatus.LECTURER_NOT_DOC_OR_PRO);
        }
        if (stat == eStatus.SUCCESS){
            System.out.println(Util.printNames(college.getCommittees(), college.getNumOfCommittee(), Util.getCommitteeFromName(committeeName, college.getCommittees()).getLecturers(), Util.getCommitteeFromName(committeeName, college.getCommittees()).getNumofLecturer()));
        }
    }


    private static void addLecturerToCommittee(College college) {
        s.nextLine();
        System.out.println("Enter committee name: ");
        String committeeName = s.nextLine();
        System.out.println("Enter lecturer name: ");
        String name = s.nextLine();
        eStatus stat = college.addLecturerToCommittee(committeeName, name);
        switch (stat){
            case SUCCESS -> System.out.println(eStatus.SUCCESS);
            case COMMITTEE_DONT_EXIST -> System.out.println(eStatus.COMMITTEE_DONT_EXIST);
            case LECTURER_DONT_EXIST -> System.out.println(eStatus.LECTURER_DONT_EXIST);
            case LECTURER_EXISTS -> System.out.println(eStatus.LECTURER_EXISTS_IN_COMMITTEE);
        }
        if (stat == eStatus.SUCCESS){
            System.out.println(Util.printNames(college.getCommittees(), college.getNumOfCommittee(), Util.getCommitteeFromName(committeeName, college.getCommittees()).getLecturers(), Util.getCommitteeFromName(committeeName, college.getCommittees()).getNumofLecturer()));
        }
    }


    private static void updateChairman(College college){
        s.nextLine();
        System.out.println("What Committee: ");
        String committeeName = s.nextLine();
        System.out.println("enter new head of committee: ");
        String newChairMan = s.nextLine();
        eStatus stat = college.updateChairman(committeeName, newChairMan);
        if (Util.getCommitteeFromName(committeeName,college.getCommittees()) == null){
            stat = eStatus.COMMITTEE_DONT_EXIST;
        }
        switch (stat){
            case SUCCESS -> System.out.println(eStatus.SUCCESS);
            case LECTURER_NOT_DOC_OR_PRO -> System.out.println(eStatus.LECTURER_NOT_DOC_OR_PRO);
            case LECTURER_DONT_EXIST -> System.out.println(eStatus.LECTURER_DONT_EXIST);
            case COMMITTEE_DONT_EXIST -> System.out.println(eStatus.COMMITTEE_DONT_EXIST);
            case LECTURER_IS_THE_CHAIRMAN -> System.out.println(eStatus.LECTURER_IS_THE_CHAIRMAN);
        }
        if (stat == eStatus.SUCCESS){
            System.out.println(Util.printNames(college.getCommittees(), college.getNumOfCommittee(), Util.getCommitteeFromName(committeeName,college.getCommittees()).getLecturers(), Util.getCommitteeFromName(committeeName,college.getCommittees()).getNumofLecturer()));
        }
    }


    private static void removeFromCommittee(College college) {
        s.nextLine();
        System.out.println("Enter committee name: ");
        String committeeName = s.nextLine();
        System.out.println("Enter lecturer name: ");
        String name = s.nextLine();
        eStatus stat = college.removeFromCommittee(name, committeeName);
        switch (stat){
            case SUCCESS -> System.out.println(eStatus.SUCCESS);
            case LECTURER_DONT_EXIST -> System.out.println(eStatus.LECTURER_DONT_EXIST);
            case COMMITTEE_DONT_EXIST -> System.out.println(eStatus.COMMITTEE_DONT_EXIST);
            case LECTURER_NOT_EXISTS_IN_COMMITTEE -> System.out.println(eStatus.LECTURER_NOT_EXISTS_IN_COMMITTEE);
        }
        if (stat == eStatus.SUCCESS){
            System.out.println(Util.printNames(college.getCommittees(), college.getNumOfCommittee(), Util.getCommitteeFromName(committeeName,college.getCommittees()).getLecturers(), Util.getCommitteeFromName(committeeName,college.getCommittees()).getNumofLecturer()));
        }
    }


    private static void addDepartment(College college) {
        s.nextLine();
        System.out.println("Enter department name: ");
        String depName = s.nextLine();
        eStatus stat = college.addDepartment(depName);
        switch (stat){
            case SUCCESS -> System.out.println(eStatus.SUCCESS);
            case DEPARTMENT_EXIST -> System.out.println(eStatus.DEPARTMENT_EXIST);
        }
        if (stat == eStatus.SUCCESS){
            System.out.println(Util.printNames(college.getDepartments(), college.getNumOfDepartment()));
        }
    }


    private static void addLecturerToDepartment(College college) {
        s.nextLine();
        System.out.println("Enter department name: ");
        String deptName = s.nextLine();
        System.out.println("Enter lecturer name: ");
        String lectName = s.nextLine();
        eStatus stat = college.addLecturerToDepartment(lectName,deptName);
        switch (stat) {
            case SUCCESS -> System.out.println(eStatus.SUCCESS);
            case LECTURER_DONT_EXIST -> System.out.println(eStatus.LECTURER_DONT_EXIST);
            case DEPARTMENT_DONT_EXIST -> System.out.println(eStatus.DEPARTMENT_DONT_EXIST);
            case LECTURER_EXISTS_IN_DEPARTMENT -> System.out.println(eStatus.LECTURER_EXISTS_IN_DEPARTMENT);
        }
        if (stat == eStatus.SUCCESS){
            System.out.println(Util.printNames(Util.getDepartmentFromName(deptName, college.getDepartments()).getArrLecturer(), Util.getDepartmentFromName(deptName, college.getDepartments()).getNumOfLecturer(), deptName));
        }
    }


    private static void showAverageProfessorsSalary(College college) {
        float sum = college.showAverageProfessorsSalary();
        if (sum == -1){
            System.out.println(eStatus.GENERAL_ERROR);
        }else {
            System.out.println(eStatus.AVERAGE_SALARY + " " + sum);
        }
    }


    private static void showAverageSalaryFromSpecificDepartment(College college) {
        s.nextLine();
        System.out.println("Enter department name: ");
        String deptName = s.nextLine();
        float sum = college.showAverageSalaryFromSpecificDepartment(deptName);
        if (sum == -1){
            System.out.println(eStatus.DEPARTMENT_DONT_EXIST);
        }else if (sum == -2){
            System.out.println(eStatus.LECTURER_NOT_EXISTS_IN_DEPARTMENT);
        } else {
            System.out.println(eStatus.AVERAGE_SALARY + " " + sum);
        }
    }


    private static void showInfo(Object[] obj, int numOfObj) {
        if (obj == null){
            System.out.println(eStatus.GENERAL_ERROR);
        } else {
            for (int i = 0; i < numOfObj; i++) {
                System.out.println(obj[i]);
            }
        }
    }


    private static int showMenu(Scanner s) {
        System.out.println("\n====== Menu =======");
        for (int i = 0; i < MENU.length; i++) {
            System.out.println(i + ". " + MENU[i]);
        }
        System.out.println("Please enter your choose : ");
        return s.nextInt();
    }
}
