package com.javarush.jira.bugtracking.task.taskTag;

import com.javarush.jira.common.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TaskTagRepository extends BaseRepository<TaskTag> {

}
