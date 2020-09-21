package engine.service.quiz;

import engine.dto.user.UserDTO;
import engine.exception.NoSuchQuizException;
import engine.model.quiz.QuizEntity;
import engine.model.quiz.completed.CompletedQuizData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface QuizDao {

    QuizEntity addQuiz(QuizEntity quizDTO);

    QuizEntity getById(long id) throws NoSuchQuizException;

    List<QuizEntity> getAll();

    void removeById(long id) throws NoSuchQuizException;

    Page<QuizEntity> getAllWithPagination(Pageable pageable, UserDTO userDTO);

    Page<CompletedQuizData> getAllCompleted(Pageable pageable, UserDTO userDTO);

    void saveCompletionDate(CompletedQuizData completedQuizData);
}
