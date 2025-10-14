package org.example.demo.dto.request;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data

public class LoginRequest {
    private String username;
    private String password;
}
