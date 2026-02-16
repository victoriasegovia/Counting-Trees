import React from "react";
import TopBar from "../components/TopBar";
import BottomBar from "../components/BottomBar";

export default function AppLayout({ user, setUser, children }) {
    return (
        <>
            <TopBar user={user} setUser={setUser} />
            <div className="view">
                {children}
            </div>
            <BottomBar />
        </>
    );
}
