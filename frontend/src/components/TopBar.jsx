import React from "react";
import { NavLink } from "react-router-dom";
import '../CSS/AppLayout.css';

export default function TopBar({ user, setUser }) {
    return (
        <>
            <div className="topbar">
                <div className="topbar-user">
                    <h2 className="topbar-username">
                        {user.loggedIn ? user.username : "Invitado"}
                    </h2>
                    <span className="topbar-role">
                        {user.loggedIn ? user.role : "Observador"}
                    </span>
                </div>

                {user.loggedIn ? (
                    <button className="logout-button">
                        x
                    </button>
                ) : (
                    <NavLink to="/access">
                        <button className="logout-button">→</button>
                    </NavLink>
                )}
            </div>
        </>
    )
}