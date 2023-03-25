import axios from "axios";
import { useLoggedInStore } from "@/store/store";
import router from "@/router/router";


const BASE_USER_URL = "http://localhost:8080/user";
export const registerUser = async (userData) => {
    return await axios.post(`${BASE_USER_URL}/register`, userData);
}

export const loginUser = async (userLoginDTO) => {
    return axios.post(`${BASE_USER_URL}/auth/authenticate`, userLoginDTO)
}

export const getUser = async () => {
    const sessionToken = useLoggedInStore().getSessionToken
    if (sessionToken === null) {
        alert("Log in to access your profile!") //TODO: make better
        await router.push("/login")
        throw new Error("Session token cannot be null. Login in again.")
    }
    return axios.get(`${BASE_USER_URL}/load`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`,
        },
    })
}

export const updateUser = async (userUpdateDTO) => {
    const sessionToken = useLoggedInStore().getSessionToken
    return axios.post(`${BASE_USER_URL}/update`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`
        },
        body: {
            userUpdateDTO
        }
    });
}
