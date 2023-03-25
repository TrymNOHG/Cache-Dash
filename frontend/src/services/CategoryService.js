import axios from "axios";
import { useLoggedInStore } from "@/store/store";

const BASE_USER_URL = "http://localhost:8080/category";
export const addCategory = async (categoryEditDTO) => {
    const sessionToken = useLoggedInStore().getSessionToken
    return await axios.post(`${BASE_USER_URL}/admin/save`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`,
        },
        body: {
            categoryEditDTO
        }
    });
}

export const deleteCategory = async (categoryEditDTO) => {
    const sessionToken = useLoggedInStore().getSessionToken
    return axios.post(`${BASE_USER_URL}/admin/delete`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`,
        },
        body: {
            categoryEditDTO
        }
    });
}

//TODO: take away the need for authentication ***
export const loadCategories = async () => {
    const sessionToken = useLoggedInStore().getSessionToken
    if (sessionToken === null) throw new Error("Session token cannot be null. Login in again.")
    return axios.post(`${BASE_USER_URL}/load`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`,
        },
    })
}
