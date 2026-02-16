import React from "react";
import '../CSS/AppLayout.css';
import { NavLink } from "react-router-dom";

export default function BottomBar() {
    return (
        <>
            <div className="bottombar">

                <NavLink
                    to="/map"
                    className={({ isActive }) =>
                        `bottom-button ${isActive ? "active" : ""}`
                    }
                >
                    <span>MAPA</span>
                </NavLink>

                <NavLink
                    to="/new-tree"
                    className={({ isActive }) =>
                        `bottom-button center-button ${isActive ? "active" : ""}`
                    }
                >
                    <span>NUEVO ÁRBOL</span>
                </NavLink>

                <NavLink
                    to="/stats"
                    className={({ isActive }) =>
                        `bottom-button ${isActive ? "active" : ""}`
                    }
                >
                    <span>STATS</span>
                </NavLink>

            </div>
        </>
    )
}