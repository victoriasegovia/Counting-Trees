import { MapContainer, TileLayer, Marker, Popup, Circle } from "react-leaflet";
import { DivIcon } from "leaflet";
import "leaflet/dist/leaflet.css";
import "../CSS/AppLayout.css";
import { useEffect, useState } from "react";
import { getPlants } from "../services/plantService";
import { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";

export default function MapView() {

    const { user } = useContext(AuthContext)
    const [plants, setPlants] = useState("")
    const [loading, setLoading] = useState(true)
    const [position, setPosition] = useState(null);

    const periUrbanoGranada = [37.16920, -3.57447];

    const treeEmojiIcon = new DivIcon({
        html: "🌳",
        className: "emoji-marker",
        iconSize: [50, 50],
        iconAnchor: [25, 25],
    })

    useEffect(() => {

        navigator.geolocation.getCurrentPosition(
            (pos) => setPosition([pos.coords.latitude, pos.coords.longitude]),
            (err) => console.error(err),
            { enableHighAccuracy: true }
        );

        const fetchPlants = async () => {
            try {
                const data = await getPlants();
                setPlants(data);
                setLoading(false);
            } catch (err) {
                console.log(err.message);
            }
        };
        fetchPlants();
        
    }, [])

    return (
        <>
            {!loading &&
                <MapContainer
                    center={periUrbanoGranada}
                    zoom={16}
                    minZoom={18}
                    maxZoom={21}
                    style={{ width: "100%", height: "100%", borderRadius: "inherit" }}
                >
                    <TileLayer
                        // // ONLINE WITH SATELITE
                        // url="https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}"
                        // attribution='Tiles &copy; Esri &mdash; Source: Esri, Maxar, Earthstar Geographics'

                        // // ONLINE STREET MAP STYLE
                        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'

                        // OFFLINE WITH SATELITE
                        // url="/tiles/{z}/{x}/{y}.png"
                        // minZoom={18}
                        // maxZoom={21}
                        // tileSize={256}
                    />

                    {plants.map((plant) => (
                        <Marker
                            key={plant.id}
                            position={[plant.latitude, plant.longitude]}
                            icon={treeEmojiIcon}
                        >
                            <Popup>
                                <div>
                                    <a href={`/plants/${plant.plantId}`}>Ver planta</a>
                                </div>
                            </Popup>
                        </Marker>
                    ))}
                    {position &&
                        < Circle center={position} radius={5} color="green" />
                    }
                </MapContainer>
            }
        </>
    )
}

