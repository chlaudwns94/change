import { css } from "@emotion/react";

export const layout = css`
    display: flex;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
    width: 100%;
    height: 100%;
`;

export const logoContainer = css`
    position: absolute;
    top: 20px;
    left: 20px;

    & img {
        width: 20rem;
    }
`;

export const title = css`
    font-size: 5rem;
    font-weight: 700;
    margin-bottom: 20px;
    text-align: center;
`;

export const groupBox = css`
    box-sizing: border-box;
    display: flex;
    padding: 0.6rem 0;
`;

export const textInput = css`
    box-sizing: border-box;
    outline: none;
    margin-bottom: 1rem;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.5rem;
    padding: 0.5rem 5rem;
    width: 50rem;
    height: 8rem;
    background-color: #dbdbdb;

    font-size: 1.4rem;
    font-weight: 800;
    letter-spacing: 0.1rem;

    &:focus {
        box-shadow: 0rem 0rem 0.2rem 0.2rem #7edaff;
    }
`;

export const accountMessage = css`
    font-size: 2rem;
    color: #777777;
    font-weight: 700;
    cursor: default;
`;

export const accountButton = css`
    box-sizing: border-box;
    border: none;
    border-radius: 0.5rem;
    font-size: 2rem;
    font-weight: 700;
    background-color: #ffffff;
    letter-spacing: 0.1rem;
    cursor: pointer;
`;

export const footerbox = css`
    display: flex;
    flex-direction: row;
    justify-content: space-around;
`;

