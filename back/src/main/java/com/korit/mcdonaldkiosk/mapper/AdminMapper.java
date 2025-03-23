package com.korit.mcdonaldkiosk.mapper;

import com.korit.mcdonaldkiosk.entity.Admin;
import com.korit.mcdonaldkiosk.entity.OAuth2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * AdminMapper 인터페이스
 *
 * 관리자 관련 데이터베이스 작업을 수행하는 MyBatis 매퍼 인터페이스
 * - 관리자의 기본 정보 조회, 수정, OAuth2 관련 정보 저장 및 관리자 계정 활성화/비활성화 기능을 제공
 */
@Mapper
public interface AdminMapper {

    /**
     * 주어진 관리자 이름으로 관리자를 조회하는 메서드
     *
     * @param adminName 관리자 이름
     * @return Admin 객체
     */
    Admin selectByAdminName(String adminName);

    /**
     * 주어진 관리자 ID로 관리자를 조회하는 메서드
     *
     * @param adminId 관리자 ID
     * @return Admin 객체
     */
    Admin selectByAdminId(int adminId);

    /**
     * 주어진 OAuth2 이름으로 관리자를 조회하는 메서드
     *
     * @param oauth2name OAuth2 이름
     * @return Admin 객체
     */
    Admin findAdminByOAuth2name(String oauth2name);

    /**
     * 새로운 관리자 정보를 DB에 저장하는 메서드
     *
     * @param admin Admin 객체
     * @return 저장된 행의 수
     */
    int insertAdmin(Admin admin);

    /**
     * OAuth2 관련 정보를 저장하는 메서드
     *
     * @param oAuth2 OAuth2 객체
     * @return 저장된 행의 수
     */
    int saveOAuth2(OAuth2 oAuth2);

    /**
     * 주어진 관리자 ID로 비밀번호를 업데이트하는 메서드
     *
     * @param adminId 관리자 ID
     * @param adminPassword 새 비밀번호
     * @return 업데이트된 행의 수
     */
    int updatePasswordById(
            @Param("adminId") int adminId,
            @Param("adminPassword") String adminPassword);

    /**
     * 주어진 관리자 ID로 이메일을 업데이트하는 메서드
     *
     * @param adminId 관리자 ID
     * @param email 새 이메일
     * @return 업데이트된 행의 수
     */
    int updateEmailById(
            @Param("adminId") int adminId,
            @Param("email") String email
    );

    /**
     * 주어진 관리자 ID로 상호명을 업데이트하는 메서드
     *
     * @param adminId 관리자 ID
     * @param tradeName 새 상호명
     * @return 업데이트된 행의 수
     */
    int updateTradeNameById(
            @Param("adminId") int adminId,
            @Param("tradeName") String tradeName);

    /**
     * 주어진 관리자 이름으로 계정 활성화/비활성화 상태를 업데이트하는 메서드
     *
     * @param adminName 관리자 이름
     * @return 업데이트된 행의 수
     */
    int updateAccountEnabledByAdminName(@Param("adminName") String username);
}
