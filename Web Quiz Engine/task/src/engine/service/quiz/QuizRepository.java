package engine.service.quiz;

import engine.dto.user.UserDTO;
import engine.model.quiz.QuizEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    @Query(
            value = "select * from quiz q where q.user_id = ?1",
            countQuery = "select count(*) from quiz q where q.user_id = ?1",
            nativeQuery = true
    )
    Page<QuizEntity> findAllByUser(String userEmail, Pageable pageable);

    List<QuizEntity> findAllByUser(UserDTO user);

}
