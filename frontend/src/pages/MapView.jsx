import TopBar from "../components/TopBar";
import BottomBar from "../components/BottomBar";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { DivIcon } from "leaflet";
import "leaflet/dist/leaflet.css";
import "../CSS/AppLayout.css";

export default function MapView() {

    const periUrbanoGranada = [37.1741, -3.5663];

    const treeEmojiIcon = new DivIcon({
        html: "🌳",
        className: "emoji-marker",
        iconSize: [50, 50],
        iconAnchor: [25, 25],
    })

    return (
        <>
            <TopBar />

            <div className="view">
                <MapContainer
                    center={periUrbanoGranada} // coordinates
                    zoom={20}
                    style={{ width: "100%", height: "100%", borderRadius: "inherit" }}
                >
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    />
                    <Marker position={periUrbanoGranada} icon={treeEmojiIcon}>
                        <Popup>Plaza Nueva, Granada</Popup>
                    </Marker>
                </MapContainer>
            </div>

            <BottomBar />
        </>
    )
}

