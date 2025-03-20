package com.korit.mcdonaldkiosk.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReqAdminSignUpDto {
    private String adminName;
    private String adminPassword;
    private String email;
    private String tradeName;
}
