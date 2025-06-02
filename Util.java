package Idan_Lachovitz_Idan_Pekler_Part2;

public class Util {
    public static boolean isExist(String name, Lecturer[] arr, int logicSize){
        for (int i = 0; i < logicSize; i++) {
            if ((arr[i]).getName().equals(name)){
                return true;
            }
        }
        return false;
    }


    public static boolean isExist(String name, Department[] arr, int logicSize){
        for (int i = 0; i < logicSize; i++) {
            if ((arr[i]).getName().equals(name)){
                return true;
            }
        }
        return false;
    }


    public static boolean isExist(String name, Committees[] arr, int logicSize){
        for (int i = 0; i < logicSize; i++) {
            if ((arr[i]).getNameofCommittees().equals(name)){
                return true;
            }
        }
        return false;
    }


    public static Lecturer getLecturerFromName(String name, Lecturer[] lecturers){
        for (Lecturer lecturer : lecturers){
            if (lecturer.getName().equals(name)){
                return lecturer;
            }
        }
        return null;
    }


    public static Department getDepartmentFromName(String name, Department[] departments){
        for (Department department : departments){
            if (department.getName().equals(name)){
                return department;
            }
        }
        return null;
    }


    public static String printNames(Committees[] arr, int size){
        StringBuilder sb = new StringBuilder();
        sb.append("Committees: [");
        for (int i = 0; i < size; i++) {
            if (i == 0) sb.append(arr[i].getNameofCommittees());
            else sb.append(", ").append(arr[i].getNameofCommittees());
        }
        sb.append("]");
        return sb.toString();
    }


    public static String printNames(Committees c1, Lecturer[] l1, int lectSize){
        StringBuilder sb = new StringBuilder();
        sb.append("Committee name: ").append(c1.getNameofCommittees());
        sb.append(" , The chairman: ").append(c1.getChairman().getName());
        sb.append(" , Lecturers list: [");
        for (int j = 0; j < lectSize; j++) {
            if (j == 0){
                sb.append(l1[j].getName());
            }else {
                sb.append(", ").append(l1[j].getName());
            }
        }
        sb.append("] \n");
        return sb.toString();
    }


    public static String printNamesFromCommittee(Lecturer[] l1, int lectSize){
        StringBuilder sb = new StringBuilder();
        sb.append("Committee lecturers list: [");
        for (int j = 0; j < lectSize; j++) {
            if (j == 0){
                sb.append(l1[j].getName());
            }else {
                sb.append(", ").append(l1[j].getName());
            }
        }
        sb.append("] \n");
        return sb.toString();
    }


    public static String printNames(Lecturer[] arr, int size){
        StringBuilder sb = new StringBuilder();
        sb.append("Lecturers: [");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i].getName());
            if (i < size-1 && arr[i] != null){
                sb.append(", ");
            } else {
                break;
            }
        }
        sb.append("]");
        return sb.toString();
    }


    public static String printNames(Lecturer[] arr, int size, String deptName){
        StringBuilder sb = new StringBuilder();
        sb.append(deptName).append(" lecturers are: [");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i].getName());
            if (i < size-1 && arr[i] != null){
                sb.append(", ");
            } else {
                break;
            }
        }
        sb.append("]");
        return sb.toString();
    }


    public static String printNames(Department[] arr, int size){
        StringBuilder sb = new StringBuilder();
        sb.append("Departments: [");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i].getName());
            if (i < size-1 && arr[i] != null){
                sb.append(", ");
            } else {
                break;
            }
        }
        sb.append("]");
        return sb.toString();
    }


    public static Committees getCommitteeFromName(String name, Committees[] committees){
        for (Committees committee : committees){
            if (committee == null){
                return null;
            }
            if (committee.getNameofCommittees().equals(name)){
                return committee;
            }
        }
        return null;
    }


    public static int getNumOfArticles(Committees c){
        int sum = ((Doctor) c.getChairman()).getNumOfArticles();
        for (int i = 0; i < c.getNumofLecturer(); i++) {
            if (c.getLecturers()[i] instanceof Doctor d){
                sum += d.getNumOfArticles();
            }
        }
        return sum;
    }
}


