import { QueryClient, useQueryClient } from '@tanstack/react-query';
import React, { useEffect } from 'react';
import { Route, Routes, useNavigate } from 'react-router-dom';

import MainSidebar from '../../components/common/MainSidebar/MainSidebar';
import MainContainer from '../../components/common/MainContainer/MainContainer';
import AdminMenuPage from '../../pages/adminPages/AdminMenuPage/AdminMenuPage';
import AdminProductPage from '../../pages/adminPages/AdminProductPage/AdminProductPage';
import AdminOptionPage from '../../pages/adminPages/AdminOptionPage/AdminOptionPage';
import AdminAccountPage from '../../pages/adminPages/AdminAccountPage/AdminAccountPage';
import AdminOrderPage from '../../pages/adminPages/AdminOrderPage/AdminOrderPage';
import AdminSalesPage from '../../pages/adminPages/AdminSalesPage/AdminSalesPage';
import AdminProductManage from '../../pages/adminPages/AdminProductManage/AdminProductManage';
import AdminMyPage from '../../pages/adminPages/AdminMyPage/AdminMyPage';

function AdminMainRoute(props) {
    //로그인 구현 후 사용
    // const navigate = useNavigate();
    // const queryClient = useQueryClient();
    // const queryState = queryClient.getQueryState(["userMeQuery"]);

    //f로그아웃 상태로 접근 시 로그인 페이지로
    // useEffect (() => {
    //     if(queryState.status === "error") {
    //         navigate("/auth/login");
    //     }
    // }, [queryState]);

    return (
        //queryState.status === "success" &&
        <>
            <MainSidebar />
            <MainContainer>
                <Routes>
                    <Route path='/menu' element={<AdminMenuPage />} /> 
                    <Route path='/product' element={<AdminProductPage />} /> 
                    <Route path='/product/manage' element={<AdminProductManage />} /> 
                    <Route path='/option' element={<AdminOptionPage />} /> 
                    <Route path='/sales' element={<AdminSalesPage />} /> 
                    <Route path='/order' element={<AdminOrderPage />} />
                    <Route path='/mypage' element={<AdminMyPage />} />
                    <Route path='/account' element={<AdminAccountPage />} /> 
                </Routes>
            </MainContainer>
        </>
    )
}

export default AdminMainRoute;