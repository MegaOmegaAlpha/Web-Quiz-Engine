package engine.service.quiz;

import engine.dto.quiz.QuizAnswer;
import engine.dto.quiz.QuizDTO;
import engine.dto.quiz.QuizResult;
import engine.dto.user.UserDTO;
import engine.exception.NoSuchQuizException;
import engine.exception.UserIsNoAuthorException;
import engine.model.quiz.QuizEntity;
import engine.model.quiz.completed.CompletedQuizData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuizService {

    private QuizDao quizDao;
    private ModelMapper modelMapper;

    @Autowired
    public QuizService(@Qualifier("quizDatabase") QuizDao quizDao, ModelMapper modelMapper) {
        this.quizDao = quizDao;
        this.modelMapper = modelMapper;
    }

    public QuizEntity getById(long id) throws NoSuchQuizException {
        return quizDao.getById(id);
    }

    public QuizDTO addQuiz(QuizDTO quizDTO, UserDTO user) {
        QuizEntity quizEntity = convertDTOToEntity(quizDTO);
        quizEntity.setUser(user);
        return convertEntityToDTO(quizDao.addQuiz(quizEntity));
    }

    public List<QuizEntity> getAll() throws NoSuchQuizException {
        return quizDao.getAll();
    }

    public void removeQuiz(long id, UserDTO user) throws NoSuchQuizException, UserIsNoAuthorException {
        QuizEntity quizToRemove = getById(id);
        if (quizToRemove.getUser().getEmail().equals(user.getEmail())) {
            quizDao.removeById(id);
        } else {
            throw new UserIsNoAuthorException();
        }
    }

    public QuizDTO convertEntityToDTO(QuizEntity entity) {
        return modelMapper.map(entity, QuizDTO.class);
    }

    public QuizEntity convertDTOToEntity(QuizDTO quizDTO) {
        return modelMapper.map(quizDTO, QuizEntity.class);
    }

    public QuizResult solve(QuizAnswer answer, long quizId) throws NoSuchQuizException {
        return solve(answer, getById(quizId));
    }

    public QuizResult solve (QuizAnswer answer, QuizEntity quizEntity) {
        List<Integer> answerOptionsList = answer.getAnswer();
        List<Integer> quizOptionsList = quizEntity.getAnswer();
        /*if (quizOptionsList == null) {
            return QuizResult.getResult(answerOptionsList.isEmpty());
        }*/
        QuizResult quizResult;
        if (answerOptionsList.equals(quizOptionsList)) {
            CompletedQuizData completedQuiz = new CompletedQuizData();
            completedQuiz.setCompletedAt(new Date(System.currentTimeMillis()));
            completedQuiz.setCompletedQuizId(quizEntity.getId());
            quizDao.saveCompletionDate(completedQuiz);
            quizResult = QuizResult.getSuccessResult();
        } else {
            quizResult = QuizResult.getFailureResult();
        }
        return quizResult;
    }

    public Page<QuizDTO> getAllQuizzesWithPagination(Pageable pageable, UserDTO userDTO) {
        return quizDao.getAllWithPagination(pageable, userDTO).map(this::convertEntityToDTO);
    }

    public Page<CompletedQuizData> getAllQuizzesCompleted(Pageable pageable, UserDTO userDTO) {
        return quizDao.getAllCompleted(pageable, userDTO);
    }

}
