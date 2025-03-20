package com.korit.mcdonaldkiosk.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqUserDto {
    private int userId;
    private String phoneNumber;
}
