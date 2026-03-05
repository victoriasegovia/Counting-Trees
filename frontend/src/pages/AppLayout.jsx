import React from "react";
import TopBar from "../components/TopBar";
import BottomBar from "../components/BottomBar";

export default function AppLayout({ children }) {
    return (
        <>
            <TopBar />
            <div className="view">
                <div className="view-content">
                    {children}
                </div>
            </div>
            <BottomBar />
        </>
    );
}
