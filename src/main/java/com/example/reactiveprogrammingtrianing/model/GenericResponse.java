package com.example.reactiveprogrammingtrianing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class GenericResponse<T> {
    private boolean success;
    private String message;
    private T data;
}