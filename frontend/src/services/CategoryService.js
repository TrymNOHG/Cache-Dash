import axios from "axios";
import SessionToken from '@/features/SessionToken.js'

const BASE_USER_URL = "http://localhost:8080/category";
export const addCategory = async (categoryEditDTO) => {
    return await axios.post(`${BASE_USER_URL}/admin/save`, categoryEditDTO, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        },
    });
}

export const deleteCategory = async (categoryEditDTO) => {
    return axios.post(`${BASE_USER_URL}/admin/delete`, categoryEditDTO, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        },

    });
}


//TODO: take away the need for authentication ***
export const loadAllCategories = async (categoryId) => {
    console.log(categoryId)
    return axios.get(`${BASE_USER_URL}/load/all/${categoryId}`)
}

export const loadMainCategories = async () => {
    return axios.get(`${BASE_USER_URL}/load/main`)
}

export const loadSubCategories = async (categoryId) => {
    console.log("Du er inne i subs" + categoryId)
    console.log(axios.get(`${BASE_USER_URL}/load/sub/${categoryId}`))
    return axios.get(`${BASE_USER_URL}/load/sub/${categoryId}`)
}