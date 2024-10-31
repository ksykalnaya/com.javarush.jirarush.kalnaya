package com.javarush.jira.bugtracking.task.taskTag;

import com.javarush.jira.bugtracking.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "task_tag")
@NoArgsConstructor
@AllArgsConstructor
public class TaskTag{
    @EmbeddedId
    private TaskTagId id;

}