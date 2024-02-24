import axios from 'axios';

const baseURL = 'http://localhost:8080';

export const getProduct = async() => {
    try {
        const response = await axios.get(`${baseURL}/api/admin/products/all`);

        return response;
    } catch (error) {
        throw error;
    }
};