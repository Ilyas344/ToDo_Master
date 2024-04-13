package t1academy.todo_master.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateAllTaskDto;
import t1academy.todo_master.dto.input.UpdateTaskDto;
import t1academy.todo_master.dto.output.GetAllTaskResponse;
import t1academy.todo_master.dto.output.GetTaskResponse;
import t1academy.todo_master.dto.output.TaskResponse;
import t1academy.todo_master.exception.TaskNotFoundException;
import t1academy.todo_master.mapper.TaskMapper;
import t1academy.todo_master.model.Task;
import t1academy.todo_master.repository.TaskRepository;
import t1academy.todo_master.service.TaskService;


@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse createTask(CreateTaskDto taskDto) {
        Task task = taskRepository.save(taskMapper.toEntity(taskDto));
        return new TaskResponse(task);
    }

    @Override
    public GetTaskResponse getTask(Long taskId) {
        return new GetTaskResponse(getTaskById(taskId));
    }

    @Override
    public TaskResponse updateTask(Long id, UpdateTaskDto task) {
        Task oldTask = getTaskById(id);
        taskMapper.partialUpdate(task, oldTask);
        return new TaskResponse(taskRepository.save(oldTask));
    }

    @Override
    public TaskResponse updateAllTask(Long id, UpdateAllTaskDto task) {
        Task oldTask = getTaskById(id);
        BeanUtils.copyProperties(task, oldTask);
        return new TaskResponse(taskRepository.save(oldTask));
    }


    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public GetAllTaskResponse getAllTasks() {
        return new GetAllTaskResponse(taskRepository.findAll());
    }


    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() ->
                new TaskNotFoundException("Task not found with id: " + taskId));
    }
}
