import React from "react";
import '../CSS/AppLayout.css';

export default function BottomBar() {
    return (
        <>
            <div className="bottombar">
                <button className="bottom-button">
                    <span>MAPA</span>
                </button>

                <button className="bottom-button center-button">
                    <span>NUEVO ÁRBOL</span>
                </button>

                <button className="bottom-button">
                    <span>STATS</span>
                </button>
            </div>
        </>
    )
}