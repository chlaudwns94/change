/**@jsxImportSource @emotion/react */
import * as s from './style';
import React from 'react';

function MainContainer({children}) {

    return (
        <div css={s.container}>
            {children}
        </div>
    );
}

export default MainContainer;