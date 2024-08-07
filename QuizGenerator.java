import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswer;

    public Question(String questionText, List<String> options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private String title;
    private List<Question> questions;

    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}

class QuizManager {
    private List<Quiz> quizzes;

    public QuizManager() {
        quizzes = new ArrayList<>();
    }

    public void createQuiz(String title) {
        quizzes.add(new Quiz(title));
    }

    public Quiz getQuiz(String title) {
        for (Quiz quiz : quizzes) {
            if (quiz.getTitle().equalsIgnoreCase(title)) {
                return quiz;
            }
        }
        return null;
    }

    public void addQuestion(String title, String questionText, List<String> options, int correctAnswer) {
        Quiz quiz = getQuiz(title);
        if (quiz != null) {
            quiz.addQuestion(new Question(questionText, options, correctAnswer));
        }
    }

    public void takeQuiz(String title) {
        Quiz quiz = getQuiz(title);
        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : quiz.getQuestions()) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.print("Enter your answer (1-" + options.size() + "): ");
            int answer = scanner.nextInt() - 1;

            if (answer == question.getCorrectAnswer()) {
                score++;
            }
        }

        System.out.println("You scored " + score + "/" + quiz.getQuestions().size());
    }

    public void showQuizzes() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
        } else {
            System.out.println("Available quizzes:");
            for (Quiz quiz : quizzes) {
                System.out.println("- " + quiz.getTitle());
            }
        }
    }
}

public class QuizGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizManager quizManager = new QuizManager();

        System.out.println("Welcome to the Quiz Generator!");
        showCommands();

        while (true) {
            System.out.print("\nEnter a command: ");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "create_quiz":
                    System.out.print("Enter the quiz title: ");
                    String title = scanner.nextLine();
                    quizManager.createQuiz(title);
                    System.out.println("Quiz '" + title + "' created successfully.");
                    break;

                case "add_question":
                    System.out.print("Enter the quiz title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter the question: ");
                    String questionText = scanner.nextLine();
                    List<String> options = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        System.out.print("Enter option " + (i + 1) + ": ");
                        options.add(scanner.nextLine());
                    }
                    System.out.print("Enter the correct option number (1-4): ");
                    int correctAnswer = scanner.nextInt() - 1;
                    scanner.nextLine();  // Consume newline
                    quizManager.addQuestion(title, questionText, options, correctAnswer);
                    System.out.println("Question added successfully.");
                    break;

                case "take_quiz":
                    System.out.print("Enter the quiz title: ");
                    title = scanner.nextLine();
                    quizManager.takeQuiz(title);
                    break;

                case "show_quizzes":
                    quizManager.showQuizzes();
                    break;

                case "show_commands":
                showCommands();
                    break;

                case "exit":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid command. Type 'show_commands' to see available commands.");
            }
        }
    }

    private static void showCommands() {
        System.out.println("Available commands:");
        System.out.println("create_quiz: Create a new quiz");
        System.out.println("add_question: Add a question to an existing quiz");
        System.out.println("take_quiz: Take a quiz");
        System.out.println("show_quizzes: Show all available quizzes");
        System.out.println("show_commands: Show available commands");
        System.out.println("exit: Exit the application");
    }
}