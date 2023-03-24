import axios from "axios";
import { useLoggedInStore } from "@/store/store";


const BASE_USER_URL = "http://localhost:8080/user";
export const registerUser = async (userData) => {
    return await axios.post(`${BASE_USER_URL}/register`, userData);
}

export const loginUser = async (username, password) => {
    return axios.post(`${BASE_USER_URL}/auth/authenticate`, {
        username: username,
        password: password,
    }).then((response) => {
        //TODO: get user information back
        console.log("User login response: " + response)
        useLoggedInStore().setSessionToken(response.data)
        useLoggedInStore().fetchUser()
        return response.data;
    }).catch((error) => {
        console.warn(error);
    });
}

export const getUser = async () => {
    const sessionToken = useLoggedInStore().getSessionToken
    console.log(sessionToken)
    if (sessionToken === null) throw new Error("Session token cannot be null. Login in again.")
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
