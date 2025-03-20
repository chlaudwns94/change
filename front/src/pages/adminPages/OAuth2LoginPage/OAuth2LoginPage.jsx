import React, { useEffect } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { useQueryClient } from '@tanstack/react-query';
import { setTokenLocalStorage } from '../../../configs/axiosConfig';

function OAuth2LoginPage(props) {
    const navigate = useNavigate();
    const queryClient = useQueryClient();
    const [ searchParams ] = useSearchParams();

    const setAccessToken = async () => {
    const accessToken = searchParams.get("accessToken");
    console.log(accessToken);  // Token이 제대로 출력되는지 확인
    setTokenLocalStorage("AccessToken", accessToken);
    await queryClient.invalidateQueries({ queryKey: ["userMeQuery"] });
    navigate("/");
}

    useEffect(() => {
        setAccessToken();
    }, []);

    return <></>;
}

export default OAuth2LoginPage;