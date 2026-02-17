import { API } from "./api.js"

const API_URL = "http://localhost:8080/api/v1/auth";

// LOG IN
export async function loginUser(email, password) {
    try {
        const response = await fetch(`${API_URL}/authenticate`, {
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

// REGISTER
export async function registerUser(firstName, lastName, email, password, role) {
    try {
        const response = await fetch(`${API_URL}/register`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password,
                role: role
            }),
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || "Error al registrar el usuario");
        }

        const data = await response.text();
        console.log("STATUS:", response.status);
        console.log("RESPONSE DATA:", data);

        return data;

    } catch (error) {
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