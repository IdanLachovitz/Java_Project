package Idan_Lachovitz_Idan_Pekler_Part2;

public class CollegeExceptions extends Exception{
    private static final String PRE_MESSAGE = "Error: ";

    public CollegeExceptions(String message) {
        super(PRE_MESSAGE+message);
    }

}