package t1academy.todo_master.dto.input;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;



@Builder
@Validated
public record UpdateAllTaskDto(
        @NotBlank(message = "Имя задачи не может быть пустым")
        String title,
        @NotBlank(message = "Описание задачи не может быть пустым")
        String description,
        @NotNull(message = "Дата окончания задачи не может быть пустой")
        @Future(message = "Дата окончания задачи должна быть после сегодняшней")
        LocalDateTime dueDate,
        Boolean isCompleted) {
}