import axios from "axios";
import SessionToken from '@/features/SessionToken.js'

const BASE_LISTING_URL = "http://localhost:8080/listing";
export const createNewListing = async (listingDTO) => {
    return await axios.post(`${BASE_LISTING_URL}/user/create`, listingDTO, {
        headers: {
            'Content-Type': 'multipart/form-data',
            Authorization: `Bearer ${await SessionToken()}`,
        }
    })
}

export const deleteListing = async (listingDeletionDTO) => {
    return await axios.post(`${BASE_LISTING_URL}/user/delete`, listingDeletionDTO, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
        }
    })
}

export const updateListing = async (listingUpdateDTO) => {
    return await axios.post(`${BASE_LISTING_URL}/user/update`, listingUpdateDTO, {
        headers: {
            Authorization: `Bearer ${await SessionToken()}`,
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
}

export const loadListingsByCategoryId = async (categoryId) => {
    return await axios.get(`${BASE_LISTING_URL}/category/${categoryId}/load`)
}

