import { useMutation } from "@tanstack/react-query";
import { joinApi, loginApi } from "../apis/authApi";

export const useJoinMutation = () => useMutation({
    mutationKey: ["joinMutation"],
    mutationFn: joinApi,
    retry: 0,
});

export const useLoginMutation = () => useMutation({
    mutationKey: ["loginMutation"],
    mutationFn: loginApi,
    retry: 0,
});

// 메뉴 가져오기
export const useMenuMutation = () => useMutation({
    mutationKey: ["menuMutation"],
    mutationFn: fetchMenuData,
    retry: 0,
});

// 특정 메뉴 가져오기
export const useMenuDetailMutation = () => useMutation({
    mutationKey: ["menuDetailMutation"],
    mutationFn: fetchMenuDetail,
    retry: 0,
});

// 메뉴 추가
export const useAddMenuMutation = () => useMutation({
    mutationKey: ["addMenuMutation"],
    mutationFn: addMenuData,
    retry: 0,
});

// 메뉴 삭제
export const useDeleteMenuMutation = () => useMutation({
    mutationKey: ["deleteMenuMutation"],
    mutationFn: deleteMenuData,
    retry: 0,
});