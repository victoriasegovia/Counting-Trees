import { API } from "./api.js"

// LOG IN
export async function loginUser(email, password) {
    try {
        const response = await fetch(`${API}/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password }),
        });

        if (!response.ok) {
            throw new Error("Usuario o contraseña incorrectos");
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error login:", error);
        throw error;
    }
}

export function getUsers() {
    return API.get('/users')
}

export function getUserById(id) {
    return API.get('/users/' + id)
}

export function postUser(user) {
    return API.post('/users', user)
}

export function putUser(id, user) {
    return API.put('/users/' + id, user)
}

export function deleteUser(id) {
    return API.delete('/users/' + id)
}