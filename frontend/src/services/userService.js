import axios from 'axios';

export const API = axios.create({
    baseURL: 'http://localhost:8080/api/v1/auth',
    headers: { "Content-Type": "application/json" }
});

// ------------------------------ LOG IN
export async function loginUser(email, password) {
    try {
        const response = await API.post("/authenticate", { email, password });
        return response.data;
    } catch (error) {
        console.error("Error login:", error.response?.data || error.message);
        throw new Error(
            error.response?.data?.message || "Usuario o contraseña incorrectos"
        );
    }
}

// ------------------------------ REGISTER
export async function registerUser(firstName, lastName, email, password, role) {
    try {
        const response = await API.post("/register", {
            firstName,
            lastName,
            email,
            password,
            role
        });

        // Devuelve data directamente
        return response.data;
    } catch (error) {
        console.error("Error register:", error.response?.data || error.message);
        throw new Error(
            error.response?.data?.message || "Error al registrar el usuario"
        );
    }
}

// export function getUsers() {
//     return API.get('/users')
// }

// export function getUserById(id) {
//     return API.get('/users/' + id)
// }

// export function postUser(user) {
//     return API.post('/users', user)
// }

// export function putUser(id, user) {
//     return API.put('/users/' + id, user)
// }

// export function deleteUser(id) {
//     return API.delete('/users/' + id)
// }