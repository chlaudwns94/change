package com.korit.mcdonaldkiosk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OAuth2 {
    private int oAuth2Id;
    private String oAuth2Name;
    private int adminId;
    private String providerName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
