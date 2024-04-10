package t1academy.todo_master.dto.input;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * DTO for {@link t1academy.todo_master.model.Task}
 */

@Builder
public record UpdateTaskDto(String title, String description, LocalDateTime dueDate, Boolean isCompleted) {
}