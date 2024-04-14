package t1academy.todo_master.service;

import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateAllTaskDto;
import t1academy.todo_master.dto.output.GetAllTaskResponse;
import t1academy.todo_master.dto.output.TaskResponse;
import t1academy.todo_master.model.Task;

public interface TaskService {

    /**
     * Создает новую задачу на основе данных из CreateTaskDto.
     *
     * @param task DTO-объект, содержащий данные новой задачи (required)
     * @return TaskResponse объект, содержащий информацию о созданной задаче
     */
    TaskResponse createTask(CreateTaskDto task);

    /**
     * Получает задачу по ее идентификатору.
     *
     *  @param taskId Идентификатор задачи (required)
     *  @return GetTaskResponse объект, содержащий информацию о найденной задаче
     */
    TaskResponse getTask(Long taskId);


    /**
     * Полностью обновляет задачу по ее идентификатору.
     *
     * @param id Идентификатор задачи (required)
     * @param task DTO-объект, содержащий новые данные задачи (required)
     * @return TaskResponse объект, содержащий информацию об обновленной задаче
     */

    TaskResponse updateAllTask(Long id, UpdateAllTaskDto task);

    /**
     * Удаляет задачу по ее идентификатору.
     *
     * @param taskId Идентификатор задачи (required)
     */
    void deleteTask(Long taskId);

    /**
     * Получает список всех задач.
     *
     * @return GetAllTaskResult объект, содержащий информацию о всех задачах
     */

    GetAllTaskResponse getAllTasks();

    /**
     * Получить задачу по id
     * @param taskId id задачи
     * @throw TaskNotFoundException если задача не найдена
     * @return задача
     */
    Task getTaskById(Long taskId);
}
