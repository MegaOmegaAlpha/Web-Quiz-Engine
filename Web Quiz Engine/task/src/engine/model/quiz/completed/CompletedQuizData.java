package engine.model.quiz.completed;

import engine.model.quiz.QuizEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "quiz_completed")
public class CompletedQuizData {

    @Id
    @Column(name = "completed_at")
    private Date completedAt;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quizEntity;

    public CompletedQuizData(Date completedAt, long completedQuizId, QuizEntity quizEntity) {
        this.completedAt = completedAt;
        this.quizEntity = quizEntity;
    }

    public CompletedQuizData() {
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public QuizEntity getQuizEntity() {
        return quizEntity;
    }

    public void setQuizEntity(QuizEntity quizEntity) {
        this.quizEntity = quizEntity;
    }

}
