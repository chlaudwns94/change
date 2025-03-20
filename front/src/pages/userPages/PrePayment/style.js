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

export const header = css`
    display: flex;
    width: 100%;
    height: 10rem;
    

    & > img {
        display: flex;
        height: 100%;
        width: auto;
    }

    & > p {
        display: flex;
        height: 100%;
        width: 20%;
        justify-content: center;
        align-items: center;
        font-size: 2rem;
        font-weight: 550;
        margin: 0; 
    }
`;

export const main = css`
    display: flex;
    height: 80%;
    width: 100%;
`;

export const footer = css`
    display: flex;
    height: 30%;
    width: 100%;
    border-top: solid black 1.6px;
    background-color:rgb(248, 210, 106);
`;

export const pay = css`
    display: flex;
    width: 100%;
    height: 80%;
    border-top: 0.2rem solid black;
    
    & > div {
        display: flex;
        margin: 0;
        padding: 0;
        width: 100%;
        font-size: 1.1rem;
        overflow-y: auto;

        &::-webkit-scrollbar {
            display: none;
        }
    }
`;

export const xUpDown = css`
    display: flex;
    flex-direction: column;
    /* justify-content: space-between; */
    align-items: center;
    width: 100%;
    /* height: auto; */
    padding: 0.2rem;
    font-size: 1rem;
    font-weight: 550;

    & > div {
        display: flex;
        align-items: center;
        gap: 0.1rem;
    }

    & > div > div {
        display: flex;
        flex-direction: column;
        gap: 0.1rem;
    }

    & button {
        font-size: 0.8rem;
        font-weight: 550;
        justify-content: center;
        align-items: center;
        margin-right: 1px;
    }
`;