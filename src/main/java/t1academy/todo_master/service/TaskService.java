package t1academy.todo_master.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import t1academy.todo_master.dto.input.AllUpdateTaskDto;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateTaskDto;
import t1academy.todo_master.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask(CreateTaskDto task);
    Task getTask(Long taskId);

    Task updateTask(Long id, UpdateTaskDto task);
    Task allUpdateTask(Long id, AllUpdateTaskDto task);
    void deleteTask(Long taskId);
    List<Task> getAllTasks();
    Page<Task> getAllTasks(Pageable pageable);
}
