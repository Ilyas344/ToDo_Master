package t1academy.todo_master.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import t1academy.todo_master.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {




}