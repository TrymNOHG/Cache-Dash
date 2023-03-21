import axios from "axios";

const BASE_API_URL = "http://localhost:8080/api/auth";
export const registerUser = async (name, password) => {
    return await axios.post(`${BASE_API_URL}/register`, {
        username: name,
        password: password,
    }).then((response) => {
        console.log(response.data)
        return response.data;
    }).catch(
        (error) => {
            console.warn(error);
        }
    );
}

export const loginUser = async (username, password) => {
    return axios.post(`${BASE_API_URL}/authenticate`, {
        username: username,
        password: password,
    }).then((response) => {
        return response.data;
    }).catch(
        (error) => {
            console.warn(error);
        }
    );
}