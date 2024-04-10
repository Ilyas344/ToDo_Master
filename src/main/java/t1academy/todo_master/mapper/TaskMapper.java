package t1academy.todo_master.mapper;

import org.mapstruct.*;
import t1academy.todo_master.dto.input.CreateTaskDto;
import t1academy.todo_master.dto.input.UpdateTaskDto;
import t1academy.todo_master.model.Task;

import java.time.LocalDateTime;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "isCompleted", source = "dueDate", qualifiedByName = "getIssCompleted")
    Task toEntity(CreateTaskDto createTaskDto);

    CreateTaskDto toDto(Task task);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task partialUpdate(UpdateTaskDto createTaskDto, @MappingTarget Task task);

    @Named("getIssCompleted")
    default Boolean getIssCompleted(LocalDateTime dueDate) {
        return dueDate.isBefore(LocalDateTime.now());
    }
}