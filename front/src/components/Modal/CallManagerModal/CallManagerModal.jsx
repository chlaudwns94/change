/** @jsxImportSource @emotion/react */
import React, { useState } from 'react';
import * as s from './style';

// 기능은 구현되어 있지 않고, 열리는 modal 창만 있음.

function CallManagerModal() {
    const [isModalOpen, setIsModalOpen] = useState(false);

    const openModal = () => {
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
    };

    return (
        <div>
            <div onClick={openModal}>직원호출</div>
            {isModalOpen && (
                <div css={s.modalContainer}>
                    <div css={s.modalContent}>
                        <span css={s.modalText}>직원을 호출하였습니다.</span>
                        <span css={s.modalText}>잠시만 기다려주세요.</span>
                        <button onClick={closeModal} css={s.closeButton}>확인</button>
                    </div>
                </div>
            )}
        </div>
    );
}

export default CallManagerModal;
