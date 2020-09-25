package engine.dto.quiz;

import java.util.Date;

public class CompletedQuizDTO {

    private long id;
    private Date completedAt;

    public CompletedQuizDTO(long id, Date completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public CompletedQuizDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
}
