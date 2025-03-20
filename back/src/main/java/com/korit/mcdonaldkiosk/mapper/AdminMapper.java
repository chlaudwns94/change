package com.korit.mcdonaldkiosk.mapper;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.entity.OAuth2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    Admin selectByAdminName(String adminName);
    Admin selectByAdminId(int adminId);
    Admin findAdminByOAuth2name(String oauth2name);

    int insertAdmin(Admin admin);


    int saveOAuth2(OAuth2 oAuth2);

    int updatePasswordById(
            @Param("adminId") int adminId,
            @Param("adminPassword") String adminPassword);

    int updateEmailById(
            @Param("adminId") int adminId,
            @Param("email") String email
    );

    int updateTradeNameById(
            @Param("adminId") int adminId,
            @Param("tradeName") String tradeName);

    int updateAccountEnabledByAdminName(@Param("adminName") String username);

}
