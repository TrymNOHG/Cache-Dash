import axios from "axios";
import SessionToken from '@/features/SessionToken.js'

const BASE_LISTING_URL = "http://localhost:8080/listing";
export const createNewListing = async (listingDTO) => {
    return await axios.post(`${BASE_LISTING_URL}/user/create`, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        },
        body: {
            listingDTO
        }
    })
}

export const deleteListing = async (listingDeletionDTO) => {
    return await axios.post(`${BASE_LISTING_URL}/user/delete`, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        },
        body: {
            listingDeletionDTO
        }
    })
}

export const updateListing = async (listingUpdateDTO) => {
    return await axios.post(`${BASE_LISTING_URL}/user/update`, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        },
        body: {
            listingUpdateDTO
        }
    })
}

//TODO: add token use instead
export const loadListingByUser = async (username) => {
    return await axios.get(`${BASE_LISTING_URL}/user/load`, username)
        .then((response) => {
            console.log(response.data)
            return response.data;
        }).catch((error) => {
            console.warn(error);
        });
}

export const loadAllListings = async () => {
    return await axios.get(`${BASE_LISTING_URL}/load`)
        .then((response) => {
            console.log(response.data)
            return response.data;
        }).catch((error) => {
            console.warn(error);
        });
}

