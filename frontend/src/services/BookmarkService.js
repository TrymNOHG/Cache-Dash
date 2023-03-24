import axios from "axios";
import { useLoggedInStore } from "@/store/store";

const BASE_USER_URL = "http://localhost:8080/user/bookmark";
export const saveBookmark = async (bookmarkDTO) => {
    const sessionToken = useLoggedInStore().getSessionToken
    return await axios.post(`${BASE_USER_URL}/save`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`,
        },
        body: {
            bookmarkDTO
        }
    });
}

export const deleteBookmark = async (bookmarkDeletionDTO) => {
    const sessionToken = useLoggedInStore().getSessionToken
    return axios.post(`${BASE_USER_URL}/delete`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`,
        },
        body: {
            bookmarkDeletionDTO
        }
    });
}

export const loadBookmarks = async () => {
    const sessionToken = useLoggedInStore().getSessionToken
    if (sessionToken === null) throw new Error("Session token cannot be null. Login in again.")
    return axios.post(`${BASE_USER_URL}/load`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`,
        },
    })
}
