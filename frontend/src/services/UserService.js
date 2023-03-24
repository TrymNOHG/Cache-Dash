import axios from "axios";
import { useLoggedInStore } from "@/store/store";


const BASE_USER_URL = "http://10.24.3.126:8080/user";
export const registerUser = async (userData) => {
    return await axios.post(`${BASE_USER_URL}/register`, userData)
    .then((response) => {
        //TODO: take in proper logged in data, including the session token.
        useLoggedInStore().setSessionToken(response.data)
        useLoggedInStore().fetchUser()
        console.log("in register user: ")
        console.log(response.data)
        return response.data;
    }).catch((error) => {
        console.warn(error);
    }
    );
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

export const getUser = async sessionToken => {
    return axios.get(`${BASE_USER_URL}/load`, {
        headers: {
            Authorization: `Bearer ${this.sessionToken}`,
        }
    })
        .then(response => {
            console.log(response)
            return response
        }).catch(error => {
            console.warn(error)
            return error
        })
}

export const updateUser = async (userUpdateDTO) => {
    return axios.post(`${BASE_USER_URL}/update`, userUpdateDTO)
        .then((response) => {
            return response.data;
        }).catch((error) => {
            console.warn(error);
        });
}
