import axios from 'axios';

export const API = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
    headers: { "Content-Type": "application/json" }
});

export async function getAlertTypes() {
    try {
        const token = localStorage.getItem("token");
        const response = await API.get("/alert-types", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        return response.data;
    } catch (error) {
        console.error("Error fetching alert types:", error.response?.data || error.message);
        throw new Error(error.response?.data?.message || "Error al obtener los tipos de alertas");
    }
}