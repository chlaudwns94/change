package com.korit.mcdonaldkiosk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {
    private int adminId;
    private String adminName;
    @JsonIgnore
    private String adminPassword;
    private String email;
    private String tradeName;
    private int accountEnabled;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Set<OAuth2> oAuth2s;
}
