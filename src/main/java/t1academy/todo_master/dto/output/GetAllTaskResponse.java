package t1academy.todo_master.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import t1academy.todo_master.model.Task;

import java.util.List;

@Data
@Validated
public class GetAllTaskResponse {
    @Schema(description = "Количество задач", example = "0")
    @PositiveOrZero
    private int count;
    @Schema(description = "Список задач")
    private List<TaskResponse> tasks;


    public GetAllTaskResponse(List<Task> tasks) {
        this.tasks = tasks.stream().map(TaskResponse::new).toList();
        this.count = this.tasks.size();
    }
}