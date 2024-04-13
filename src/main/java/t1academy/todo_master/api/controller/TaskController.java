package t1academy.todo_master.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import t1academy.todo_master.api.TaskApi;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateAllTaskDto;
import t1academy.todo_master.dto.input.UpdateTaskDto;
import t1academy.todo_master.dto.output.GetAllTaskResponse;
import t1academy.todo_master.dto.output.GetTaskResponse;
import t1academy.todo_master.dto.output.TaskResponse;
import t1academy.todo_master.model.Task;
import t1academy.todo_master.service.TaskService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {
    private final TaskService taskService;

    @Override
    public ResponseEntity<GetAllTaskResponse> getAllTasks() {
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @Override
    public ResponseEntity<Page<Task>> getAllTasks(int pageSize,
                                                  int pageNumber) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Task> tasks = taskService.getAllTasks(pageable);
        return ResponseEntity.ok(tasks);
    }

    @Override
    public ResponseEntity<GetTaskResponse> getTaskById(final Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @Override
    public ResponseEntity<TaskResponse> createTask(final CreateTaskDto task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @Override
    public ResponseEntity<TaskResponse> updateTask(final Long id, final UpdateAllTaskDto task) {
        return ResponseEntity.ok(taskService.updateAllTask(id, task));
    }

    @Override
    public ResponseEntity<TaskResponse> updateTask(final Long id,
                                           final String title,
                                           final String description,
                                           final LocalDateTime dueDate,
                                           final Boolean isCompleted) {
        UpdateTaskDto task = UpdateTaskDto
                .builder()
                .title(title)
                .description(description)
                .dueDate(dueDate)
                .isCompleted(isCompleted)
                .build();
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @Override
    public ResponseEntity<Void> deleteTask(final Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();

    }
}
