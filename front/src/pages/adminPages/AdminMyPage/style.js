import { css } from "@emotion/react";

export const container = css`
    display: flex;
    flex-direction: column;
    width: 100%;
`;


export const title = css`
    box-sizing: border-box;
    margin: 0;
    margin-bottom: 2rem;
    border-bottom: 0.1rem solid #dbdbdb;
    padding: 2rem 4rem;
    font-size: 3rem;
    font-weight: 600;
`;

export const infoContainer = css`
    display: flex;
    flex-direction: column;
`;

export const infoBox = css`
    box-sizing: border-box;
    margin-bottom: 1rem;
    width: 100%;

    & > h3 {
        font-size: 2rem;
        font-weight: 600;
    }
`;

export const inputBox =css`
    height: 5rem;
    width: 40rem;
`;

export const accountBox = css`
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 5rem;
`;

export const tradeNameBox = css`
    margin: 0 0 0.7rem;
    font-size: 1.4rem;
    font-weight: 400;
    cursor: default;
`;

export const subContent = css`
    margin: 0.5rem 0;
    font-size: 2rem;
    font-weight: 500;
    color: #777777;
`;

export const borderButton = css`
    box-sizing: border-box;
    border: 0.1rem solid #dbdbdb;
    border-radius: 0.5rem;
    padding: 0.7rem 2rem;
    background-color: #ffffff;
    cursor: pointer;

    &:hover {
        background-color: #fafafa;
    }

    &:active {
        background-color: #eeeeee;
    }
`;