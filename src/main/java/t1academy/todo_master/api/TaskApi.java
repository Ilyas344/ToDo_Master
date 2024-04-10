package t1academy.todo_master.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1academy.todo_master.dto.input.AllUpdateTaskDto;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.model.Task;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "To-Do Master API", description = "Сервис для управления задачами")
@RequestMapping("/api/v1")
public interface TaskApi {

    @GetMapping("/tasks/all")
    ResponseEntity<List<Task>> getAllTasks();

    @GetMapping("/tasks")
    ResponseEntity<Page<Task>> getAllTasks(@RequestParam(defaultValue = "10") int pageSize,
                                           @RequestParam(defaultValue = "0") int pageNumber);

    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id);

    @PostMapping("/tasks")
    ResponseEntity<Task> createTask(@Valid @RequestBody CreateTaskDto task);

    @PutMapping("/tasks/{id}")
    ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody AllUpdateTaskDto task);

    @PatchMapping("/tasks/{id}")
    ResponseEntity<Task> updateTask(@PathVariable
                                    @Parameter(description = "ID задачи") Long id,

                                    @RequestParam(required = false)
                                    @Parameter(description = "Название задачи") String title,

                                    @RequestParam(required = false)
                                    @Parameter(description = "Подробное описание задачи") String description,

                                    @RequestParam(required = false)
                                    @Parameter(description = "Дата и время, к которым задача должна быть выполнена")
                                    LocalDateTime dueDate,

                                    @RequestParam(required = false)
                                    @Parameter(description = "Флаг, указывающий, выполнена ли задача")
                                    Boolean completed);

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id);


}
