package t1academy.todo_master.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateAllTaskDto;
import t1academy.todo_master.dto.output.GetAllTaskResponse;
import t1academy.todo_master.dto.output.TaskResponse;
import t1academy.todo_master.exception.TaskNotFoundException;
import t1academy.todo_master.mapper.TaskMapper;
import t1academy.todo_master.model.Task;
import t1academy.todo_master.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TaskService тесты")
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;


    @InjectMocks
    private TaskServiceImpl taskService;

    LocalDateTime expectedDueDate = LocalDateTime.parse("2024-04-20T18:47:13");
    private final Task actualTask = new Task(1L, "Название Task 1", "Описание Task 1", expectedDueDate.plusDays(7), false);


    @Test
    @DisplayName("Тест на успешное создание задачи")
    public void testCreateTask_success() {

        var expectedTask = actualTask;
        when(taskRepository.save(any(Task.class))).thenReturn(expectedTask);

        var taskDto = CreateTaskDto
                .builder()
                .title(expectedTask.getTitle())
                .description(expectedTask.getDescription())
                .dueDate(expectedTask.getDueDate())
                .build();
        when(taskMapper.toEntity(taskDto)).thenReturn(expectedTask);

        var response = taskService.createTask(taskDto);

        assertEquals(expectedTask.getId(), response.getId());
        assertEquals(expectedTask.getTitle(), response.getTitle());
        assertEquals(expectedTask.getDescription(), response.getDescription());

        verify(taskMapper).toEntity(taskDto);
        verify(taskRepository).save(expectedTask);
        verifyNoMoreInteractions(taskRepository);
    }


    @Test
    @DisplayName("Тест на успешное получение задачи")
    public void testGetTask_success() {
        var expectedTask = actualTask;
        when(taskRepository.findById(1L)).thenReturn(Optional.of(expectedTask));

        TaskResponse response = taskService.getTask(1L);

        assertEquals(expectedTask.getId(), response.getId());
        assertEquals(expectedTask.getTitle(), response.getTitle());
        assertEquals(expectedTask.getDescription(), response.getDescription());

        verify(taskRepository).findById(1L);
        verifyNoMoreInteractions(taskRepository);
    }


    @Test
    @DisplayName("Тест на получение несуществующей задачи")
    public void testGetTask_taskNotFound() {

        Exception exception = assertThrows(TaskNotFoundException.class,
                () -> taskService.getTaskById(1L));

        assertEquals("Task not found with id: 1", exception.getMessage());
        verify(taskRepository).findById(1L);
    }

    @Test
    @DisplayName("Тест на успешное обновление всех полей задачи")
    public void testUpdateAllTask_success() {

        var oldTask = actualTask;
        when(taskRepository.findById(1L)).thenReturn(Optional.of(oldTask));

        var updateDto = UpdateAllTaskDto.builder()
                .title("New Title")
                .description("New Description")
                .dueDate(oldTask.getDueDate())
                .isCompleted(false)
                .build();


        var expectedTask = new Task(1L, "New Title", "New Description", expectedDueDate.plusDays(7), false);
        when(taskRepository.save(oldTask)).thenReturn(expectedTask);

        var response = taskService.updateAllTask(1L, updateDto);

        assertEquals(expectedTask.getId(), response.getId());
        assertEquals(expectedTask.getTitle(), response.getTitle());
        assertEquals(expectedTask.getDescription(), response.getDescription());

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(oldTask);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    @DisplayName("Тест на обновление несуществующей задачи")
    public void testUpdateAllTask_taskNotFound() {
        var updateDto = mock(UpdateAllTaskDto.class);

        Exception exception = assertThrows(TaskNotFoundException.class,
                () -> taskService.updateAllTask(1L, updateDto));
        assertEquals("Task not found with id: 1", exception.getMessage());

        verify(taskRepository).findById(1L);
        verifyNoMoreInteractions(taskRepository);

    }

    @Test
    @DisplayName("Тест на успешное удаление задачи")
    public void testDeleteTask_success() {
        taskService.deleteTask(1L);
        verify(taskRepository).deleteById(1L);
        verifyNoMoreInteractions(taskRepository);
    }


    @Test
    @DisplayName("Тест на получение всех задач")
    public void testGetAllTasks_success() {

        List<Task> tasks = Arrays.asList(actualTask, actualTask);

        when(taskRepository.findAll()).thenReturn(tasks);


        GetAllTaskResponse response = taskService.getAllTasks();

        assertEquals(2, response.getTasks().size());
        assertEquals(2, response.getCount());
        assertEquals("Название Task 1", response.getTasks().get(0).getTitle());
        assertEquals("Описание Task 1", response.getTasks().get(0).getDescription());
        assertEquals(expectedDueDate.plusDays(7), response.getTasks().get(0).getDueDate());
        assertEquals(false, response.getTasks().get(0).getIsCompleted());

        verify(taskRepository).findAll();
        verifyNoMoreInteractions(taskRepository);
    }
}






