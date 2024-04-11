package t1academy.todo_master.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import t1academy.todo_master.model.Task;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    @Schema(description = "id задачи", example = "1")
    private Long id;
    @Schema(description = "Название задачи", example = "Название задачи")
    private String title;
    @Schema(description = "Описание задачи", example = "Описание задачи")
    private String description;
    @Schema(description = "Дата и время, к которым задача должна быть выполнена.", example = "2024-04-11T20:58:00.640Z")
    private LocalDateTime dueDate;
    @Schema(description = "Флаг, указывающий, выполнена ли задача.", example = "false")
    private Boolean isCompleted;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate();
        this.isCompleted = task.getIsCompleted();
    }
}

