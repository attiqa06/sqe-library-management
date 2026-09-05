package library;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String studentID;  // Note: uppercase 'ID'
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

/**
 * Add a score to the student's scores list.
 * 
 * @param score The score to add. Must be non-negative (>= 0).
 * @throws IllegalArgumentException if the score is negative.
 */
public void addScore(double score) {
    if (score < 0) {
        throw new IllegalArgumentException("Score cannot be negative");
    }
    this.scores.add(score);
}
public double calculateAverage() {
    if (scores.isEmpty()) {
        return 0.0;
    }
    double sum = 0;
    for (double score : scores) {
        sum += score;
    }
    return sum / scores.size();
}
}
"// wip" 
"// fix typo" 
"// more fixes" 
"// done" 
