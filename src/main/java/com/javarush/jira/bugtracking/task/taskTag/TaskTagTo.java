package com.javarush.jira.bugtracking.task.taskTag;

import com.javarush.jira.common.to.BaseTo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Getter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class TaskTagTo extends BaseTo{
    @NotNull
    Long taskId;

    @Size(max = 32)
    @NotNull
    String tagName;
}
