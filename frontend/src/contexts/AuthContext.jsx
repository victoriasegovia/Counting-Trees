import { createContext, useState, useEffect } from "react";

export const AuthContext = createContext();

export function AuthProvider({ children }) {

    const [loading, setLoading] = useState(true)
    const [user, setUser] = useState(null)

    useEffect(() => {
        const token = localStorage.getItem("token");
        const storedUser = JSON.parse(localStorage.getItem("user"));

        if (token && storedUser) {
            setUser({
                username: storedUser.username,
                role: storedUser.role,
                loggedIn: true
            });
        } else {
            setUser({
                username: "Invitado",
                role: "OBSERVADOR",
                loggedIn: false
            });
        }
        setLoading(false);
    }, [])

    if (loading) return null;

    return (
        <AuthContext.Provider value={{ user, setUser }}>
            {children}
        </AuthContext.Provider>
    )
}
