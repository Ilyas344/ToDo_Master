package t1academy.todo_master.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateAllTaskDto;
import t1academy.todo_master.dto.output.GetAllTaskResponse;
import t1academy.todo_master.dto.output.TaskResponse;
import t1academy.todo_master.exception.InternalServerException;
import t1academy.todo_master.exception.ResponseException;
import t1academy.todo_master.exception.TaskNotFoundException;

@Tag(name = "To-Do Master API", description = "Сервис для управления задачами")
public interface TaskApi {

    @Operation(summary = "Получить все задачи (Get all tasks)",
            description = "Получает все задачи из базы данных (Retrieves all tasks from the database).")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное выполнение",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetAllTaskResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "В случае внутренних ошибок",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = InternalServerException.class))
            )
    })
    @GetMapping("/tasks")
    ResponseEntity<GetAllTaskResponse> getAllTasks();


    @Operation(summary = "Получить задачу по ID (Get task by ID)", description = "Получает задачу из базы данных по ее ID (Retrieves a task from the database by its ID).")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное выполнение",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "В случае нарушения контракта",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseException.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не найден",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskNotFoundException.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "В случае внутренних ошибок",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = InternalServerException.class))
            )
    })
    @GetMapping("/tasks/{id}")
    ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id);


    @Operation(summary = "Создать задачу (Create task)",
            description = "Создает новую задачу на основе данных, переданных в теле запроса (Creates a new task based on the data provided in the request body).")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успешное выполнение",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "В случае нарушения контракта",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseException.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "В случае внутренних ошибок",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = InternalServerException.class))
            )
    })
    @PostMapping("/tasks")
    ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskDto task);


    @Operation(summary = "Обновить задачу (Update task)",
            description = "Обновляет все поля задачи с ID, указанным в пути (Updates all fields of the task with the specified ID).")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное выполнение",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "В случае нарушения контракта",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseException.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не найден",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TaskNotFoundException.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "В случае внутренних ошибок",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = InternalServerException.class))
            )
    })
    @PutMapping("/tasks/{id}")
    ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateAllTaskDto task);


    @Operation(summary = "Удалить задачу по id (Delete task by id)",
            description = "Удаляет задачу из базы данных по ее ID (Deletes a task from the database by its ID).",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content")
            })
    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id);


}
