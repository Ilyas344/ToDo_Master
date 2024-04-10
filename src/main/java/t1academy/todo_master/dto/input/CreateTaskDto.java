package t1academy.todo_master.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

/**
 * DTO for {@link t1academy.todo_master.model.Task}
 */

@Builder
@Validated
public record CreateTaskDto(
        @NotBlank(message = "Title cannot be null or blank") String title,
        @NotBlank(message = "Description cannot be null or blank") String description,
        LocalDateTime dueDate) {
}