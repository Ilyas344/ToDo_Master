package t1academy.todo_master.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import t1academy.todo_master.model.Task;

@Data
public class GetTaskResult {
    @Schema(description = "Задача")
    private TaskResponse task;

    public GetTaskResult(Task task) {
        this.task = new TaskResponse(task);
    }
}