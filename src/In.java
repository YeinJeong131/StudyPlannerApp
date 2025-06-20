import java.util.*;

public class In {
    private static Scanner in = new Scanner(System.in);

    public static String nextLine() {
        return in.nextLine();
    }

    public static char nextChar() {
        return in.nextLine().charAt(0);
    }

    public static char nextUpperChar() { 
        return in.nextLine().toUpperCase().charAt(0);
    }

    public static int nextInt() {
        int i = in.nextInt();
        in.nextLine();
        return i;
    }

    public static double nextDouble() {
        double d = in.nextDouble();
        in.nextLine();
        return d;
    }

    public static Priority getPriorityfromString() {
        String input = in.nextLine().trim().toUpperCase();
        switch (input) {
            case "HIGH": return Priority.HIGH;
            case "MEDIUM": return Priority.MEDIUM;
            case "LOW": return Priority.LOW;
            default: throw new IllegalArgumentException("Invalid priority: " + input);
        }
    }
    public static Status getStatusfromString() {
        String input = in.nextLine().trim().toUpperCase();
        switch (input) {
            case "NOT_STARTED": return Status.NOT_STARTED;
            case "IN_PROGRESS": return Status.IN_PROGRESS;
            case "COMPLETED": return Status.COMPLETED;
            default: throw new IllegalArgumentException("Invalid status: " + input);
        }
    }
    public static MaterialType getMaterialTypefromString() {
        String input = in.nextLine().trim().toUpperCase();
        switch (input) {
            case "PPT": return MaterialType.PPT;
            case "VIDEO": return MaterialType.VIDEO;
            case "DOCUMENT": return MaterialType.DOCUMENT;
            default: throw new IllegalArgumentException("Invalid material type: " + input);
        }
    }
    public static QuestionType getQuestionTypefromString() {
        String input = in.nextLine().trim().toUpperCase();
        switch (input) {
            case "MULTIPLE_CHOICE": return QuestionType.MULTIPLE_CHOICE;
            case "SHORT_ANSWER": return QuestionType.SHORT_ANSWER;
            case "MIXED": return QuestionType.MIXED;
            default: throw new IllegalArgumentException("Invalid question type: " + input);
        }
    }

}