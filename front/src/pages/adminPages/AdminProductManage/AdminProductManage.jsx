/**@jsxImportSource @emotion/react */
import { useEffect, useState } from "react";
import * as s from "./style";
import { Checkbox } from "@mui/material";
import { 
    useMenuMutation, 
    useMenuDetailMutation, 
    useAddMenuMutation, 
    useDeleteMenuMutation 
} from "../../../mutations/authMutation";

function App() {
    const [menus, setMenus] = useState([]);
    const [selectedMenu, setSelectedMenu] = useState(null);
    const [formData, setFormData] = useState({
        menuName: "",
        menuCategory: "",
        menuSequence: "",
        isExposure: 1,
        singleImg: null,
        setImg: null,
        prices: [
            { size: "M", price: "", discountPrice: "" },
            { size: "L", price: "", discountPrice: "" }
        ],
    });

    const menuMutation = useMenuMutation();
    const menuDetailMutation = useMenuDetailMutation();
    const addMenuMutation = useAddMenuMutation();
    const deleteMenuMutation = useDeleteMenuMutation();

    useEffect(() => {
        fetchMenus();
    }, []);

    // 메뉴 리스트 불러오기
    const fetchMenus = async () => {
        try {
            const data = await menuMutation.mutateAsync();
            setMenus(data);
        } catch (error) {
            console.error("메뉴 불러오기 실패:", error);
        }
    };

    // 선택한 메뉴 정보 불러오기
    const handleMenuSelect = async (e) => {
        const menuId = e.target.value;
        setSelectedMenu(menuId);

        if (!menuId) return;

        try {
            const menuData = await menuDetailMutation.mutateAsync(menuId);
            setFormData({
                menuName: menuData.menuName,
                menuCategory: menuData.menuCategory,
                menuSequence: menuData.menuSequence,
                isExposure: menuData.isExposure,
                singleImg: menuData.singleImg,
                setImg: menuData.setImg,
                prices: menuData.menuPrices.map(price => ({
                    size: price.size,
                    price: price.menuPrice,
                    discountPrice: price.discountPrice || ""
                }))
            });
        } catch (error) {
            console.error("메뉴 정보 불러오기 실패:", error);
        }
    };

    // input 값 변경 처리
    const handleInputValueOnChange = (e) => {
        const { name, value, type, checked } = e.target;

        setFormData((prev) => {
            if (type === "checkbox") {
                return { ...prev, [name]: checked ? 1 : 0 };
            }

            if (name === "singlePrice" || name === "setPrice") {
                return {
                    ...prev,
                    prices: prev.prices.map((p) =>
                        p.size === (name === "singlePrice" ? "M" : "L") ? { ...p, price: value } : p
                    ),
                };
            }

            return { ...prev, [name]: value };
        });
    };

    // 이미지 업로드 
        const handleImageUpload = (e, type) => {
        const file = e.target.files[0];
        setFormData(prev => ({
            ...prev,
            [type === "single" ? "singleImg" : "setImg"]: file
        }));
    };

    // 메뉴 추가
    const handleSubmitMenuOnClick = async () => {
        try {
            await addMenuMutation.mutateAsync(formData);
            await fetchMenus();
            alert("메뉴가 추가되었습니다.");
        } catch (error) {
            console.error("메뉴 추가 실패:", error);
        }
    };

    // 메뉴 삭제
    const handleDeleteMenuOnClick = async () => {
        if (!selectedMenu) return alert("삭제할 메뉴를 선택하세요.");

        try {
            await deleteMenuMutation.mutateAsync(selectedMenu);
            await fetchMenus();
            alert("메뉴가 삭제되었습니다.");
        } catch (error) {
            console.error("메뉴 삭제 실패:", error);
        }
    };

    return (
        <div css={s.container}>
            <div css={s.dropdownContainer}>
            <select onChange={handleMenuSelect} css={s.dropdown}>
                <option value="">메뉴를 선택해주세요</option>
                {menus && menus.length > 0 ? (
                    menus.map((menu) => (
                        <option key={menu?.menuId} value={menu?.menuId}>
                            {menu?.menuName}
                        </option>
                    ))
                ) : (
                    <option value="">메뉴가 없습니다</option>
                )}
            </select>
            </div>

            <div css={s.productContainer}>
                <div css={s.imageCon}>
                    <label css={s.imageBox}>
                        <input type="file" onChange={(e) => handleImageUpload(e, "single")} hidden />
                        {formData.singleImg ? (
                            <img src={URL.createObjectURL(formData.singleImg)} alt="Single" />
                        ) : (
                            <span>단품 또는 M사이즈</span>
                        )}
                    </label>
                    <label css={s.imageBox}>
                        <input type="file" onChange={(e) => handleImageUpload(e, "set")} hidden />
                        {formData.setImg ? (
                            <img src={URL.createObjectURL(formData.setImg)} alt="Set" />
                        ) : (
                            <span>세트 또는 L사이즈</span>
                        )}
                    </label>
                </div>

                <div css={s.inputGroup}>
                    <div>
                        <label css={s.label}>상품명</label>
                        <input type="text" name="menuName" value={formData.menuName} onChange={handleInputValueOnChange} css={s.input} placeholder="상품명" />
                    </div>
                    <div>
                        <label css={s.label}>카테고리</label>
                        <input type="text" name="menuCategory" value={formData.menuCategory} onChange={handleInputValueOnChange} css={s.input} placeholder="카테고리" />
                    </div>
                    <div>
                        <label css={s.label}>상품 우선 순위</label>
                        <input type="number" name="menuSequence" value={formData.menuSequence} onChange={handleInputValueOnChange} css={s.input} placeholder="메뉴 우선순위" />
                    </div>
                    <div>
                        <label css={s.label}>노출 여부</label>
                        <Checkbox name="isExposure" checked={formData.isExposure === 1} onChange={handleInputValueOnChange} />
                    </div>
                    <div>
                        <label css={s.label}>단품/M 사이즈 가격</label>
                        <input type="number" name="singlePrice" value={formData.prices.find(p => p.size === "M")?.price || ""} onChange={handleInputValueOnChange} css={s.input} placeholder="단품/M 가격" />
                    </div>
                    <div>
                        <label css={s.label}>세트/L 사이즈 가격</label>
                        <input type="number" name="setPrice" value={formData.prices.find(p => p.size === "L")?.price || ""} onChange={handleInputValueOnChange} css={s.input} placeholder="세트/L 가격" />
                    </div>
                </div>
            </div>

            <div css={s.buttonGroup}>
                <button onClick={handleSubmitMenuOnClick} css={s.button}>추가</button>
                <button onClick={handleDeleteMenuOnClick} css={s.button}>삭제</button>
            </div>
        </div>
    );
}

export default App;
