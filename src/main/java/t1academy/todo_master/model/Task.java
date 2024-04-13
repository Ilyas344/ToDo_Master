package t1academy.todo_master.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    /**
     * Идентификатор задачи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Название задачи.
     */
    private String title;

    /**
     * Подробное описание задачи.
     */
    private String description;

    /**
     * Дата и время, к которым задача должна быть выполнена.
     */
    private LocalDateTime dueDate;

    /**
     * Флаг, указывающий, выполнена ли задача.
     */
    private Boolean isCompleted;

}
