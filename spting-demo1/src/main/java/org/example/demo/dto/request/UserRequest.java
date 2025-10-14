package org.example.demo.dto.request;

import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserRequest {
    private String username;
    private String password;
    private String roles;
    private String classCode;
}
