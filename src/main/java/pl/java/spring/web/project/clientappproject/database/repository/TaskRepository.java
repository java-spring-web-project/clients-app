package pl.java.spring.web.project.clientappproject.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.java.spring.web.project.clientappproject.database.model.Task;


//is @Repository annotation necessary?
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Transactional
    void deleteById(long id);
}
