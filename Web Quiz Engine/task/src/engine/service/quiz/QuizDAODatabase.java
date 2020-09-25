package engine.service.quiz;

import engine.dto.user.UserDTO;
import engine.exception.NoSuchQuizException;
import engine.model.quiz.QuizEntity;
import engine.model.quiz.completed.CompletedQuizData;
import engine.service.quiz.completed.CompletedQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("quizDatabase")
public class QuizDAODatabase implements QuizDao {

    private QuizRepository quizRepository;
    private CompletedQuizRepository completedQuizRepository;

    @Autowired
    public QuizDAODatabase(QuizRepository quizRepository,
                           CompletedQuizRepository completedQuizRepository) {
        this.quizRepository = quizRepository;
        this.completedQuizRepository = completedQuizRepository;
    }

    @Override
    public QuizEntity addQuiz(QuizEntity quizEntity) {
        return quizRepository.save(quizEntity);
    }

    @Override
    public QuizEntity getById(long id) throws NoSuchQuizException {
        return quizRepository.findById(id).orElseThrow(NoSuchQuizException::new);
    }

    @Override
    public List<QuizEntity> getAll() {
        return quizRepository.findAll();
    }

    @Override
    public void removeById(long id) throws NoSuchQuizException {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
        } else {
            throw new NoSuchQuizException();
        }
    }

    @Override
    public Page<QuizEntity> getAllWithPagination(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    @Override
    public Page<CompletedQuizData> getAllCompletedWithPagination(Pageable pageable, UserDTO userDTO) {
        return completedQuizRepository.findAllByQuizUser(userDTO.getEmail(), pageable);
    }

    @Override
    public void saveCompletionDate(CompletedQuizData completedQuizData) {
        completedQuizRepository.save(completedQuizData);
    }

}
