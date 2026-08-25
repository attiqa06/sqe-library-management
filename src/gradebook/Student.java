package gradebook;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String studentId;
    private List<Double> scores;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.scores = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public List<Double> getScores() { return scores; }

    public List<Double> getScores() { return scores; }

public void addScore(double score) {
    if (score < 0) {
        throw new IllegalArgumentException("Score cannot be negative");
    }
}
}
