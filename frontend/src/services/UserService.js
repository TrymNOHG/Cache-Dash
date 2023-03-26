import axios from "axios";
import SessionToken from '@/features/SessionToken.js'



const BASE_USER_URL = "http://localhost:8080/user";
export const registerUser = async (userData) => {
    return await axios.post(`${BASE_USER_URL}/register`, userData);
}

export const loginUser = async (userLoginDTO) => {
    return axios.post(`${BASE_USER_URL}/auth/authenticate`, userLoginDTO)
}

export const getUser = async () => {
    return axios.get(`${BASE_USER_URL}/load`, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        },
    })
}

export const updateUser = async (userUpdateDTO) => {
    return axios.post(`${BASE_USER_URL}/update`, userUpdateDTO, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`
        },
    });
}
