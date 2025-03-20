/**@jsxImportSource @emotion/react */
import { useNavigate } from 'react-router-dom';
import BurgerMenu from './menu/BurgerMenu';
import DrinkMenu from './menu/DrinkMenu';
// import HappySnackMenu from './menu/HappySnackMenu';
// import RecommendMenu from './menu/RecommendMenu';
import SideMenu from './menu/SideMenu';
import * as s from './style';
import React, { useState, useEffect } from 'react';
import CoffeeMenu from './menu/CoffeeMenu';
import CallManagerModal from '../../../components/Modal/CallManagerModal/CallManagerModal';
import MenuDetailModal from '../../../components/Modal/MenuDetailModal/MenuDetailModal';
import MenuModifySideAndDrinkModal from '../../../components/Modal/MenuModifySideAndDrinkModal/MenuModifySideAndDrinkModal';
import DessertMenu from './menu/DessertMenu';
import { addedCart } from '../../../atoms/addedCart/addedCart';
import { useRecoilState } from 'recoil';

// 보류@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

function OrderPage(props) {
    const navi = useNavigate();

    // 선택된 카테고리 상태 추가
    const [selectedCategory, setSelectedCategory] = useState("버거");

    // 장바구니
    const [addedCartState, setAddedCartState] = useRecoilState(addedCart);

    // 세트메뉴의 사이드와 음료 수정 모달
    const [editingItem, setEditingItem] = useState(null);

    const handleMenuCategoryOnClick = (category) => {
        if (selectedCategory !== category) {
            setSelectedCategory(category);
        }
    }
    
    const handleBackMenuOnClick = () => {
        navi("/menu");
    }

    // 선택된 메뉴 정보를 저장할 상태
    const [selectedMenu, setSelectedMenu] = useState(null);

    const handleMenuItemClick = (menu) => {
        setSelectedMenu(menu); // 메뉴 클릭 시 모달에 정보를 전달
    }

    const handleCloseMenuDetailModal = () => {
        setSelectedMenu(null); // 모달 닫기
    }

    // 삭제할 아이템의 index로 .filter()를 사용하여 해당 index가 아닌 요소만 남김
    const handleRemoveFromCart = (index) => {
        setAddedCartState(prevCart => {
            const updatedCart = prevCart.filter((_, i) => i !== index);
            console.log("Updated Cart after Removal:", updatedCart);
            return updatedCart;
        });
    };

    const handleModifyFromCart = (index) => {
        setEditingItem({ ...addedCartState[index], index });
    };

    const handleSaveModifiedItem = (updatedItem) => {
        setAddedCartState(prevCart => 
            prevCart.map((item, i) => (i === updatedItem.index ? updatedItem : item))
        );
        setEditingItem(null);
    };

    // quantity 를 1씩 증가
    const handleUpFromCart = (index) => {
        setAddedCartState(prevCart => {
            const updatedCart = prevCart.map((item, i) =>
                i === index ? { ...item, quantity: item.quantity + 1 } : item
            );
            console.log("Updated Cart after Increment:", updatedCart[0]);
            return updatedCart;
        });
    };

    // quantity 를 1씩 감소 (최소값 1 유지)
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

    // menu_id -> 갯수만 있으면 백엔드에서 계산해서 db에 넣는다
    // 장바구니 결제전에 이 2가지로 계싼
    

    return (
        <div css={s.container}>
            <header css={s.logoAnd2button}>
                <div css={s.mcdonaldLogo}>
                    <img src="https://pngimg.com/uploads/mcdonalds/mcdonalds_PNG17.png" alt="" />
                </div>
                <div css={s.buttons}>
                    <div onClick={handleBackMenuOnClick}>처음으로</div>
                    <CallManagerModal />
                </div>
            </header>

            <main css={s.body}>
                <div css={s.category}>
                    {/* <div onClick={() => handleMenuCategoryOnClick("추천메뉴")}>🔥 추천메뉴</div> */}
                    <div onClick={() => handleMenuCategoryOnClick("버거")}>🍔 버거</div>
                    {/* <div onClick={() => handleMenuCategoryOnClick("해피스낵")}>🍟 해피스낵</div> */}
                    <div onClick={() => handleMenuCategoryOnClick("사이드")}>🍗 사이드</div>
                    <div onClick={() => handleMenuCategoryOnClick("음료")}>🥤 음료</div>
                    <div onClick={() => handleMenuCategoryOnClick("커피")}>☕ 커피</div>
                    <div onClick={() => handleMenuCategoryOnClick("디저트")}>🍦 디저트</div>
                    <div>테스트 2</div>
                    <div>테스트 3</div>
                </div>
                <div css={s.menu}>
                    {/* 선택된 카테고리에 따라 메뉴를 렌더링하고, 각 메뉴 항목 클릭 시 handleMenuItemClick 호출 */}
                    {/* {selectedCategory === "추천메뉴" && <RecommendMenu onMenuItemClick={handleMenuItemClick} />} */}
                    {selectedCategory === "버거" && <BurgerMenu onMenuItemClick={handleMenuItemClick} />}
                    {/* {selectedCategory === "해피스낵" && <HappySnackMenu onMenuItemClick={handleMenuItemClick} />} */}
                    {selectedCategory === "사이드" && <SideMenu onMenuItemClick={handleMenuItemClick} />}
                    {selectedCategory === "음료" && <DrinkMenu onMenuItemClick={handleMenuItemClick} />}
                    {selectedCategory === "커피" && <CoffeeMenu onMenuItemClick={handleMenuItemClick} />}
                    {selectedCategory === "디저트" && <DessertMenu onMenuItemClick={handleMenuItemClick} />}
                </div>
            </main>

            <footer css={s.pay}>
                <div>
                    {addedCartState.length > 0 ? (
                        <ul>
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
                                        {/* 수정 버튼은 isSet이 true이거나 실제 메뉴 이름이 일치하는 경우에만 보임 */}
                                        {(item.isSet) && (
                                            <button onClick={() => handleModifyFromCart(index)}>수정</button>
                                        )}
                                            <button onClick={() => handleRemoveFromCart(index)}>삭제</button>
                                        </span>
                                        <div>
                                            <button onClick={() => handleUpFromCart(index)}>▲</button>
                                            <button onClick={() => handleDownFromCart(index)}>▼</button>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </ul>
                    ) : (
                        <p>장바구니에 아무것도 없습니다.</p>
                    )}
                </div>
                <span>
                    <p>주문하기</p>
                    <p>마일리지 조회</p>
                </span>
            </footer>

            {/* 선택된 메뉴가 있을 경우 모달을 띄운다 */}
            {selectedMenu && <MenuDetailModal menu={selectedMenu} onClose={handleCloseMenuDetailModal} />}
            {editingItem && <MenuModifySideAndDrinkModal menu={editingItem} onClose={() => setEditingItem(null)} onSave={handleSaveModifiedItem} />}
        </div>
    );
}

export default OrderPage;