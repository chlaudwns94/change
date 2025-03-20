/**@jsxImportSource @emotion/react */
import { Link, useNavigate } from 'react-router-dom';
import * as s from './style';
import React, { useState } from 'react';
import { useJoinMutation } from '../../../mutations/authMutation';
import { Input } from '@mui/material';
import ValidInput from '../../../components/ValidInput/ValidInput';
import { SiGoogle, SiKakao, SiNaver } from 'react-icons/si';

function JoinPage(props) {
    const joinMutation = useJoinMutation();
    const navigate = useNavigate();
    const [ inputValue, setInputValue ] = useState({
        adminName: "",
        adminPassword: "",
        passwordCheck: "",
        email: "",
        tradeName: "",
    });

    const [ inputValidError, setInputValidError ] = useState({
        adminName: false,
        email: false,
        tradeName: false,
        adminPassword: false,
        passwordCheck: false,
    });

    const handleInputOnChange = (e) => {
        setInputValue(prev => ({
            ...prev,
            [e.target.name]: e.target.value,
        }));
        console.log(inputValue)
    }

    const isErrors = () => {
        const isEmpty = Object.values(inputValue).map(value => !!value).includes(false);
        const isValid = Object.values(inputValidError).includes(true);
        return isEmpty || isValid;
    }

    const handlePasswordOnFocus = () => {
        setInputValue(prev => ({
            ...prev,
            adminPassword: "",
            passwordCheck: "",
        }));
    }


    const handleJoinOnClick = async () => {
        if (isErrors()) {
            alert("가입 정보를 다시 확인해주세요.");
            return;
        }

        if (inputValue.adminPassword !== inputValue.passwordCheck) {
            alert("비밀번호가 일치하지 않습니다.");
            setInputValidError(prev => ({
                ...prev,
                adminPassword: true,
                passwordCheck: true
            }));
            return;
        }
    
        try {
            const response = await joinMutation.mutateAsync({
                adminName: inputValue.adminName, 
                adminPassword: inputValue.adminPassword,
                passwordCheck: inputValue.passwordCheck,
                email: inputValue.email,
                tradeName: inputValue.tradeName,
            });
    
            alert("가입해 주셔서 감사합니다.");
        } catch (error) {
            console.error("회원가입 오류:", error);
    
            if (error.response?.status === 400) {
                setInputValidError(prev => ({
                    ...prev,
                    adminName: true,
                }));
            } else {
                alert("서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
                
            }
        }
    };

    const handleOAuth2LoginOnClick = (provider) => {
        window.location.href = `http://localhost:8080/oauth2/authorization/${provider}`;
    }

    return (
        <div css={s.container}>
            <div css={s.logoContainer}>
                    <img src="https://blog.kakaocdn.net/dn/w1UK3/btqwTx0mNVX/ki6E4Mva5YavwrOFJQkCP1/img.jpg" alt="" />
                </div>
                <div css={s.test1}>
                    <div css={s.formContainer}>
                        <div>
                            <h1 css={s.title}>McDonald Admin</h1>
                            <ValidInput
                                type={"text"}
                                name={"adminName"}
                                placeholder={"사용자이름"}
                                value={inputValue.adminName}
                                onChange={handleInputOnChange}
                                inputValidError={inputValidError}
                                />
                            <ValidInput
                                type={"password"}
                                name={"adminPassword"}
                                placeholder={"비밀번호"}
                                value={inputValue.adminPassword}
                                onChange={handleInputOnChange}
                                onFocus={handlePasswordOnFocus}
                                inputValidError={inputValidError}
                                />
                            <ValidInput
                                type={"password"}
                                name={"passwordCheck"}
                                placeholder={"비밀번호 확인"}
                                value={inputValue.passwordCheck}
                                onChange={handleInputOnChange}
                                inputValidError={inputValidError}
                                />
                                <ValidInput
                                type={"text"}
                                name={"email"}
                                placeholder={"이메일"}
                                value={inputValue.email}
                                onChange={handleInputOnChange}
                                inputValidError={inputValidError}
                                />
                            <ValidInput
                                type={"text"}
                                name={"tradeName"}
                                placeholder={"상호명"}
                                value={inputValue.tradeName}
                                onChange={handleInputOnChange}
                                inputValidError={inputValidError}
                                />
                        </div>
                    </div>

                    <div css={s.rightContainer}>
                        <h3 css={s.socialLoginTitle}>간편 회원가입</h3>
                        <div css={s.socialLoginBox}>
                            <img onClick={() => handleOAuth2LoginOnClick("google")} src="https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/5rH/image/aFrEyVpANu07FvoBZQbIB4aF_uc"  alt="Google" />
                            <img onClick={() => handleOAuth2LoginOnClick("naver")}src="https://i.namu.wiki/i/p_1IEyQ8rYenO9YgAFp_LHIAW46kn6DXT0VKmZ_jKNijvYth9DieYZuJX_E_H_4GkCER_sVKhMqSyQYoW94JKA.svg" alt="Naver" />
                            <div css={s.buttonContainer}>
                                <Link css={s.button} to={"/admin/login"}> 로그인</Link>               
                                <button css={s.button} onClick={handleJoinOnClick}>
                                    가입하기
                                </button>
                            </div>
                            <img src="https://upload.wikimedia.org/wikipedia/commons/e/e3/KakaoTalk_logo.svg" alt="Kakao" />
                        </div>
                    </div>
                </div>
                
        </div>
    );
}

export default JoinPage;