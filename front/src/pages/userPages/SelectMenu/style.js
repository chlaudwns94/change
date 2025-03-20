import { css } from "@emotion/react";

export const container = css`
    display: flex;
    flex-direction: column;
    width: 40rem;
    height: 71.1rem;
    border: 0.2rem solid black;
`;

export const smImage = css`
    display: flex;
    width: 100%;
    height: 37%;
    overflow: hidden;
    justify-content: center;
`;

export const imageStyle = css`
    display: flex;
    width: 100%;
    height: 100%;
    object-fit: cover; 
    transition: opacity 1s ease-in-out;
`;

export const smChoice = css`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    font-size: 1rem;
`;

export const smLanguage = css`
    display: flex;
    font-size: 2rem;
    width: 100%;
    height: 7rem;
    justify-content: space-evenly;
    margin-bottom: 4rem;
    
    & > span {
        display: flex;
        justify-content: center;
        align-items: center;
        border: 0.2rem solid black;
        border-radius: 2rem;
        width: 12rem;
        font-size: 2rem;
        font-weight: 800;
    }
`;

export const selectedLanguage = css`
    background-color:#ffd154;
    box-shadow: 0.5rem 0.5rem 0.5rem rgba(0,0,0,0.25); 
`;

export const smHow = css`
    position: relative;
    display: flex;
    font-size: 2rem;
    width: 100%;
    height: 15rem;
    justify-content: space-evenly;


    & > span {
        display: flex;
        border: 0.2rem solid black;
        border-radius: 1rem;
        width: 17rem;
        height: 17rem;
        box-shadow: 0.5rem 0.5rem 0.5rem rgba(0,0,0,0.25);
        justify-content: center;
        align-items: center;

        & > img {
            display: flex;
            width: 80%;
            height: 80%;
        }
    }
`;