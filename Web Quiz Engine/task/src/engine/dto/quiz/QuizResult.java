package engine.dto.quiz;

import engine.utils.FeedbackConstants;

public class QuizResult {

    private boolean success;
    private String feedback;

    private final static QuizResult SUCCESS_RESULT = new QuizResult(true, FeedbackConstants.SUCCESS_FEEDBACK);
    private final static QuizResult FAILURE_RESULT = new QuizResult(false, FeedbackConstants.FAILURE_FEEDBACK);

    public QuizResult(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public QuizResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public static QuizResult getSuccessResult() {
        return SUCCESS_RESULT;
    }

    public static QuizResult getFailureResult() {
        return FAILURE_RESULT;
    }

    public static QuizResult getResult(boolean condition) {
        return condition ? SUCCESS_RESULT : FAILURE_RESULT;
    }

}
