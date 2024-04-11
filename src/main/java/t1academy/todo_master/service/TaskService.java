package t1academy.todo_master.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateAllTaskDto;
import t1academy.todo_master.dto.input.UpdateTaskDto;
import t1academy.todo_master.dto.output.GetAllTaskResult;
import t1academy.todo_master.dto.output.GetTaskResult;
import t1academy.todo_master.dto.output.TaskResponse;
import t1academy.todo_master.model.Task;

public interface TaskService {

    TaskResponse createTask(CreateTaskDto task);

    GetTaskResult getTask(Long taskId);

    TaskResponse updateTask(Long id, UpdateTaskDto task);

    TaskResponse updateAllTask(Long id, UpdateAllTaskDto task);
    void deleteTask(Long taskId);

    GetAllTaskResult getAllTasks();
    Page<Task> getAllTasks(Pageable pageable);
}
