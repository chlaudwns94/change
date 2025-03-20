/**@jsxImportSource @emotion/react */
import { useNavigate } from 'react-router-dom';
import * as s from './style';
import React from 'react';
import { useQueryClient } from '@tanstack/react-query';
//import { setTokenLocalStorage } from '../../../config/axiosConfig'; 로그인 구현 후 사용
import { BiLogOut } from 'react-icons/bi';

function MainSidebar(props) {
    const navigate =useNavigate();
    const queryClient =useQueryClient();
    const queryDate = queryClient.getQueryData(["useMeQuery"]);

    //로그아웃
    const handleLogoutButtonOnClick = async () => {
        setTokenLocalStorage("AccessToken", null);
        await queryClient.invalidateQueries({queryKey: ["userMeQuery"]});
        navigate("/auth/login");
    }

    return (
        <div css={s.container}>
            <div css={s.header}>
                <p>관리자 모드</p>
            </div>
            <div css={s.body}>
                <div css={s.buttonstyle}>
                    <button css={s.emptybutton}>메뉴 관리</button>
                </div>
                <div css={s.buttonstyle}>
                    <button css={s.emptybutton}>제품 관리</button>
                </div>
                <div css={s.buttonstyle}>
                    <button css={s.emptybutton}>옵션 관리</button>
                </div>
                <div css={s.buttonstyle}>
                    <button css={s.emptybutton}>매출 관리</button>
                </div>
                <div css={s.buttonstyle}>
                    <button css={s.emptybutton}>주문 관리</button>
                </div>
                <div css={s.buttonstyle}>
                    <button css={s.emptybutton}>내 정보</button>
                </div>
                <div css={s.buttonstyle}>
                    <button css={s.emptybutton} onClick={handleLogoutButtonOnClick}>
                        <BiLogOut />로그아웃
                    </button>
                </div>
                <div css={s.footer}></div>
            </div>
        </div>
    );
}

export default MainSidebar;