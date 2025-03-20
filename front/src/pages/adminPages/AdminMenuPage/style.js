import { css } from "@emotion/react";

export const container = css`
    display: flex;
    flex-direction: column;
    width: 100%;
`;

export const header = css`
    display: flex;
    justify-content: space-between;
    padding: 2rem 4rem;
    

    & > span {
        display: flex;
        justify-content: start;
        align-items: center;
        flex-grow: 4;
        min-width: 12rem;
        font-size: 3rem;
        font-weight: 600;
    }
    & > div {
        display: flex;
        justify-content: space-around;
        align-items: center;
        flex-grow: 1;
        min-width: 12rem;
    }
`;

export const buttons = css`
    padding: 0.4rem 1.5rem;
    box-sizing: border-box;
    border: 0.2rem solid #77ac58;
    border-radius: 0.5rem;
    font-size: 2.3rem;
    font-weight: 600;
    cursor: pointer;

    &:hover {
        background-color: #00000011;
    }

    &:active {
        background-color: #00000033;
    }
`;

export const menuListContainer = css`
    & li {
        display: flex;
        justify-content: space-between;

        
        & div {
            text-align: center;
            min-width: 3rem;
        }
    }
`;

export const pageNum = (isSelect) => css`
 background-color: ${isSelect ? "#eeeeee" : "#ffffff"} !important;
`;

export const footer = css`

`;