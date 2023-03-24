import axios from "axios";
import { useLoggedInStore } from "@/store/store";


const BASE_USER_URL = "http://localhost:8080/user/chat";
export const newChat = async (conversationDTO) => {
    const sessionToken = useLoggedInStore().getSessionToken
    return await axios.post(`${BASE_USER_URL}/new`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`,
        },
        body: {
            conversationDTO
        }
    });
}

export const sendMessage = async (messageDTO) => {
    const sessionToken = useLoggedInStore().getSessionToken
    return await axios.post(`${BASE_USER_URL}/send`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`,
        },
        body: {
            messageDTO
        }
    });
}

//TODO: change the load of conversation to just load the authenticated user's conversations.
export const loadConversations = async () => {
    const sessionToken = useLoggedInStore().getSessionToken
    return axios.post(`${BASE_USER_URL}/load`, {
        headers: {
            Authorization: `Bearer ${sessionToken}`,
        }
    })
}
