import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



enum Priority { HIGH, MEDIUM, LOW }
enum Status { NOT_STARTED, IN_PROGRESS, COMPLETED }

abstract class Task {
    protected String title;
    protected String description;
    protected String dueDate;  //yyyy/MM/dd
    protected String dueTime;  // e.g. if current time is 12am -> 00:00:00 -> Hour:Minute:Second
    protected String courseName;
    protected Priority priority;
    protected Status status;

    Task(String title, String description, String dueDate, String dueTime, String courseName, Priority priority, Status status) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.courseName = courseName;
        this.priority = priority;
        this.status = status;
    }
    
    public void completeTask() {
        setStatus(Status.COMPLETED);
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String newDueDate) {
        this.dueDate = newDueDate;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public String toString() {
        return "Title: " + title + " | Description: " + description + " | Due date: " + dueDate + " | Due time: " + dueTime + " | Course name: " + courseName + " | Priority: " + priority + " | Status: " + status;
    }

    public String getDueTime() {
        return dueTime;
    }
    public void setDueTime(String newDueTime) {
        this.dueTime = newDueTime;
    }
    public Priority getPriority() {
        return priority;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String newCourseName) {
        this.courseName = newCourseName;
    }
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

}

class Revision extends Task {
    private MaterialType materialType;
    
    Revision(String title, String description, String dueDate, String dueTime, String courseName, Priority priority, Status status, MaterialType materialType) {
        super(title, description, dueDate, dueTime, courseName, priority, status);
        this.materialType = materialType;
    }

    void setMaterialType(MaterialType newMaterialType) {
        this.materialType = newMaterialType;
    }
    
    boolean isCompletedTask() {
        if (getStatus() == Status.COMPLETED) return true;
        else return false;
    }

    @Override
    public String toString() {
        return super.toString() + " | MaterialType: " + materialType;
    }

}

enum MaterialType { PPT, VIDEO, DOCUMENT }

class RequiredTask extends Task {
    private String availableDate;  //yyyy/MM/dd

    RequiredTask(String title, String description, String dueDate, String dueTime, String availableDate, String courseName, Priority priority, Status status) {
        super(title, description, dueDate, dueTime, courseName, priority, status);
        this.availableDate = availableDate;
    }
    public String getAvailableDate() {
        return availableDate;
    }
    public void setAvailableDate(String newAvailableDate) {
        this.availableDate = newAvailableDate;
    }

    @Override
    public String toString() {
        return super.toString() + " | Available date: " + availableDate; 
    }

    public void handleOverDue() {
        setPriority(Priority.HIGH);
        
    }
    
}


enum QuestionType { MULTIPLE_CHOICE, SHORT_ANSWER, MIXED }

class Quiz extends RequiredTask {
    private String questionCount;
    private QuestionType questionType;
    private String timeLimit; // e.g. if time limit is 12 minute -> 00:12:00

    Quiz(String title, String description, String dueDate, String dueTime, String availableDate, String courseName, Priority priority, Status status, String questionCount, QuestionType questionType, String timeLimit) {
        super(title, description, dueDate, dueTime, availableDate, courseName, priority, status);
        this.questionCount = questionCount;
        this.questionType = questionType;
        this.timeLimit = timeLimit;
    }

    void setQuestionCount(String newQuestionCount) {
        this.questionCount = newQuestionCount;
    }
    void setQuestionType(QuestionType newQuestionType) {
        this.questionType = newQuestionType;
    }
    void setTimeLimit(String newTimeLimit) {
        this.timeLimit = newTimeLimit;
    }

    @Override
    public String toString() {
        return super.toString() + " | Question count: " + questionCount + " | Question type: " + questionType + " | Time limit: " + timeLimit;
    }
}

class Assignment extends RequiredTask {
    private String submissionLink;
    private String maxScore;
    private String groupMembers;

    Assignment(String title, String description, String dueDate, String dueTime, String availableDate, String courseName, Priority priority, Status status, String submissionLink, String maxScore, String groupMembers) {
        super(title, description, dueDate, dueTime, availableDate, courseName, priority, status);
        this.submissionLink = submissionLink;
        this.maxScore = maxScore;
        this.groupMembers = groupMembers;
    }

    void setSubmissionLink(String newSubmissionLink) {
        this.submissionLink = newSubmissionLink;
    }
    void setMaxScore(String newMaxScore) {
        this.maxScore = newMaxScore;
    }
    void setGroupMembers(String newGroupMembers) {
        this.groupMembers = newGroupMembers;
    }

    @Override
    public String toString() {
        return super.toString() + " | Submission link: " + submissionLink + " | Max score: " + maxScore + " | Group members: " + groupMembers;
    }
}

class ListManager {
    List<Integer> taskIndexes;
    String name;
   
    final static Comparator<Task> comparatorImportance = Comparator.comparing(Task::getDueDate).thenComparing(Task::getDueTime).thenComparing(Task::getPriority);

    ListManager(String name, List<Integer> taskIndexes) {
        this.name = name;
        this.taskIndexes = taskIndexes;
   }
    public List<Integer> getTaskIndexes() {
        return this.taskIndexes;
    }

    public String getName() {
        return this.name;
    }

    void addTask(int taskIndex) {
    this.taskIndexes.add(taskIndex);
    }

    void removeTask(int taskIndex) {
        this.taskIndexes.remove(taskIndex);
    }
}
interface MakeList {
    ListManager listQuiz();
    ListManager listAssignments();
    ListManager listRevisions();
    ListManager listTasks();
    //ListManager listTasks(int week);
    ListManager listTasks(Status status);
    ListManager listTasks(String courseName);
    //To do sequence 에 쓸 interface
    void sortByImportance();
}
interface ViewList {
    void displayTaskList(ListManager taskList);
}

public class StudyPlanner implements MakeList, ViewList{
    private List<Task> allTasks;
    //private String currentWeek; //e.g. week1, week2, week3....

    StudyPlanner(Task[] allTasks) {
        this.allTasks = new ArrayList<>(Arrays.asList(allTasks));
    }
    public List<Task> getAllTasks() {
        return this.allTasks;
    }
    public void setAllTasks(List<Task> allTasks) {
        this.allTasks = allTasks;
    }

    @Override
    public ListManager listQuiz() {
        ArrayList<Integer> quizIndexes = new ArrayList<>();

        for (int i = 0; i < this.allTasks.size(); i++) {
            if (this.allTasks.get(i) instanceof Quiz) {
                quizIndexes.add(i);
            }
        }
        return new ListManager("All quiz: ", quizIndexes);
    }
    @Override
    public ListManager listAssignments() {
        ArrayList<Integer> assignmentIndexes = new ArrayList<>();

        for (int i = 0; i < this.allTasks.size(); i++) {
            if (this.allTasks.get(i) instanceof Assignment) {
                assignmentIndexes.add(i);
            }
        }
        return new ListManager("All assignments: ", assignmentIndexes);
    }
    @Override
    public ListManager listRevisions() {
        ArrayList<Integer> revisionIndexes = new ArrayList<>();

        for (int i = 0; i < this.allTasks.size(); i++) {
            if (this.allTasks.get(i) instanceof Revision) {
                revisionIndexes.add(i);
            }
        }
        return new ListManager("All revisions: ", revisionIndexes);
    }

    @Override
    public ListManager listTasks() {
        ArrayList<Integer> allTasksIndexes = new ArrayList<>();
        for (int i = 0; i < this.allTasks.size(); i++) {
            allTasksIndexes.add(i);
        }
        return new ListManager("All tasks", allTasksIndexes);
    }
    @Override
    public ListManager listTasks(Status status) {
        ArrayList<Integer> filterByStatus = new ArrayList<>();
        boolean foundStatus = false;
        for (int i = 0; i < this.allTasks.size(); i++) {
            Task task = this.allTasks.get(i);
            if (task.getStatus().equals(status)) {
                filterByStatus.add(i);
                foundStatus = true;
            }
        }
        if (!foundStatus) System.out.println("Invalid choice");
        return new ListManager("List by particular status", filterByStatus);
    }
    @Override
    public ListManager listTasks(String courseName) {
        ArrayList<Integer> filterByCourse = new ArrayList<>();
        boolean foundCourse = false;
        for (int i = 0; i < this.allTasks.size(); i++) {
            Task task = this.allTasks.get(i);
            if (task.getCourseName().equals(courseName)) {
                filterByCourse.add(i);
                foundCourse = true;
            }
        }
        if (!foundCourse) System.out.println("Invalid choice");

        return new ListManager("List by particular course", filterByCourse);
    }

    @Override
    public void sortByImportance() {
        ArrayList<Task> toDoSequence = new ArrayList<>(this.allTasks);
        Collections.sort(toDoSequence, ListManager.comparatorImportance);
        for (Task task : toDoSequence) {
            if (task instanceof Revision) {
                System.out.println(((Revision)task).toString());
            }
            else if (task instanceof RequiredTask) {
                if (task instanceof Quiz) {
                    System.out.println(((Quiz)task).toString());
                }
                else if (task instanceof Assignment) {
                    System.out.println(((Assignment)task).toString());
                }
            }
        }
    }

    @Override
    public void displayTaskList(ListManager taskList) {
        for (int i = 0; i < taskList.getTaskIndexes().size(); i++) {
            System.out.println(" " + (i+1) + ". " + this.allTasks.get(taskList.getTaskIndexes().get(i)));
        }
    }
 
}
   