package engine.service.quiz.completed;

import engine.model.quiz.completed.CompletedQuizData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CompletedQuizRepository extends JpaRepository<CompletedQuizData, Date> {

    @Query(value =
            "select * from quiz_completed qc join quiz q on qc.quiz_id = q.id " +
            "join user u on q.user_id = u.id where u.email = ?1 " +
            "order by qc.completedat",
    countQuery =
            "select count(*) from quiz_completed qc join quiz q on qc.quiz_id = q.id " +
            " join user u on q.user_id = u.id where u.email = ?1",
    nativeQuery = true)
    Page<CompletedQuizData> findAllByQuizUser(String userEmail, Pageable pageable);

}
