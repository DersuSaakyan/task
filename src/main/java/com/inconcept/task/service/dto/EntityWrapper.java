package com.inconcept.task.service.dto;

import java.util.List;

public interface EntityWrapper<T> {
    T castEntityToDo(T t);
    List<T> castEntityToDo(List<T> list);
}
