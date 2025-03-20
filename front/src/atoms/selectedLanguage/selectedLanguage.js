import { atom } from "recoil";

export const selectedLanguageState = atom({
    key: "selectedLanguageState",
    default: "한국어",
});