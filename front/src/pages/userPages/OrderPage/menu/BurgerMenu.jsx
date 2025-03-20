import React, { useEffect } from 'react';
import useMenuData from '../../../../hooks/menu/getMenuHooks';

function BurgerMenu({ onMenuItemClick }) {
    // useMenuData 훅을 사용하여 메뉴 데이터를 가져옴
    const { data: menuData, error, isLoading } = useMenuData();

    useEffect(() => {
        if (menuData) {
            console.log("Fetched Menu Data:", menuData);
        }
    }, [menuData]);


    // useEffect(() => {
    //     if (menuData) {
    //         console.log("111Fetched Menu Data:", menuData);
    //         console.log("222Fetched Menu Data (pretty print):", JSON.stringify(menuData, null, 2));
    
    //         if (Array.isArray(menuData)) {
    //             menuData.forEach((item, index) => {
    //                 console.log(`333Item ${index + 1}:`, JSON.stringify(item, null, 2));
    //             });
    //         } else {
    //             console.log("menuData is not an array:", menuData);
    //         }
    //     }
    // }, [menuData]);
    


    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        console.log("Fetched Menu Data:", menuData);
        // console.log("Fetched Menu Data:", menuData.data);
        console.error("메뉴 데이터를 가져오는 데 실패했습니다:", error);
        return <div>메뉴 데이터를 가져오는 데 실패했습니다.</div>;
    }

    return (
        <div>
            {(menuData || [])
                .filter((burger) => burger.menuCategory === "버거") // "버거"인 것만 필터링
                .map((burger) => (
                    <div 
                        key={burger.menuId} 
                        onClick={() => onMenuItemClick({ // 선택한 메뉴의 name, img, img2, price 등을 전달
                            name: burger.menuName, 
                            category: burger.menuCategory,
                            seq: burger.menuSequence,
                            img: burger.singleImg, 
                            img2: burger.setImg,
                            size: burger.size,
                            price1: burger.menuPrice[0].menuPrice || 0,
                            price2: burger.menuPrice.length > 1 ? burger.menuPrice[1].menuPrice : 0,
                        })}
                        style={{ cursor: 'pointer' }} // 클릭 가능하도록 스타일 추가
                    >
                        <img src={burger.singleImg} alt={burger.menuName} />
                        <p>{burger.menuName}</p>
                        <p>{burger.menuPrice && burger.menuPrice[0]?.menuPrice ? `${burger.menuPrice[0].menuPrice}원` : "가격 없음"}</p>
                    </div>
                ))}
        </div>
    );
}

export default BurgerMenu;