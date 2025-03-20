/**@jsxImportSource @emotion/react */
import * as s from './style';
import { Link, useNavigate } from "react-router-dom";
import { useLoginMutation } from "../../../mutations/authMutation";
import { useState } from "react";
import Swal from 'sweetalert2';
import { setTokenLocalStorage } from '../../../configs/axiosConfig';


function LoginPage(props) {
    const loginMutation = useLoginMutation();
    const navigate = useNavigate();

    const [ inputValue, setInputValue ] = useState({
        adminName: "",
        adminPassword: ""
    });

    const handleInputOnChange = (e) => {
        setInputValue(prev => ({
            ...prev,
            [e.target.name]: e.target.value,
        }));
    }

    const handleLoginOnClick = async () => {
        try {
            const response = await loginMutation.mutateAsync(inputValue);
            console.log(response)
            const tokenName = response.data.name;
            console.log(response.data.name);
            const accessToken = response.data.token;
            console.log(response.data.token)
            setTokenLocalStorage(tokenName, accessToken);
            await Swal.fire({
                icon: "success",
                text: "로그인 성공",
                timer: 1000,
                position:"center",
                showConfirmButton: false,
            });
            navigate("/admin/main/menu");
            await queryClient.invalidateQueries({queryKey: ["userMeQuery"]});
        } catch(error) {
            // if(error.response.status === 401) {
            //     const result = await Swal.fire({
            //         title: '계정 활성화',
            //         text: '계정을 활성화 하려면 등록하신 메일을 통해 계정 인증을 하세요. 다시 메일 전송이 필요하면 전송버튼을 클릭하세요.',
            //         confirmButtonText: '전송',
            //         confirmButtonColor: "#2389e2",
            //         showCancelButton: true,
            //         cancelButtonText: '취소',
            //         cancelButtonColor: "#999999",
            //     });
            //     if(result.isConfirmed) {
            //         await sendAuthMailMutation.mutateAsync(inputValue.adminName);
            //         await Swal.fire({
            //             title: '메일 전송 완료',
            //             confirmButtonText: '확인',
            //             confirmButtonColor: "#2389e2"
            //         });
            //     }

            // } else {
            //     await Swal.fire({
            //         title: '로그인 실패',
            //         text: '사용자 정보를 다시 확인해주세요.',
            //         confirmButtonText: '확인',
            //         confirmButtonColor: "#e22323"
            //     });
            // }
        }
    }

    return (
        <div css={s.layout}>
                    <div css={s.logoContainer}>
                        <img src="https://blog.kakaocdn.net/dn/w1UK3/btqwTx0mNVX/ki6E4Mva5YavwrOFJQkCP1/img.jpg" alt="" />
                    </div>
                    <div>
                        <h1 css={s.title}>McDonald Admin</h1>
                        <div css={s.groupBox}>
                            <input css={s.textInput} type="text" placeholder='이메일'
                                name="adminName"
                                value={inputValue.adminName}
                                onChange={handleInputOnChange}
                                />
                        </div>
                        <div css={s.groupBox}>
                            <input css={s.textInput} type="password" placeholder='비밀번호'
                                name="adminPassword"
                                value={inputValue.adminPassword}
                                onChange={handleInputOnChange}
                                />
                        </div>
                        <div css={s.footerbox}>
                        <p css={s.accountMessage}>
                            <Link to={"/admin/join"}>회원가입</Link>
                        </p>
                            <button css={s.accountButton} onClick={handleLoginOnClick}>로그인</button>
                        </div>
                        
                    </div>
        </div>
    );
}

export default LoginPage;