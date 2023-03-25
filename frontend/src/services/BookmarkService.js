import axios from "axios";
import SessionToken from '@/features/SessionToken.js'

const BASE_USER_URL = "http://localhost:8080/user/bookmark";
export const saveBookmark = async (bookmarkDTO) => {
    return await axios.post(`${BASE_USER_URL}/save`, bookmarkDTO, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        }
    });
}

export const deleteBookmark = async (bookmarkDeletionDTO) => {
    return axios.post(`${BASE_USER_URL}/delete`, bookmarkDeletionDTO, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        }
    });
}

export const loadBookmarks = async () => {
    return axios.post(`${BASE_USER_URL}/load`, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        },
    })
}
