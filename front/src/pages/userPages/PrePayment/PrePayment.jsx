import React from 'react';
/**@jsxImportSource @emotion/react */
import * as s from './style';
import { useRecoilState } from 'recoil';
import { addedCart } from '../../../atoms/addedCart/addedCart';

function PrePayment(props) {
    // 장바구니
    const [addedCartState, setAddedCartState] = useRecoilState(addedCart);
    
    const handleRemoveFromCart = (index) => {
        setAddedCartState(prevCart => {
            const updatedCart = prevCart.filter((_, i) => i !== index);
            console.log("Updated Cart after Removal:", updatedCart);
            return updatedCart;
        });
    };

    const handleUpFromCart = (index) => {
        setAddedCartState(prevCart => {
            const updatedCart = prevCart.map((item, i) =>
                i === index ? { ...item, quantity: item.quantity + 1 } : item
            );
            console.log("Updated Cart after Increment:", updatedCart[0]);
            return updatedCart;
        });
    };

    const handleDownFromCart = (index) => {
        setAddedCartState(prevCart => {
            const updatedCart = prevCart.map((item, i) =>
                i === index && item.quantity > 1
                    ? { ...item, quantity: item.quantity - 1 }
                    : item
            );
            console.log("Updated Cart after Decrement:", updatedCart[0]);
            return updatedCart;
        });
    };



    return (
        <div css={s.container}>
            <header css={s.header}>
                <img src="https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/ec545603-cf4e-48e0-936d-5376ea12fdc0/dh1vv57-b11b6232-616f-4266-8bb2-388aa1f1c548.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2VjNTQ1NjAzLWNmNGUtNDhlMC05MzZkLTUzNzZlYTEyZmRjMFwvZGgxdnY1Ny1iMTFiNjIzMi02MTZmLTQyNjYtOGJiMi0zODhhYTFmMWM1NDgucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.RNXDS0nBItT7WkcJDYigGSDUyjQXJhkqwM8CSB-keHY" alt="" />
                <p>주 문</p>
            </header>
            <main css={s.pay}>
                {addedCartState.map((item, index) => (
                    <div css={s.xUpDown} key={index}>
                        <div>
                            <li>
                                {item.detailMenu} 
                                <span style={{ marginLeft: "auto" }}>
                                    {item.isSet && " 세트"}
                                </span>
                                - {item.detailPrice}원
                            </li>   
                        </div>
                        <div>
                            <span>
                                <button onClick={() => handleRemoveFromCart(index)}>삭제</button>
                            </span>
                            <div>
                                <button onClick={() => handleUpFromCart(index)}>▲</button>
                                <button onClick={() => handleDownFromCart(index)}>▼</button>
                            </div>
                        </div>
                    </div>
                ))}
            </main>
            <footer css={s.footer}>

            </footer>
        </div>
    );
}

export default PrePayment;