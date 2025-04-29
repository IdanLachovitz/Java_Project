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

    public static boolean isDocOrPro(Lecturer lecturer){
        if (lecturer.getDegree().equals(Lecturer.eDegree.DOCTOR) || lecturer.getDegree().equals(Lecturer.eDegree.PROFESSOR)){
            return true;
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

    public static String printArrayNames(Object[] arr){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length-1 && arr[i] != null){
                sb.append(", ");
            } else {
                break;
            }
        }
        sb.append("]");
        System.out.println(sb);
        return sb.toString();
    }

    public static String printNames(Committees[] arr, int size, Lecturer[] l1, int lectSize){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("The name of committee:" + arr[i].getNameofCommittees());
            sb.append(", The chairman is: " + arr[i].getChairman().getName());
            sb.append(", The Lecturers are: [");
            for (int j = 0; j < lectSize; j++) {
                System.out.println(l1[j].getName());
                if (j == 0){
                    sb.append(l1[j].getName());
                }else {
                    sb.append(", " + l1[j].getName());
                }
            }
            sb.append("]");
        }
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
        sb.append(deptName + " lecturers are: [");
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
        sb.append("Departments: ");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i].getName());
            if (i < size-1 && arr[i] != null){
                sb.append(", ");
            } else {
                break;
            }
        }
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
}
