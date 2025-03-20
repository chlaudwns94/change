import { css } from "@emotion/react";

export const mainContainer = css`
    display: flex;
    justify-content: center; 
    align-items: center; 
    height: 100%; 
    width: 100%; 
    margin: 0;
    padding: 0;
`;

export const mainLayout = css`
    text-align: center;
    padding: 20px;
    border-radius: 10px;
    `;

export const conBox = css`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
    position: relative;
    

    
`;

export const mainCon = css`
    display: flex;
    justify-content: space-end;

    & > div {
        margin-top: 5rem;
        width: 33rem;
        height: 33rem;
        background-color: #47a8af;
        border-radius: 3rem;
        cursor: pointer;
        font-size: 5rem;
        text-align: center;
        
        &:hover {
            text-decoration: underline;
        }

        & > svg {
            margin-top: 10rem;
            
            & > path {
                font-size: 3rem;
            }
        }

        & > h5 {
            margin-top: 0;
        }
    }
    
    & > div:nth-of-type(2) {
        margin-right: 15rem;
        margin-left: 15rem;
        background-color: #a837aa;
    }

    & > div:nth-of-type(3) {
        background-color: #42df34;
    }
`;