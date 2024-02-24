import axios from 'axios';

const baseURL = 'http://localhost:8080';

export const loginUser = async (data) => {
    try {
        const response = await axios.post(`${baseURL}/auth/signin`,data);

        return response;
    } catch (error) {
        throw error;
    }
};