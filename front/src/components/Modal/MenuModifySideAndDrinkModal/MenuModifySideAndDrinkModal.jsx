/**@jsxImportSource @emotion/react */
import * as s from './style';
import { useRecoilState } from 'recoil';
import { addedCart } from '../../../atoms/addedCart/addedCart';
import React, { useState, useEffect } from 'react';
import useMenuData from '../../../hooks/menu/getMenuHooks';


const MenuModifyModal = ({ menu, onClose }) => { // menu, onClose -> OrderPage에서 전달받은 선택한 메뉴 상태
    const [step, setStep] = useState(1);
    const [isSet, setIsSet] = useState(null);
    const [side, setSide] = useState(null);
    const [drink, setDrink] = useState(null);
    const [addedCartState, setAddedCartState] = useRecoilState(addedCart);

    const [isLarge, setIsLarge] = useState(null);

    const { data: menuData, error, isLoading } = useMenuData(); // 메뉴 데이터 및 가격을 훅에서 가져옴 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    console.log("Fetched menu data:", menuData); // 메뉴 데이터 확인

    // 사이드와 음료 데이터만 필터링
    const filteredSides = menuData?.filter(item => item.menuCategory === "사이드");
    const filteredDrinks = menuData?.filter(item => item.menuCategory === "음료");
    console.log("Filtered Sides:", filteredSides); // 사이드 확인
    console.log("Filtered Drinks:", filteredDrinks); // 음료 확인

    // 기본 사이드 및 음료
    const defaultSide = filteredSides?.find((item) => item.menuName === "후렌치 후라이")?.menuName;
    const defaultDrink = filteredDrinks?.find((item) => item.menuName === "코카 콜라")?.menuName;
    console.log("Default side:", defaultSide); // 기본 사이드 확인
    console.log("Default drink:", defaultDrink); // 기본 음료 확인

    // 단계
    const handleNext = () => {
        console.log("Current step:", step); // 현재 단계 확인

        console.log("카테고리 : ", menu.category);
        console.log("메뉴 : ", menu);

        if (step === 1 && (menu.category === "음료" || menu.category === "커피")) {
            setStep(10);
            return;
        }

        if (step === 1) {
            if (isSet === false && (menu.category === "디저트" || menu.category === "사이드" || menu.category === "버거")) {
                handleAddToCart(); 
            }
        }
    
        if (step === 3) {
            handleAddToCart(); // 버거 - 최종적으로 장바구니에 추가
            return;
        }
        if (step === 10) {
            handleAddToCart(); // 음료, 커피 - 최종적으로 장바구니에 추가
            return;
        }
    
        setStep((prev) => prev + 1);
    };    

    // 세트 선택 시 기본값 설정
    const handleIsSetOnClick = (boolean) => {
        console.log("Set chosen:", boolean); // 세트 선택 확인
        setIsSet(boolean);
        if (boolean) {
            setSide(defaultSide);
            setDrink(defaultDrink);
            console.log(`세트, 추가 금액: 2000`);
        } else {
            setSide(null);
            setDrink(null);
            console.log(`단품`);
        }
    };

        // 세트 선택 시 기본값 설정
        const handleIsLargeOnClick = (boolean) => {
            console.log("Large chosen:", boolean); // 라지 선택 확인
            setIsLarge(boolean);
            if (boolean) {
                // setSide(defaultSide);
                // setDrink(defaultDrink);
                console.log(`라지, 추가 금액: 800`);
            } else {
                setSide(null);
                setDrink(null);
                console.log(`미디엄`);
            }
        };

    const handleChangeSideOnClick = (selectedSide) => {
        console.log("Selected side:", selectedSide); // 선택한 사이드 확인
        if (isSet) {
            const newPrice = filteredSides?.find(side => side.menuName === selectedSide)?.menuPrice[0].discountPrice - filteredSides?.find(side => side.menuName === defaultSide)?.menuPrice[0].discountPrice;
            setSide(selectedSide);
            console.log(`선택한 사이드: ${selectedSide}, 추가 금액: ${newPrice}`);
        }
    };

    const handleChangeDrinkOnClick = (selectedDrink) => {
        console.log("Selected drink:", selectedDrink); // 선택한 음료 확인
        if (isSet) {
            const newPrice = filteredDrinks?.find(drink => drink.menuName === selectedDrink)?.menuPrice[0].discountPrice  - filteredDrinks?.find(drink => drink.menuName === defaultDrink)?.menuPrice[0].discountPrice ;
            setDrink(selectedDrink);
            console.log(`선택한 음료: ${selectedDrink}, 추가 금액: ${newPrice}`);
        }
    };

    const handleAddToCart = () => {
        console.log("Menu object before add:", menu); // menu 객체 확인
        const basePrice = isSet ? menu.price1 : isLarge? menu.price2 : menu.price1; // NaN 방지 
        const sidePrice = isSet ? (side !== defaultSide ? filteredSides?.find(temp1 => temp1.menuName === side)?.menuPrice[0].discountPrice : filteredSides?.find(temp1 => temp1.menuName === defaultSide)?.menuPrice[0].discountPrice) : 0;
        const drinkPrice = isSet ? (drink !== defaultDrink ? filteredDrinks?.find(temp2 => temp2.menuName === drink)?.menuPrice[0].discountPrice : filteredDrinks?.find(temp2 => temp2.menuName === defaultDrink)?.menuPrice[0].discountPrice ) : 0;

        console.log("Base price:", basePrice); // 기본 가격 확인
        console.log("Side price:", sidePrice); // 사이드 가격 확인
        console.log("Drink price:", drinkPrice); // 음료 가격 확인

        const orderDetails = {
            detailMenu: menu.name,
            detailSide: isSet ? side : null,
            detailDrink: isSet ? drink : null,
            detailPrice: basePrice + sidePrice + drinkPrice,
            quantity: 1,
            isSet: isSet
        };

        console.log("Order details before adding to cart:", orderDetails); // 장바구니에 추가할 내용 확인

        // 장바구니에 추가
        setAddedCartState((prevCart) => {
            const updatedCart = [...prevCart, orderDetails];
            console.log("Updated cart:", updatedCart); // 장바구니 업데이트 후 확인
            return updatedCart;
        });

        console.log("장바구니에 추가됨:", orderDetails);
        onClose(); // 모달 닫기
    };

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        console.error("메뉴 데이터를 가져오는 데 실패했습니다:", error);
        return <div>메뉴 데이터를 가져오는 데 실패했습니다.</div>;
    }

    return (
        <div css={s.modalOverlay}>
            <div css={s.modalContent}>
                {step === 1 && (
                    <div>
                        <h3 css={s.modalBasich3}>세트(사이즈) 선택</h3>
                        <div css={s.temp}>
                            <div css={s.modalBuguerSetImage}>
                                <div onClick={() => handleIsSetOnClick(false)}>{menu.name}
                                    <img src={menu.img} alt={menu.name} />
                                </div>
                            </div>
                            {menu.category === "버거" && ( // 버거일 때만 세트 옵션 렌더링
                                <div css={s.modalBuguerSetImage}>
                                    <div onClick={() => handleIsSetOnClick(true)}>{menu.name} 세트
                                        <img src={menu.img2} alt={menu.name} />
                                    </div>
                                </div>
                            )}
                        </div>
                        <div css={s.nextAndClose}>
                            <span onClick={handleNext}>다음</span>
                            <span onClick={onClose}>닫기</span>
                        </div>
                    </div>
                )}

                {step === 2 && (
                    <div>
                        <h3 css={s.modalBasich3}>사이드 선택</h3>
                        <div css={s.mapParent}>
                            {filteredSides?.map((side, index) => (
                                <div css={s.childrenDiv} key={`${side.menuName}-${index}`}>
                                    <div css={s.modalSideSetImage}>
                                        <div onClick={() => handleChangeSideOnClick(side.menuName)}>
                                            <img src={side.singleImg} alt={side.menuName} />
                                            <div>
                                                <p>{side.menuName}</p>
                                                <p>{side.menuName === defaultSide ? "+0원" : `+${side.menuPrice[0].discountPrice - filteredSides?.find(side => side.menuName === defaultSide)?.menuPrice[0]?.discountPrice}원`}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                        <div css={s.nextAndClose}>
                            <span onClick={handleNext}>다음</span>
                            <span onClick={onClose}>닫기</span>
                        </div>
                    </div>
                )}

                {step === 3 && isSet == true && (
                    <div>
                        <h3 css={s.modalBasich3}>음료 선택</h3>
                        <div css={s.mapParent}>
                            {filteredDrinks?.map((drink, index) => (
                                <div css={s.childrenDiv} key={`${drink.menuName}-${index}`}>
                                    <div css={s.modalSideSetImage}>
                                        <div onClick={() => handleChangeDrinkOnClick(drink.menuName)}>
                                            <img src={drink.singleImg} alt={drink.menuName} />
                                            <div>
                                                <p>{drink.menuName}</p>
                                                <p>{drink.menuName === defaultDrink ? "+0원" : `+${drink.menuPrice[0].discountPrice - filteredDrinks?.find(drink => drink.menuName === defaultDrink)?.menuPrice[0]?.discountPrice}원`}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                        <div css={s.cartParent}>
                            <button onClick={handleAddToCart} css={s.cart}>카트에 담기</button>
                            <button onClick={onClose} css={s.closeTemp}>닫기</button>
                        </div>
                    </div>
                )}

                {/* 음료와 커피는 세트가 아니라 미디엄 라지임 */}
                {step === 10 && (
                    <div>
                        <h3 css={s.modalBasich3}>사이즈 선택</h3>
                        <div css={s.temp}>
                            <div css={s.modalBuguerSetImage}>
                                <div onClick={() => handleIsLargeOnClick(false)}> 미디엄
                                    <img src={menu.img} alt={menu.name} />
                                </div>
                            </div>
                            {menu.img2 !== null && ( // 버거일 때만 세트 옵션 렌더링
                                <div css={s.modalBuguerSetImage}>
                                    <div onClick={() => handleIsLargeOnClick(true)}> 라지
                                        <img src={menu.img2} alt={menu.name} />
                                    </div>
                                </div>
                            )}
                        </div>
                        <div css={s.nextAndClose}>
                            <span onClick={handleNext}>다음</span>
                            <span onClick={onClose}>닫기</span>
                        </div>
                    </div>
                )}

                {/* 최종적으로 결제하기 전에 추천할 저기 */}
                {step === 100 && isSet === false && (
                    <div>
                        <h3 css={s.modalBasich3}>사이드 추가</h3>
                        <div css={s.mapParent}>
                            {filteredSides?.map((side, index) => (
                                <div css={s.childrenDiv} key={`${side.menuName}-${index}`}>
                                    <div css={s.modalSideSetImage}>
                                        <div onClick={() => handleChangeSideOnClick(side.menuName)}>
                                            <img src={side.singleImg} alt={side.menuName} />
                                            <div>
                                                <p>{side.menuName}</p>
                                                <p>{side.menuPrice[0].menuPrice}원</p> {/* 추가 가격 설정 */}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                        <div css={s.nextAndClose}>
                            <span onClick={handleNext}>다음</span>
                            <span onClick={onClose}>닫기</span>
                        </div>
                    </div>
                )}

                {step === 101 && isSet === false && (
                    <div>
                        <h3 css={s.modalBasich3}>음료 추가</h3>
                        <div css={s.mapParent}>
                            {filteredDrinks?.map((drink, index) => (
                                <div css={s.childrenDiv} key={`${drink.menuName}-${index}`}>
                                    <div css={s.modalSideSetImage}>
                                        <div onClick={() => handleChangeSideOnClick(drink.menuName)}>
                                            <img src={drink.singleImg} alt={drink.menuName} />
                                            <div>
                                                <p>{drink.menuName}</p>
                                                <p>{drink.menuPrice[0].menuPrice}원</p> {/* 추가 가격 설정 */}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                        <div css={s.nextAndClose}>
                            <span onClick={handleNext}>다음</span>
                            <span onClick={onClose}>닫기</span>
                        </div>
                    </div>
                )}
                
            </div>
        </div>
    );
};

export default MenuModifyModal;