import React, { useEffect } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';
import { selectedLanguageState } from '../../atoms/selectedLanguage/selectedLanguage';
import { useRecoilValue } from "recoil";
import NotFoundPage from '../../pages/NotFoundPage/NotFoundPage';
import LoginPage from '../../pages/adminPages/LoginPage/LoginPage';
import JoinPage from '../../pages/adminPages/JoinPage/JoinPage';
import OAuth2LoginPage from '../../pages/adminPages/OAuth2LoginPage/OAuth2LoginPage';


function AdminRoute(props) {
    // const navigate = useNavigate();

    // const queryClient = useQueryClient();
    // const queryState = queryClient.getQueryState(["userMeQuery"]);

    // useEffect(() => {
    //     if(queryState.status === "success") {
    //         navigate("/");
    //     }
    // }, [queryState]);

    // return queryState.status === "error" &&       일단은 밑에걸로 임시

    const selectedLanguage = useRecoilValue(selectedLanguageState); // 전역 상태 가져오기

    return (
        <Routes>
            {/* <Route path="/login" element={<LoginPage />} />
            <Route path='/login/oauth2' element={<OAuth2LoginPage />} />
            <Route path="/join" element={<JoinPage />} /> */}

            <Route path="/join/*" element={<JoinPage />} />
            <Route path="/login/*" element={<LoginPage />} />
            <Route path='/login/oauth2' element={<OAuth2LoginPage />} />
            <Route path="/*" element={<NotFoundPage />} />
        </Routes>
    )
}

export default AdminRoute;