import java.util.List;

public class Menu {
    StudyPlanner studyPlanner;
    Menu() {
        Task[] allTasks = TaskList.ALLTASKS;

        this.studyPlanner = new StudyPlanner(allTasks);
    }
    public static void main(String[] args) { 
        Menu menu = new Menu();
        menu.runMainMenu();
    }

    void runMainMenu() {
        System.out.println(" --------------");
        System.out.println("| STUDY PLANNER |");
        System.out.println(" --------------");

        while (true) {
            System.out.println();
            System.out.println("Select an option: ");
            System.out.println("1. View tasks by list");
            System.out.println("2. Add tasks");
            System.out.println("3. Modify tasks");
            System.out.println("4. Remove tasks");
            System.out.println("5. Exit");
            System.out.print("-> Enter your choice: ");
            int firstChoice = In.nextInt();
            if (firstChoice == 1) {
                viewMenu();
            }
            else if (firstChoice == 2) {
                addMenu();
            }
            else if (firstChoice == 3) {
                modifyMenu();
            }
            else if (firstChoice == 4) {
                removeMenu();
            }
            else if (firstChoice == 5) {
                break;
            }
            else { System.out.println("Pick an option 1, 2, 3, 4 or 5");}
        }
    }

    void viewMenu() {
        while(true) {
            System.out.println();
            System.out.println("a. View all tasks");
            System.out.println("b. View tasks by type"); 
            System.out.println("c. View tasks by particular course");
            System.out.println("d. View tasks by particular status");
            System.out.println("e. View To-Do sequence");
            System.out.println("f. Exit");
            System.out.print("-> Enter your choice: ");
            char secondChoice = In.nextChar();
            if (secondChoice == 'a') {
                ListManager allTasks = this.studyPlanner.listTasks();
                this.studyPlanner.displayTaskList(allTasks);
            }
            else if (secondChoice == 'b') {
                selectType();
            }
            else if (secondChoice == 'c') {
                System.out.println("Enter the course name to display its list: ");
                String courseName = In.nextLine();
                ListManager courseList = this.studyPlanner.listTasks(courseName);
                this.studyPlanner.displayTaskList(courseList);
            }
            else if (secondChoice == 'd') {
                System.out.println("Choose the status to display its list (NOT_STARTED, IN_PROGRESS, COMPLETED): ");
                Status status = In.getStatusfromString();
                ListManager statusList = this.studyPlanner.listTasks(status);
                this.studyPlanner.displayTaskList(statusList);
            }
            else if (secondChoice == 'e') {
                this.studyPlanner.sortByImportance();
            }
            else if (secondChoice == 'f') {
                break;
            }
            else System.out.println("Pick an option a,b,c or d");
        }
    }
    void selectType() {
        while(true) {
            System.out.println();
            System.out.println("i. View quiz");
            System.out.println("ii. View assignments");
            System.out.println("iii. View revisions");
            System.out.println("iv. Exit");
            System.out.print("-> Enter type: ");
            String thirdChoice = In.nextLine();
            if (thirdChoice.equals("i")) {
                ListManager quizList = this.studyPlanner.listQuiz();
                this.studyPlanner.displayTaskList(quizList);
            }
            else if (thirdChoice.equals("ii")) {
                ListManager assignmentList = this.studyPlanner.listAssignments();
                this.studyPlanner.displayTaskList(assignmentList);
            }
            else if (thirdChoice.equals("iii")) {
                ListManager revisionList = this.studyPlanner.listRevisions();
                this.studyPlanner.displayTaskList(revisionList);
            }
            else if (thirdChoice.equals("iv")) {
                break;
            }
            else System.out.println("Pick an option i, ii, iii or iv");
        }
    }

    
    void addMenu() {
        while (true) {
            System.out.println("a. Add quiz");
            System.out.println("b. Add assignment");
            System.out.println("c. Add revision");
            System.out.println("d. Exit");
            System.out.print("-> Enter your choice: ");
            char secondChoice = In.nextChar();
            if (secondChoice == 'a') {
                addQuiz();
            }
            else if (secondChoice == 'b') {
                addAssignment();
            }
            else if (secondChoice == 'c') {
                addRevision();
            }
            else if (secondChoice == 'd') {
                break;
            }
            else System.out.println("Pick an option a,b,c or d");
        }
    }
    void addRevision() {
        System.out.println();
        System.out.println("----------ADD REVISION----------");
        System.out.println();
        while (true) {
            System.out.print("Enter title: ");
            String title = In.nextLine();
            System.out.print("Enter description: ");
            String description = In.nextLine();
            System.out.print("Enter due date (yyyy/MM/dd): ");
            String dueDate = In.nextLine();
            System.out.print("Enter due time (00:00:00): ");
            String dueTime = In.nextLine();
            System.out.print("Enter course name: ");
            String courseName = In.nextLine();
            System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
            Priority priority = In.getPriorityfromString();
            System.out.print("Enter status (NOT_STARTED, IN_PROGRESS, COMPLETED): ");
            Status status = In.getStatusfromString();

            System.out.print("Enter material type (PPT, VIDEO, DOCUMENT): ");
            MaterialType materialType = In.getMaterialTypefromString();
            
            List<Task> allTasks = studyPlanner.getAllTasks();
            allTasks.add(new Revision(title, description, dueDate, dueTime, courseName, priority, status, materialType));
            studyPlanner.setAllTasks(allTasks);

            System.out.println("The new revision is added to Task list.");
            System.out.println("Do you want to continue?(y/n)");
            char ch = In.nextLine().toUpperCase().charAt(0);
            if (ch == 'N')
                break;
        }
    }
    void addQuiz() {
        System.out.println();
        System.out.println("----------ADD QUIZ----------");
        System.out.println();
        while (true) {
            System.out.print("Enter title: ");
            String title = In.nextLine();
            System.out.print("Enter description: ");
            String description = In.nextLine();
            System.out.print("Enter due date (yyyy/MM/dd): ");
            String dueDate = In.nextLine();
            System.out.print("Enter due time (00:00:00): ");
            String dueTime = In.nextLine();
            System.out.print("Enter course name: ");
            String courseName = In.nextLine();
            System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
            Priority priority = In.getPriorityfromString();
            System.out.print("Enter status (NOT_STARTED, IN_PROGRESS, COMPLETED): ");
            Status status = In.getStatusfromString();

            System.out.println("Enter available date (yyyy/MM/dd): ");
            String availableDate = In.nextLine();

            System.out.print("Enter question count: ");
            String questionCount = In.nextLine();
            System.out.print("Enter question type (MULTIPLE_CHOICE, SHORT_ANSWER, MIXED): ");
            QuestionType questionType = In.getQuestionTypefromString();
            System.out.print("Enter time limit (00:00:00): ");
            String timeLimit = In.nextLine();

            
            List<Task> allTasks = studyPlanner.getAllTasks();
            allTasks.add(new Quiz(title, description, dueDate, dueTime, availableDate, courseName, priority, status, questionCount, questionType, timeLimit));
            studyPlanner.setAllTasks(allTasks);

            System.out.println("The new quiz is added to Task list.");
            System.out.println("Do you want to continue?(y/n)");
            char ch = In.nextLine().toUpperCase().charAt(0);
            if (ch == 'N')
                break;
        }
    }
    void addAssignment() {
        System.out.println();
        System.out.println("----------ADD ASSIGNMENT----------");
        System.out.println();
        while (true) {
            System.out.print("Enter title: ");
            String title = In.nextLine();
            System.out.print("Enter description: ");
            String description = In.nextLine();
            System.out.print("Enter due date (yyyy/MM/dd): ");
            String dueDate = In.nextLine();
            System.out.print("Enter due time (00:00:00)");
            String dueTime = In.nextLine();
            System.out.print("Enter course name: ");
            String courseName = In.nextLine();
            System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
            Priority priority = In.getPriorityfromString();
            System.out.print("Enter status (NOT_STARTED, IN_PROGRESS, COMPLETED): ");
            Status status = In.getStatusfromString();

            System.out.print("Enter available date (yyyy/MM/dd): ");
            String availableDate = In.nextLine();

            System.out.print("Enter submission link: ");
            String submissionLink = In.nextLine();
            System.out.print("Enter max score: ");
            String maxScore = In.nextLine();
            System.out.print("Enter group members: ");
            String groupMembers = In.nextLine();

            
            List<Task> allTasks = studyPlanner.getAllTasks();
            allTasks.add(new Assignment(title, description, dueDate, dueTime, availableDate, courseName, priority, status, submissionLink, maxScore, groupMembers));
            studyPlanner.setAllTasks(allTasks);

            System.out.println("The new quiz is added to Task list.");
            System.out.println("Do you want to continue?(y/n)");
            char ch = In.nextLine().toUpperCase().charAt(0);
            if (ch == 'N')
                break;
        }
    }
    void modifyMenu() {
        while(true) {
            System.out.println();
            System.out.println("a. Update task's details by currentDate");
            System.out.println("b. Modify particular task's details");
            System.out.println("c. Exit");
            System.out.print("-> Enter your choice: ");
            char secondChoice = In.nextChar();
            if (secondChoice == 'a') updateDetails();
            else if (secondChoice == 'b') modifyEachTask();
            else if (secondChoice == 'c') break;
            else System.out.println("Pick an option a,b or c");
        }
    }
    void updateDetails() {
        List<Task> allTasks = studyPlanner.getAllTasks();
        System.out.println("Enter Current Date to update: ");
        String currentDate = In.nextLine(); //yyyy/MM/dd
        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            
            if (task instanceof RequiredTask) {
                
                if (currentDate.compareTo(((RequiredTask)task).getAvailableDate()) > 0) {
                    System.out.println("   - Task '" + task.title +"'s available date has already passed. So it is removed");
                    allTasks.remove(i);
                    i--;
                }
                else if (currentDate.compareTo(((RequiredTask)task).getDueDate()) > 0) {
                    ((RequiredTask)task).handleOverDue();
                    System.out.println("   - Task '" + task.title +"'s due date has already passed. So the its priority is now HIGH");
                }
                else System.out.println("   - '" + task.title + "' has no changed");
            }
        }
        System.out.println();
        System.out.println("All the task details are updated according to the current date.");
        System.out.println();
        studyPlanner.setAllTasks(allTasks);
    }

    void modifyEachTask() {
        ListManager viewAllTasks = this.studyPlanner.listTasks();
        this.studyPlanner.displayTaskList(viewAllTasks);
        System.out.print("Enter the number of the task you want to modify: ");
        int number = In.nextInt();
        int indexNum = number - 1;
        List<Task> allTasks = studyPlanner.getAllTasks();
        Task modifyingTask = allTasks.get(indexNum);

        System.out.println("Modifying task: " + modifyingTask);

        System.out.print("Enter new title (or press 'Enter' to keep current): ");
        String newTitle = In.nextLine();
        if (!newTitle.isEmpty()) {
            modifyingTask.setTitle(newTitle);
        }
        System.out.print("Enter new description (or press 'Enter' to keep current): ");
        String newDescription = In.nextLine();
        if (!newDescription.isEmpty()) {
            modifyingTask.setDescription(newDescription);
        }
        System.out.print("Enter new due date (yyyy/MM/dd)(or press 'Enter' to keep current): ");
        String newDueDate = In.nextLine();
        if (!newDueDate.isEmpty()) {
            modifyingTask.setDueDate(newDueDate);
        }
        System.out.print("Enter new due time (00:00:00)(or press 'Enter' to keep current): ");
        String newDueTime = In.nextLine();
        if (!newDueTime.isEmpty()) {
            modifyingTask.setDueDate(newDueTime);
        }
        System.out.print("Enter new course name (or press 'Enter' to keep current): ");
        String newCourseName = In.nextLine();
        if (!newDueTime.isEmpty()) {
            modifyingTask.setDueDate(newCourseName);
        }
        System.out.print("Enter new priority (HIGH, MEDIUM, LOW)(or press 'Enter' to keep current): ");
        try { 
            Priority newPriority = In.getPriorityfromString();
            modifyingTask.setPriority(newPriority);
        }
        catch (IllegalArgumentException e) { }

        System.out.print("Enter new status (NOT_STARTED, IN_PROGRESS, COMPLETED)(or press 'Enter' to keep current): ");
        try {
            Status newStatus = In.getStatusfromString();
            modifyingTask.setStatus(newStatus);
        }
        catch (IllegalArgumentException e){ }

        if (modifyingTask instanceof Quiz) {
            Quiz modifyingQuiz = (Quiz) modifyingTask;

            System.out.print("Enter new question count (or press 'Enter' to keep current): ");
            String newQuestionCount = In.nextLine();
            if (!newQuestionCount.isEmpty()) {
                modifyingQuiz.setQuestionCount(newQuestionCount);
            }
            System.out.print("Enter new question type (MULTIPLE_CHOICE, SHORT_ANSWER, MIXED)(or press 'Enter' to keep current): ");
            try{
                QuestionType newQuestionType = In.getQuestionTypefromString();
                modifyingQuiz.setQuestionType(newQuestionType);
            }
            catch (IllegalArgumentException e) {}
            
            System.out.print("Enter new time limit (00:00:00)(or press 'Enter' to keep current): ");
            String newTimeLimit = In.nextLine();
            if (!newTimeLimit.isEmpty()) {
                modifyingQuiz.setTimeLimit(newTimeLimit);
            }
            System.out.print("Enter new available date (yyyy/MM/dd)(or press 'Enter' to keep current): ");
            String newAvailableDate = In.nextLine();
            if (!newAvailableDate.isEmpty()) {
                modifyingQuiz.setAvailableDate(newAvailableDate);
            }

            studyPlanner.setAllTasks(allTasks);
        }

        else if (modifyingTask instanceof Assignment) {
            Assignment modifyingAssignment = (Assignment) modifyingTask;

            System.out.print("Enter new submission link (or press 'Enter' to keep current): ");
            String newSubmissionLink = In.nextLine();
            if (!newSubmissionLink.isEmpty()) {
                modifyingAssignment.setSubmissionLink(newSubmissionLink);
            }
            System.out.print("Enter new max score (or press 'Enter' to keep current): ");
            String newMaxScore = In.nextLine();
            if (!newMaxScore.isEmpty()) {
                modifyingAssignment.setMaxScore(newMaxScore);
            }
            System.out.print("Enter new group members (or press 'Enter' to keep current): ");
            String newGroupMembers = In.nextLine();
            if (!newGroupMembers.isEmpty()) {
                modifyingAssignment.setGroupMembers(newGroupMembers);
            }
            System.out.print("Enter new available date (or press 'Enter' to keep current): ");
            String newAvailableDate = In.nextLine();
            if (!newAvailableDate.isEmpty()) {
                modifyingAssignment.setAvailableDate(newAvailableDate);
            }

            studyPlanner.setAllTasks(allTasks);

        }

        else if (modifyingTask instanceof Revision) {
            Revision modifyingRevision = (Revision) modifyingTask;

            System.out.print("Enter new material type (or press 'Enter' to keep current): ");
            try {
                MaterialType newMaterialType = In.getMaterialTypefromString();
                modifyingRevision.setMaterialType(newMaterialType);
            }
            catch (IllegalArgumentException e) {}
        
            studyPlanner.setAllTasks(allTasks);

        }
    }


    void removeMenu() {
        System.out.println("~ The taskes that you can remove ~");
        ListManager viewAllTasks = studyPlanner.listTasks();
        this.studyPlanner.displayTaskList(viewAllTasks);

        List<Task> allTasks = studyPlanner.getAllTasks();
        while(true) {
            System.out.println();
            System.out.println("a. Select one task that you want to remove ");
            System.out.println("b. Stop removing");
            System.out.print("Your selection: ");
            char secondChoice = In.nextChar();

            if (secondChoice == 'a') {
                System.out.print("- Select one task that you want to remove: ");
                int selectedIndex = In.nextInt();
                if (selectedIndex < 1 || selectedIndex > allTasks.size()) {
                    System.out.println("Invalid selection. Please select a valid task number.");
                    continue; 
                }
                allTasks.remove(selectedIndex - 1);
                studyPlanner.setAllTasks(allTasks);
            }
            else if (secondChoice == 'b') break;
            else System.out.println("Pick an option a or b");
        }
        
    }

}

class TaskList {
    static Task[] ALLTASKS = {
        new Revision("BRM week 1,2,3", "preparing for final exam", "2024/11/11", "23:59:59", "Business Requirements Modelling", Priority.MEDIUM, Status.NOT_STARTED, MaterialType.PPT),
        new Revision("Pro week 7", "revising pre-tutorial, tutorial code, lab code, quiz", "2024/11/11", "23:59:59", "Programming 2", Priority.HIGH, Status.NOT_STARTED, MaterialType.DOCUMENT),

        
        new Quiz("BRM week 8", "in-class quiz", "2024/11/11", "18:00:00", "2024/11/11", "Business Requirements Modelling", Priority.LOW, Status.NOT_STARTED, "10", QuestionType.MULTIPLE_CHOICE, "00:10:00"),

        new Assignment("BRM week 5, 8 reflection", "writing overdue reflection, week 5 and 8 reflection of BRM", "2024/12/8", "16:00:00", "2024/12/8", "Business Requirements Modelling", Priority.MEDIUM, Status.NOT_STARTED, "https://canvas.utscollege.edu.au/courses/5455/assignments/101403", "100", "Jeong, Byeon, Suk, NamKeyoung"),
        new Assignment("Pro project A", "Finishing writing code and report", "2024/11/13", "12:59:00", "2024/11/24", "Programming 2", Priority.MEDIUM, Status.IN_PROGRESS, "https://canvas.utscollege.edu.au/courses/5533/assignments/100694", "100", "Jeong")

    };
}

