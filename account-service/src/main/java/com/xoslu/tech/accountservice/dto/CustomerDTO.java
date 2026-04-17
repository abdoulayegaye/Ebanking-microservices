package com.xoslu.tech.accountservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {
    private Long id;
    private String fullName;
    private String email;
}

