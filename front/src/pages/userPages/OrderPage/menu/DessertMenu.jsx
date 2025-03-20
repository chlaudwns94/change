import React, { useEffect } from 'react';
import useMenuData from '../../../../hooks/menu/getMenuHooks';

function DessertMenu({ onMenuItemClick }) {
    // useMenuData 훅을 사용하여 메뉴 데이터를 가져옴
    const { data: menuData, error, isLoading } = useMenuData();

    useEffect(() => {
        if (menuData) {
            console.log("Fetched Menu Data:", menuData);  // 메뉴 데이터 콘솔에 출력
        }
    }, [menuData]);

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (error) {
        console.error("메뉴 데이터를 가져오는 데 실패했습니다:", error);
        return <div>메뉴 데이터를 가져오는 데 실패했습니다.</div>;
    }

    return (
        <div>
            {(menuData || [])
                .filter((dessert) => dessert.menuCategory === "디저트") // "디저트"인 것만 필터링
                .map((dessert) => (
                    <div 
                        key={dessert.menuId} 
                        onClick={() => onMenuItemClick({ // 선택한 메뉴의 name, img, img2, price 등을 전달
                            name: dessert.menuName, 
                            category: dessert.menuCategory,
                            seq: dessert.menuSequence,
                            img: dessert.singleImg, 
                            img2: dessert.setImg,
                            size: dessert.size,
                            price1: dessert.menuPrice[0].menuPrice || 0,
                            price2: dessert.menuPrice.length > 1 ? dessert.menuPrice[1].menuPrice : 0,
                        })}
                        style={{ cursor: 'pointer' }} // 클릭 가능하도록 스타일 추가
                    >
                        <img src={dessert.singleImg} alt={dessert.menuName} />
                        <p>{dessert.menuName}</p>
                        <p>{dessert.menuPrice && dessert.menuPrice[0]?.menuPrice ? `${dessert.menuPrice[0].menuPrice}원` : "가격 없음"}</p>
                    </div>
                ))}
        </div>
    );
}

export default DessertMenu;
