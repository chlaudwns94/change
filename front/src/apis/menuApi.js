import { api } from "../configs/axiosConfig";

// 메뉴 데이터를 가져오는 함수
export const fetchMenuData = async () => {
    const response = await api.get("/user/menu");

    // 받아온 데이터 가공 (menuPrice를 size 기준으로 정렬)
    const sortedData = response.data.map((item) => ({
        ...item,
        menuPrice: item.menuPrice.sort((a, b) => {
            const sizeOrder = ["M", "L"];  // 원하는 size 순서 정의
            return sizeOrder.indexOf(a.size) - sizeOrder.indexOf(b.size);  // M이 L보다 먼저 오도록 정렬
        })
    }));

    return sortedData;
};


// 특정 메뉴 가져오기
export const fetchMenuDetail = async (menuId) => {
  const response = await api.get(`/user/menu/${menuId}`);
  return response.data;
};

// 메뉴 추가
export const addMenuData = async (formData) => {
  const token = localStorage.getItem("accessToken"); // JWT 토큰 가져오기

  const data = new FormData();
  data.append("menuName", formData.menuName);
  data.append("menuCategory", formData.menuCategory);
  data.append("menuSequence", formData.menuSequence);
  data.append("isExposure", formData.isExposure);
  data.append("prices", JSON.stringify(formData.prices));

  if (formData.singleImg) data.append("singleImg", formData.singleImg);
  if (formData.setImg) data.append("setImg", formData.setImg);

  // 🚀 JWT 토큰을 헤더에 추가
  const response = await api.post("/admin/menu", data, {
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: `Bearer ${token}`, // ✅ JWT 토큰 추가
    },
  });

  return response.data;
};

// 메뉴 삭제
export const deleteMenuData = async (menuId) => {
  const response = await api.delete(`/admin/menu/${menuId}`);
  return response.data;
};


/*
[
333Item 24: {
  "menuId": 24,
  "menuName": "베이컨 토마토 에그 머핀",
  "menuCategory": "맥모닝",
  "menuSequence": 5,
  "singleImg": "https://www.mcdonalds.co.kr/upload/product/pcList/1646207398160.png",
  "setImg": "https://www.mcdonalds.co.kr/upload/product/pcList/1677677491958.png",
  "isExposure": 1,
  "menuPrice": [
    {
      "menuPriceId": 0,
      "menuId": 24,
      "size": "L",
      "menuPrice": 5700,
      "discountPrice": 0
    },
    {
      "menuPriceId": 0,
      "menuId": 24,
      "size": "M",
      "menuPrice": 3700,
      "discountPrice": 0
    }
  ]
}
  ...
]
*/