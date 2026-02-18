import React from "react";
import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";
import '../CSS/AppLayout.css';
import avatar from "../assets/avatar.svg";


export default function TopBar() {

    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const navigate = useNavigate();
    const { user } = useContext(AuthContext);

    function logout() {
        localStorage.removeItem("token");
        setUser({ username: "Invitado", role: "OBSERVADOR", loggedIn: false });
        navigate("/");
    }

    return (
        <>
            <div className="topbar">
                <div className="topbar-user">
                    <h2 className="topbar-username">
                        {user?.username}
                    </h2>
                    <span className="topbar-role">
                        {user?.role}
                    </span>
                </div>

                {user.loggedIn ? (
                    <span onClick={() => setIsMenuOpen(!isMenuOpen)}>
                        <img src={avatar} alt="Avatar" className="avatar" />
                        {isMenuOpen && (
                            <div className="dropdown-menu">

                                <div onClick={() => { navigate("/profile"); setIsMenuOpen(false); }}>
                                    Profile
                                </div>

                                <div onClick={() => { navigate("/info"); setIsMenuOpen(false); }}>
                                    Info
                                </div>

                                <div onClick={() => logout()}>
                                    Logout
                                </div>
                            </div>
                        )}
                    </span>
                ) : (
                    <NavLink to="/access">
                        <button className="login-button">→</button>
                    </NavLink>
                )}
            </div>
        </>
    )
}