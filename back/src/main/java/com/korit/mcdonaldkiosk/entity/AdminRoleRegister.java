package com.korit.mcdonaldkiosk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRoleRegister {
    private int roleAdminRegisterId;
    private int adminId;
    private int roleId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Role role;
}
