import { css } from "@emotion/react";

export const modalContainer = css`
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* 배경을 반투명하게 */
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 9999; /* 다른 요소 위에 나타나게 하기 */
`;

export const modalContent = css`
    background-color: white;
    padding: 2rem;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    text-align: center;
    width: 30rem;
    height: 15rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;

export const modalText = css`
    font-size: 1.5rem;
    margin-bottom: 1rem;
`;

export const closeButton = css`
    padding: 0.5rem 2rem;
    background-color: #ffd154;
    color: black;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1.5rem;
    margin-top: 3rem;
    
    &:hover {
        /* background-color: #ff2a2f; */
    }
`;

