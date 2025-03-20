// import React, { useContext } from "react";
// import { HappySnackPriceContext } from "./HappySnackPriceContext";

// 해피스낵 어떻게 정할지 고민중@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

// function HappySnackMenu() {
//     const { prices } = useContext(HappySnackPriceContext);

//     const menuItems = [
//         { name: "아이스 드립 커피 Medium", img: "https://www.mcdonalds.co.kr/upload/product/pcfile/1708663234209.png" },
//         { name: "아이스 드립 커피 Large", img: "https://www.mcdonalds.co.kr/upload/product/pcfile/1708663389665.png" },
//         { name: "한입 초코 츄러스 3조각", img: "https://www.mcdonalds.co.kr/upload/product/pcfile/1736299008239.png" },
//         { name: "슈림프 스낵랩", img: "https://www.mcdonalds.co.kr/upload/product/pcfile/1736300777324.png" },
//         { name: "바닐라 쉐이크 Medium", img: "https://www.mcdonalds.co.kr/upload/product/pcfile/1736299216728.png" },
//         { name: "토마토 치즈 비프 버거", img: "https://www.mcdonalds.co.kr/upload/product/pcfile/1736299355911.png" },
//         { name: "한입 초코 츄러스 라지 커피 콤보", img: "https://www.mcdonalds.co.kr/upload/product/pcfile/1739751908421.png" },
//         { name: "슈림프 스낵랩 라지 커피 콤보", img: "https://www.mcdonalds.co.kr/upload/product/pcfile/1739751835963.png" },
//     ];

//     return (
//         <div>
//             {menuItems.map((happySnack) => (
//                 <div key={happySnack}>
//                     <img src={happySnack.img} alt={happySnack.name} />
//                     <p>{happySnack.name}</p>
//                     <p>{prices[happySnack.name] ? `${prices[happySnack.name]}원` : "가격 없음"}</p>
//                 </div>
//             ))}
//         </div>
//     );
// }

// export default HappySnackMenu;
