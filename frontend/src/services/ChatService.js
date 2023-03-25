import axios from "axios";
import SessionToken from '@/features/SessionToken.js'


const BASE_USER_URL = "http://localhost:8080/user/chat";
export const newChat = async (conversationDTO) => {
    return await axios.post(`${BASE_USER_URL}/new`, conversationDTO,{
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        },
        body: {
            conversationDTO
        }
    });
}

export const sendMessage = async (messageDTO) => {
    return await axios.post(`${BASE_USER_URL}/send`, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        },
        body: {
            messageDTO
        }
    });
}

//TODO: change the load of conversation to just load the authenticated user's conversations.
export const loadConversations = async (username) => {
    console.log(username)
    return axios.post(`${BASE_USER_URL}/load`, username, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        }
    })
}
