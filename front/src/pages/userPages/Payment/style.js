import { css } from "@emotion/react";

export const container = css`
    display: flex;
    flex-direction: column;
    width: 40rem;
    height: 71.1rem;
    border: 0.2rem solid black;
    background-color: #EEEEEE;
    justify-content: center;
    align-items: center;
`;

export const main = css`
    display: flex;
    height: 80%;
    width: 70%;
`;

export const method = css`
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;

    & > div {
        display: flex;
        height: 10rem;
        background-color: white;
        margin-top: 1rem;
        border: solid black 1px;
        box-shadow: 0.2rem 0.2rem 0.2rem rgba(0,0,0,0.25); 

        & > img {
            display: flex;
            height: 100%;
            width: auto;
        }

        & > div {
            display: flex;
            flex-direction: column;
            width: 100%;
            justify-content: center;
            align-items: center;

            & > p {
                display: flex;
                margin: 0;
                font-size: 2rem;
            }
        }
    }
`;

export const footer = css`
    display: flex;
    width: 40%;
    height: 6rem;
    background-color: #ffd154;
    border-radius: 1rem;
    justify-content: center;
    align-items: center;
    font-size: 2rem;
`;