import { api } from "../../configs/axiosConfig";

export const getCategoriesApi = async () => await api.get(`/api/admin/categories`);
export const getAllMenuListApi = async () => await api.get(`/api/admin/menus`);
//export const getMenuListByCategoryApi = async (params) => await api.get("/api/admin/list", {params});

