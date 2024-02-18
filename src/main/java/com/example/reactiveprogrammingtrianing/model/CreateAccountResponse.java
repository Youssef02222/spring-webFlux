package com.example.reactiveprogrammingtrianing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAccountResponse {
    private String email;
    private String accountId;
}
