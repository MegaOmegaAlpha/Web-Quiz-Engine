package engine.controllers;

import engine.dto.quiz.QuizAnswer;
import engine.dto.quiz.QuizDTO;
import engine.dto.quiz.QuizResult;
import engine.dto.user.UserDTO;
import engine.exception.NoSuchQuizException;
import engine.exception.UserIsNoAuthorException;
import engine.model.quiz.QuizEntity;
import engine.model.quiz.completed.CompletedQuizData;
import engine.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class QuizController {

    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @RequestMapping(value = "/quizzes/{id}", method = RequestMethod.GET)
    public QuizDTO getQuiz(@PathVariable("id") long id) throws NoSuchQuizException {
        QuizEntity foundEntity = quizService.getById(id);
        return quizService.convertEntityToDTO(foundEntity);
    }

    @RequestMapping(value = "/quizzes", method = RequestMethod.POST)
    public QuizDTO createQuiz(@Valid @RequestBody QuizDTO quizDTO) {
        quizDTO = quizService.addQuiz(quizDTO,
                (UserDTO) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal());
        return quizDTO;
    }

    @RequestMapping(value = "/quizzes", method = RequestMethod.GET)
    public Page<QuizDTO> getAllQuizzes(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return quizService.getAllQuizzesWithPagination(pageable);
    }

    @RequestMapping(value = "/quizzes/completed")
    public Page<CompletedQuizData> getAllQuizzesCompleted(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int pageSize) {
        UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pageable pageable = PageRequest.of(page, pageSize);
        return quizService.getAllQuizzesCompleted(pageable, user);
    }

    @RequestMapping(value = "/quizzes/{id}/solve", method = RequestMethod.POST)
    public QuizResult solveQuiz(@RequestBody QuizAnswer answer,
                                @PathVariable long id) throws NoSuchQuizException {
        return quizService.solve(answer, id);
    }

    @RequestMapping(value = "/quizzes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeQuiz(@PathVariable long id) throws NoSuchQuizException, UserIsNoAuthorException {
        quizService.removeQuiz(id, (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
