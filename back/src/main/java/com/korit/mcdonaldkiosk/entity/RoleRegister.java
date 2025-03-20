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
public class RoleRegister {
    private int roleRegisterId;
    private int userId;
    private int roleId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
