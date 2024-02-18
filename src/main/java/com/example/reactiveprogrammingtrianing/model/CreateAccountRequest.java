package com.example.reactiveprogrammingtrianing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateAccountRequest {
    private String name;
    private String email;
    private String phone;

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
