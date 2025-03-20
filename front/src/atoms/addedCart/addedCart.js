import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist(); // persist 기능 추가

export const addedCart = atom({
    key: "addedCart",
    default: [],
    effects_UNSTABLE: [persistAtom], // 상태를 유지하도록 설정
});
    
