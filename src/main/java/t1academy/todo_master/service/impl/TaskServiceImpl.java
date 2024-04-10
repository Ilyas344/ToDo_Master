package t1academy.todo_master.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import t1academy.todo_master.dto.input.AllUpdateTaskDto;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateTaskDto;
import t1academy.todo_master.exception.TaskNotFoundException;
import t1academy.todo_master.mapper.TaskMapper;
import t1academy.todo_master.model.Task;
import t1academy.todo_master.repository.TaskRepository;
import t1academy.todo_master.service.TaskService;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public Task createTask(CreateTaskDto task) {

        return taskRepository.save(taskMapper.toEntity(task));
    }

    @Override
    public Task getTask(Long taskId) {
        return taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + taskId));
    }

    @Override
    public Task updateTask(Long id, UpdateTaskDto task) {
        Task oldTask = getTask(id);
        taskMapper.partialUpdate(task, oldTask);
        return taskRepository.save(oldTask);
    }

    @Override
    public Task allUpdateTask(Long id, AllUpdateTaskDto task) {
        Task oldTask = getTask(id);
        BeanUtils.copyProperties(task, oldTask);
        return taskRepository.save(oldTask);
    }


    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
}
