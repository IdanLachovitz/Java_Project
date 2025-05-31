package Idan_Lachovitz_Idan_Pekler_Part2;

public class CommitteeException extends CollegeExceptions {

    private static final String PRE_MESSAGE = "type: Committee Exception- ";

    public CommitteeException(String message) {
        super(PRE_MESSAGE+message);
    }

}
