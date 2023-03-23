import axios from "axios";

const BASE_API_URL = "http://10.24.0.100:8080/api/auth";
export const sendNewItem = async (formData) => {
    return await axios.post(`${BASE_API_URL}/register`, {
        formData
    }).then((response) => {
        console.log(response.data)
        return response.data;
    }).catch(
        (error) => {
            console.warn(error);
        }
    );
}