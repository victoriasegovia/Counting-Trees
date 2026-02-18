import axios from 'axios';

export const API = axios.create({
    baseURL: 'http://localhost:8080/api/v1',
    headers: { "Content-Type": "application/json" }
});

export async function postNote(note) {
    try {
        const token = localStorage.getItem("token");
        const response = await API.post("/notes",
            note,
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            }
        );
        return response.data;
    } catch (error) {
        console.error("Error fetching note:", error.response?.data || error.message);
        throw new Error(error.response?.data?.message || "Error al obtener la nota");
    }
}