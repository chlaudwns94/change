package com.korit.mcdonaldkiosk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqPointDto {
    private String phoneNumber;
    private int calcul;
    private int point;
}
