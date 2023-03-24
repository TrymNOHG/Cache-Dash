import axios from "axios";

const BASE_LISTING_URL = "http://10.24.3.126:8080/listing";
export const createNewListing = async (listingDTO) => {
    return await axios.post(`${BASE_LISTING_URL}/user/create`, listingDTO)
        .then((response) => {
            console.log(response.data)
            return response.data;
        }).catch((error) => {
            console.warn(error);
        });
}

export const deleteListing = async (listingDeletionDTO) => {
    return await axios.post(`${BASE_LISTING_URL}/user/delete`, listingDeletionDTO)
        .then((response) => {
            console.log(response.data)
            return response.data;
        }).catch((error) => {
            console.warn(error);
        });
}

export const updateListing = async (listingUpdateDTO) => {
    return await axios.post(`${BASE_LISTING_URL}/user/update`, listingUpdateDTO)
        .then((response) => {
            console.log(response.data)
            return response.data;
        }).catch((error) => {
            console.warn(error);
        });
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

