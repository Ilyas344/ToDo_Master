package t1academy.todo_master.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import t1academy.todo_master.api.TaskApi;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateAllTaskDto;
import t1academy.todo_master.dto.output.GetAllTaskResponse;
import t1academy.todo_master.dto.output.TaskResponse;
import t1academy.todo_master.service.TaskService;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {
    private final TaskService taskService;

    @Override
    public ResponseEntity<GetAllTaskResponse> getAllTasks() {
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @Override
    public ResponseEntity<TaskResponse> getTaskById(final Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @Override
    public ResponseEntity<TaskResponse> createTask(final CreateTaskDto task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
    }

    @Override
    public ResponseEntity<TaskResponse> updateTask(final Long id, final UpdateAllTaskDto task) {
        return ResponseEntity.ok(taskService.updateAllTask(id, task));
    }

    @Override
    public ResponseEntity<Void> deleteTask(final Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();

    }
}
