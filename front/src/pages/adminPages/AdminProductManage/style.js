import { css } from "@emotion/react";

// 전체 컨테이너
export const container = css`
    margin: 2rem auto;
    background: #d1d5db;
    padding: 1.5rem;
    border-radius: 10px;
`;

// 선택
export const dropdownContainer = css`
    margin-bottom: 1rem;
`;

export const dropdown = css`
    width: 250px;
    padding: 0.5rem;
    border: 1px solid #ccc;
    border-radius: 6px;
`;

// 상품 정보 컨테이너
export const productContainer = css`
    display: flex;
    gap: 1.5rem;
`;

// 이미지 영역
export const imageCon = css`
    display: flex;
    flex-direction: column;
`;

export const imageBox = css`
    width: 30rem;
    height: 30rem;
    background: white;
    border: 1px solid #ccc;
    display: flex;
    align-items: center;
    justify-content: center;
    overflow: hidden;
    cursor: pointer;

    & > img {
        width: 100%;
    }
`;

// 입력 필드 그룹
export const inputGroup = css`
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 3rem;
    width: 80rem;
    background-color: lightblue;
    & > div:nth-last-of-type() {
        display: block;
    }

    & > div {
        width: 70rem;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
`;

export const label = css`
    font-size: 3rem;
    font-weight: bold;
    margin-bottom: 0.2rem;
`;

export const input = css`
    width: 60%;
    height: 4.5rem;
    padding: 0 3rem;
    border: 1px solid #ccc;
    border-radius: 6px;
    box-sizing: border-box;
`;

export const textArea = css`
    width: 60%;
    height: 15rem;
    padding: 1.25rem 3rem;
    border: 1px solid #ccc;
    border-radius: 6px;
    box-sizing: border-box;
    resize: none;
`;

// 버튼 그룹
export const buttonGroup = css`
    margin-top: 1rem;
    display: flex;
    justify-content: flex-end;
`;

export const button = css`
    padding: 0.5rem 1rem;
    margin-left: 2rem;
    color: black;
    border: 1px solid #000;
    border-radius: 6px;
    font-weight: bold;
    cursor: pointer;
    flex-grow: 1;

    &:hover {
    background: #e5e7eb;
}
`;
