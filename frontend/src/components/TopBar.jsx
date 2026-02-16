import React from "react";
import '../CSS/AppLayout.css';

export default function TopBar() {
    return (
        <>
            <div className="topbar">
                <div className="topbar-user">
                    <h2 className="topbar-username">UserName</h2>
                    <span className="topbar-role">Role</span>
                </div>

                <button className="logout-button">
                    x
                </button>
            </div>
        </>
    )
}