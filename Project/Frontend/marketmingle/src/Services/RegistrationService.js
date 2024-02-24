import axios from 'axios';
const baseURL = 'http://localhost:8080';


export const registerUser = async (data) => {
    try {
        const response = await axios.post(`${baseURL}/auth/signup`, data);

        return response;
    } catch (error) {
        throw error;
    }
};