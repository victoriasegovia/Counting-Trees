import TopBar from "../components/TopBar";
import BottomBar from "../components/BottomBar";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import "../CSS/AppLayout.css";

export default function MapView() {
    return (
        <>
            <TopBar />

            <div className="view">
                <MapContainer
                    center={[37.17684, -3.59587]} // coordinates
                    zoom={13}
                    style={{ width: "100%", height: "100%", borderRadius: "inherit" }}
                >
                    <TileLayer
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    />
                    <Marker position={[37.17684, -3.59587]}>
                        <Popup>Ejemplo de ubicación</Popup>
                    </Marker>
                </MapContainer>
            </div>

            <BottomBar />
        </>
    )
}

