import TopBar from "../components/TopBar";
import BottomBar from "../components/BottomBar";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { DivIcon } from "leaflet";
import "leaflet/dist/leaflet.css";
import "../CSS/AppLayout.css";

export default function NewTreeView() {

    return (
        <>
            <TopBar />

            <div className="view">
                <h1>TODO</h1>
            </div>

            <BottomBar />
        </>
    )
}

