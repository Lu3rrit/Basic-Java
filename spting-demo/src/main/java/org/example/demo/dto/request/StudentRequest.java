package org.example.demo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequest {

    private String studentCode;
    private String studentName;
    private Integer studentAge;
}
