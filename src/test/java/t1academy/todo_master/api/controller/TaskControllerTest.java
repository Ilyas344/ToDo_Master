package t1academy.todo_master.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateAllTaskDto;
import t1academy.todo_master.dto.output.GetAllTaskResponse;
import t1academy.todo_master.dto.output.TaskResponse;
import t1academy.todo_master.exception.TaskNotFoundException;
import t1academy.todo_master.model.Task;
import t1academy.todo_master.service.TaskService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@DisplayName("Тестирование контроллера TaskController")
class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    TaskService taskService;
    // Необходим для устранения проблем с парсингом времени
    LocalDateTime expectedDueDate = LocalDateTime.parse("2024-04-20T18:47:13");
    private final Task actualTask = new Task(1L, "Название Task 1", "Описание Task 1", expectedDueDate.plusDays(7), false);


    @Test
    @DisplayName("Проверка метода getAllTasks() при успешном выполнении")
    public void testGetAllTasks_Success() throws Exception {
        List<Task> expectedTasks = List.of(actualTask);
        var responseFromService = new GetAllTaskResponse(expectedTasks);

        when(taskService.getAllTasks()).thenReturn(responseFromService);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(1))
                .andExpect(jsonPath("$.tasks", hasSize(expectedTasks.size())))
                .andExpect(jsonPath("$.tasks[0].id").value(1L))
                .andExpect(jsonPath("$.tasks[0].title").value("Название Task 1"))
                .andExpect(jsonPath("$.tasks[0].description").value("Описание Task 1"))
                .andExpect(jsonPath("$.tasks[0].dueDate").value(expectedDueDate.plusDays(7).toString()))
                .andExpect(jsonPath("$.tasks[0].isCompleted").value(false));
        verify(taskService, Mockito.times(1)).getAllTasks();

    }
    @Test
    @DisplayName("Проверка метода getAllTasks() при пустом листе задач")
    public void testGetAllTasks_EmptyList() throws Exception  {
        // Мок поведения
        List<Task> expectedTasks = Collections.emptyList();
        var responseFromService = new GetAllTaskResponse(expectedTasks);
        when(taskService.getAllTasks()).thenReturn(responseFromService);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0))
                .andExpect(jsonPath("$.tasks").isEmpty());
        verify(taskService, Mockito.times(1)).getAllTasks();

    }

    @Test
    @DisplayName("Проверка метода GetTaskById() при успешном выполнении")
    public void testGetTaskById_Success() throws Exception {
        var responseFromService = new TaskResponse(actualTask);
        when(taskService.getTask(actualTask.getId())).thenReturn(responseFromService);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is("Название Task 1")))
                .andExpect(jsonPath("$.description", is("Описание Task 1")))
                .andExpect(jsonPath("$.dueDate", is(expectedDueDate.plusDays(7).toString())))
                .andExpect(jsonPath("$.isCompleted", is(false)));
        verify(taskService, Mockito.times(1)).getTask(1L);

    }

    @Test
    @DisplayName("Проверка метода GetTaskById() при NotFound")
    public void testGetTaskById_NotFound() throws Exception {

        long taskId = 1L;
        when(taskService.getTask(taskId)).thenThrow(new TaskNotFoundException("Task not found with id: " + taskId));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(TaskNotFoundException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("Task not found with id: " + taskId, result.getResolvedException().getMessage()));
        verify(taskService, Mockito.times(1)).getTask(1L);
    }
    @Test
    @DisplayName("Проверка метода createTask() при успешном выполнении")
    public void testCreateTask_Success() throws Exception {
        var record = CreateTaskDto
                .builder()
                .title(actualTask.getTitle())
                .description(actualTask.getDescription())
                .dueDate(actualTask.getDueDate())
                .build();

        var responseFromService = new TaskResponse(actualTask);
        when(taskService.createTask(record)).thenReturn(responseFromService);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(responseFromService));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is("Название Task 1")))
                .andExpect(jsonPath("$.description", is("Описание Task 1")))
                .andExpect(jsonPath("$.dueDate", is(expectedDueDate.plusDays(7).toString())))
                .andExpect(jsonPath("$.isCompleted", is(false)));
        verify(taskService, Mockito.times(1)).createTask(record);
    }

    @Test
    @DisplayName("Проверка метода updateTask() при успешном выполнении")
    public void testUpdateTask_Success() throws Exception {
        var record = UpdateAllTaskDto
                .builder()
                .title(actualTask.getTitle())
                .description(actualTask.getDescription())
                .dueDate(actualTask.getDueDate())
                .isCompleted(actualTask.getIsCompleted())
                .build();

        var responseFromService = new TaskResponse(actualTask);
        when(taskService.updateAllTask(actualTask.getId(), record)).thenReturn(responseFromService);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(responseFromService));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
        verify(taskService, Mockito.times(1)).updateAllTask(1L, record);
    }
    @Test
    @DisplayName("Проверка метода deleteTaskById() при успешном выполнении")
    public void deleteTaskById_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/tasks/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

   }









