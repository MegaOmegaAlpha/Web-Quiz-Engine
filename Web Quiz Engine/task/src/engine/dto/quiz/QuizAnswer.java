package engine.dto.quiz;

import java.util.List;

public class QuizAnswer {

    private String key;
    private List<Integer> answer;

    public QuizAnswer(String key, List<Integer> options) {
        this.key = key;
        this.answer = options;
    }

    public QuizAnswer() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuizAnswer{" +
                "key='" + key + '\'' +
                ", answer=" + answer +
                '}';
    }
}
