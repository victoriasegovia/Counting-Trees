import { MapContainer, TileLayer, Marker, useMapEvents } from "react-leaflet";
import { useState } from "react";
import { DivIcon } from "leaflet";
import "leaflet/dist/leaflet.css";
import "../CSS/AppLayout.css";
import "../CSS/PlantForm.css";

export default function MapPicker({ initialPosition, onConfirm, onCancel }) {

    const periUrbanoGranada = [37.16920, -3.57447];
    const [position, setPosition] = useState(initialPosition);

    const pointEmojiIcon = new DivIcon({
        html: "📍",
        className: "emoji-marker",
        iconSize: [50, 50],
        iconAnchor: [25, 25],
    });

    function LocationMarker() {
        useMapEvents({
            click(e) {
                setPosition([e.latlng.lat, e.latlng.lng]);
            },
        });

        return position ? <Marker position={position} icon={pointEmojiIcon} /> : null;
    }

    return (

        <div className="view">

            <MapContainer
                center={initialPosition || periUrbanoGranada}
                zoom={16}
                minZoom={15}
                maxZoom={18}
                id="map"
            >
                <TileLayer
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                    attribution="&copy; OpenStreetMap contributors"
                />
                <LocationMarker />
            </MapContainer>

            <div className="map-actions">
                <button
                    className="cancel-location-btn"
                    onClick={onCancel}
                >
                    Cancelar
                </button>

                <button
                    className="confirm-location-btn"
                    disabled={!position}
                    onClick={() => onConfirm(position[0], position[1])}
                >
                    Confirmar ubicación
                </button>
            </div>

        </div>
    );
}