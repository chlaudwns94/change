package com.korit.mcdonaldkiosk.dto.request;

import lombok.Data;

@Data
public class ReqAdminSignInDto {
    private String adminName;
    private String adminPassword;
}
