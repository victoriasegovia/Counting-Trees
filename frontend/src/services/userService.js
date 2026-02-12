import { API } from "./api.js"

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