package engine.model.quiz;

import engine.dto.user.UserDTO;
import engine.model.quiz.completed.CompletedQuizData;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz")
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String text;

    @ElementCollection
    private List<String> options;

    @ElementCollection
    private List<Integer> answer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDTO user;

    @OneToMany(mappedBy = "quizEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompletedQuizData> completedQuizDataList;

    public QuizEntity(long id, String title, String text, List<String> options, List<Integer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public QuizEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<CompletedQuizData> getCompletedQuizDataList() {
        return completedQuizDataList;
    }

    public void setCompletedQuizDataList(List<CompletedQuizData> completedQuizDataList) {
        this.completedQuizDataList = completedQuizDataList;
    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                ", user=" + user +
                '}';
    }
}
