package t1academy.todo_master.dto.input;

import lombok.Builder;

import java.time.LocalDateTime;



@Builder
public record UpdateAllTaskDto(

        String title,
        String description,
        LocalDateTime dueDate,
        Boolean isCompleted) {
}