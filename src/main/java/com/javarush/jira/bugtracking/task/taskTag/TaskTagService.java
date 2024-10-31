package com.javarush.jira.bugtracking.task.taskTag;

import com.javarush.jira.bugtracking.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class TaskTagService {
    private final TaskTagRepository taskTagRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public TaskTag create(TaskTagTo taskTagTo) {
        taskRepository.getExisted(taskTagTo.getTaskId());
        log.info("Create task tag for task id {}", taskTagTo.getTaskId());
        TaskTagId taskTagId = new TaskTagId();
        taskTagId.setTaskId(taskTagTo.getTaskId());
        taskTagId.setTag(taskTagTo.getTagName());

        TaskTag taskTag = new TaskTag();
        taskTag.setId(taskTagId);
        taskTagRepository.save(taskTag);
        return taskTag;
    }

}
