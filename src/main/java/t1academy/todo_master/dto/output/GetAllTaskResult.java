package t1academy.todo_master.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import t1academy.todo_master.model.Task;

import java.util.List;

@Data
@Validated
public class GetAllTaskResult {
    @Schema(description = "Список задач")
    private List<TaskResponse> tasks;

    @Schema(description = "Количество задач", example = "0")
    @Positive
    private int count;

    public GetAllTaskResult(List<Task> tasks) {
        this.tasks = tasks.stream().map(TaskResponse::new).toList();
        this.count = this.tasks.size();
    }
}