import axios from 'axios';

const baseURL = 'http://localhost:8080';

export const addProduct = async (data) => {
    try {
        const response = await axios.post(`${baseURL}/admin/create`, data);

        return response;
    } catch (error) {
        throw error;
    }
};