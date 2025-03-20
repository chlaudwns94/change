/**@jsxImportSource @emotion/react */
import ReactModal from 'react-modal';
import { useUpdateNicknameMutation } from '../../../mutations/accountMutation';
import { useUserMeQuery } from '../../../queries/userQuery';
import * as s from './style';
import React, { useEffect, useState } from 'react';
import PasswordModal from '../../../components/Modal/AuthModal/PasswordModal/PasswordModal';
import ChangeEmailModal from '../../../components/Modal/AuthModal/ChangeEmailModal/ChangeEmailModal';

function AdminMyPage(props) {

    const loginUser = useUserMeQuery();
    const [ passwordModalOpen, setPasswordModalOpen ] = useState(false);
    const [ emailModalOpen, setEmailModalOpen ] = useState(false);
    const updateNicknameMutation = useUpdateNicknameMutation();

    const [ tradeNameValue, setTradeNameValue ] = useState("");

    useEffect(() => {
        setTradeNameValue(loginUser?.data?.data.tradeName || "");
    }, [loginUser.isFetched]);

    const handleNicknameInputOnChange = (e) => {
        setTradeNameValue(e.target.value);
    }

    const handleSaveNicknameButtonOnClick = async () => {
        await updateNicknameMutation.mutateAsync(tradeNameValue);
        loginUser.refetch();
    }

    const handleChangePasswordButtonOnClick = () => {
        setPasswordModalOpen(true);
    }

    const handleChangeEmailButtonOnClick = () => {
        setEmailModalOpen(true);
    }


    return (
        <div css={s.container}>
            <h2 css={s.title}>내정보</h2>
            <div css={s.accountBox}>
                <div css={s.infoContainer}>
                    <div css={s.infoBox}>
                        <h3>관리자 아이디</h3>
                        <p css={s.subContent}>{loginUser?.data?.data.adminName}</p>
                    </div>
                    <div css={s.infoBox}>
                        <h3>매장 이름</h3>
                        <p css={s.subContent}>{loginUser?.data?.data.tradeName}</p>
                        <div>
                            <input css={s.textInput} type="text" value={tradeNameValue} onChange={handleNicknameInputOnChange} />
                        </div>
                        <button css={s.saveButton} onClick={handleSaveNicknameButtonOnClick} disabled={loginUser?.data?.data.tradeName === tradeNameValue} >변경하기</button>
                    </div>
                    <div css={s.infoBox}>
                        <h3>이메일</h3>
                        <p css={s.subContent}>{loginUser?.data?.data.email}</p>
                        <button css={s.borderButton} onClick={handleChangeEmailButtonOnClick}>Change email</button>
                    </div>
                    {
                    !!loginUser?.data?.data?.oauth2Name ||
                    <div css={s.itemGroup}>
                        <div css={s.infoBox}>
                            <h3>비밀번호</h3>
                            <p css={s.subContent}>계정에 로그인할 영구 비밀번호를 설정합니다.</p>
                        </div>
                        <button css={s.borderButton} onClick={handleChangePasswordButtonOnClick}>Change password</button>
                    </div>
                    }

                </div>
            </div>
            <ReactModal 
                isOpen={emailModalOpen}
                onRequestClose={() => setEmailModalOpen(false)}
                style={{
                    overlay: {
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        backgroundColor: "#00000066"
                    },
                    content: {
                        position: "static",
                        boxSizing: "border-box",
                        borderRadius: "1.5rem",
                        width: "37rem",
                    }
                }}
                children={<ChangeEmailModal setOpen={setEmailModalOpen} />}
            />
            <ReactModal 
                isOpen={passwordModalOpen}
                onRequestClose={() => setPasswordModalOpen(false)}
                style={{
                    overlay: {
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        backgroundColor: "#00000066"
                    },
                    content: {
                        position: "static",
                        boxSizing: "border-box",
                        borderRadius: "1.5rem",
                        width: "37rem",
                    }
                }}
                children={<PasswordModal setOpen={setPasswordModalOpen} />}
            />
        </div>
    );
}

export default AdminMyPage;