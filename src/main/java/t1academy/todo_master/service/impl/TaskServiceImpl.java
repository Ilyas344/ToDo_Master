package t1academy.todo_master.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateAllTaskDto;
import t1academy.todo_master.dto.input.UpdateTaskDto;
import t1academy.todo_master.dto.output.GetAllTaskResult;
import t1academy.todo_master.dto.output.GetTaskResult;
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
    public GetTaskResult getTask(Long taskId) {
        return new GetTaskResult(getTaskById(taskId));
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
    public GetAllTaskResult getAllTasks() {
        return new GetAllTaskResult(taskRepository.findAll());
    }

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    private Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() ->
                new TaskNotFoundException("Task not found with id: " + taskId));
    }
}
