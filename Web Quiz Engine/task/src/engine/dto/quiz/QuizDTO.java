package engine.dto.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class QuizDTO {

    private int id;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String text;

    @NotNull
    @Size(min = 2)
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> answer;

    public QuizDTO(String title, String text, List<String> options, int id, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.id = id;
        this.answer = answer;
    }

    public QuizDTO() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizDTO quizDTO = (QuizDTO) o;
        return id == quizDTO.id &&
                answer == quizDTO.answer &&
                Objects.equals(title, quizDTO.title) &&
                Objects.equals(text, quizDTO.text) &&
                Objects.equals(options, quizDTO.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, options, id, answer);
    }

    @Override
    public String toString() {
        return "QuizDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                '}';
    }
}
