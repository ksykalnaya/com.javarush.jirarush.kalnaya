package com.javarush.jira.bugtracking.task.taskTag;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class TaskTagId implements Serializable {
      @NotNull
      @Column(name = "task_id")
      private Long taskId;

      @Size(max = 32)
      @NotNull
      @Column(name = "tag")
      private String tag;

}