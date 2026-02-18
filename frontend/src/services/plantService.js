import axios from 'axios';

export const API = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
    headers: { "Content-Type": "application/json" }
});

export async function getPlants() {
    try {
        const token = localStorage.getItem("token");
        const response = await API.get("/plants", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        return response.data;
    } catch (error) {
        console.error("Error fetching plants:", error.response?.data || error.message);
        throw new Error(error.response?.data?.message || "Error al obtener árboles");
    }
}

export async function postPlant() {
    try {
        const token = localStorage.getItem("token");
        const response = await API.post("/plants", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        return response.data;
    } catch (error) {
        console.error("Error fetching plants:", error.response?.data || error.message);
        throw new Error(error.response?.data?.message || "Error al obtener árboles");
    }
}