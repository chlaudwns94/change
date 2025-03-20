import { useQuery } from "@tanstack/react-query";
import { getAllMenuListApi, getCategoriesApi } from "../../apis/AdminApi/AdminMenuBoardApi";

//카테고리 불러오기
export const useGetCategories = () => useQuery({
    queryKey: ["useGetCategories"],
    queryFn: getCategoriesApi,
    retry: 0,
    staleTime: 1000 * 60 * 10,
    gcTime: 1000 * 60 * 5
});

//모든 메뉴 불러오기
export const useAllMenuList = () => useQuery({
    queryKey: ["useAllMenuList"],
    queryFn: getAllMenuListApi,
    retry: 0,
    staleTime: 1000 * 60 * 10,
    gcTime: 1000 * 60 * 5
});


// //페이지 수 정보
// export const useMenuListByCategory = (params) => useQuery({
//     queryKey: ["useMenuListByCategory"], //["useCountMenuListByCategory", params]
//     queryFn: async () => {
//         console.log("요청!")
//         return await getMenuListByCategoryApi(params);
//     },
//     retry: 0,
//     staleTime: 1000 * 60 * 10,
//     gcTime: 1000 * 60 * 5
// });

