import { css } from "@emotion/react";

export const container = css`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
`;

export const logoContainer = css`
    position: absolute;
    top: 20px;
    left: 20px;

    & img {
        width: 20rem;
    }
`;

export const test1 = css`
    display: flex;

`;

export const formWrapper = css`
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    width: 400px;
`;

export const title = css`
    font-size: 5rem;
    font-weight: 700;
    margin-bottom: 20px;
    text-align: center;
`;

export const formContainer = css`
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
    width: 100%;
    
`;

export const inputBox = css`
    width: 100%;
    height: 60px;
    padding: 10px;
    font-size: 1rem;
    border-radius: 12px;
    border: 1px solid #ccc;
    background-color: #f5f5f5;
`;

export const rightContainer = css`
    width: 25rem;
    height: 20rem;
    margin-top: 34.5rem;
`;

export const socialLoginTitle = css`
    font-size: 1.6rem;
    font-weight: bold;
    text-align: center;
    `;

export const socialLoginBox = css`
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
    flex-wrap: wrap;
    
    & > img {
        margin-right: 0.8rem;
        width: 8rem;
        height: 8rem;
        cursor: pointer;
        transition: transform 0.2s ease-in-out;

        &:hover {
            transform: scale(1.1);
        }
    }
`;

export const buttonContainer = css`
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    width: 8rem;
    height: 8rem;
    margin-top: 2rem;  /* OAuth2 버튼과 간격 확보 */
    margin-right: 1rem;
`;

export const button = css`
    display: flex;
    align-items: center;
    justify-content: center;
    width: 8rem;
    height: 3.8rem;
    font-size: 1.5rem;
    font-weight: 600;
    cursor: pointer;
    text-align: center;
    color: white;
    background-color: #007bff;
    border: none;
    border-radius: 0.8rem;
    text-decoration-line: none;

    &:hover {
        background-color: #0056b3;
    }
`;