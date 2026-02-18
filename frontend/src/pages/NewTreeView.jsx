import { MapContainer, TileLayer, Marker, useMapEvents } from "react-leaflet";
import { useEffect, useState } from "react";
import { getSpecies } from "../services/specieService";
import { postPlant } from "../services/plantService";
import { DivIcon } from "leaflet";
import "leaflet/dist/leaflet.css";
import "../CSS/AppLayout.css";

export default function NewTreeView() {

    const [species, setSpecies] = useState([])
    const [position, setPosition] = useState(null)
    const [specieId, setSpecieId] = useState()
    const [loading, setLoading] = useState(true)
    const periUrbanoGranada = [37.16920, -3.57447]

    const pointEmojiIcon = new DivIcon({
        html: "📍",
        className: "emoji-marker",
        iconSize: [50, 50],
        iconAnchor: [25, 25],
    })

    const [formData, setFormData] = useState({
        latitude: null,
        longitude: null,
        specie: null,
        plantedBy: null,
        plantVerificationStatus: "PENDING",
        imageBase64: "",
    })

    useEffect(() => {
        const fetchSpecies = async () => {
            try {
                const data = await getSpecies();
                setSpecies(data);
            } catch (err) {
                console.log(err.message);
            }
        };
        fetchSpecies();
        setLoading(false);
    }, [])

    useEffect(() => {
        if (position) {
            setFormData((prev) => ({
                ...prev,
                latitude: position[0],
                longitude: position[1],
            }));
        }
    }, [position])

    useEffect(() => {
        setFormData((prev) => ({
            ...prev,
            specie: specieId,
        }))
    }, [specieId])

    function handleChange(input) {
        const { name, value } = input.target
        setFormData({
            ...formData, [name]: value
        })
    }

    const handleSpecieChange = (e) => setSpecieId(Number(e.target.value))

    async function handleSubmit(input) {
        form.preventDefault();
        if (!position) {
            alert("Por favor, selecciona la ubicación en el mapa");
            return;
        }
        try {
            await postPlant(formData)
        } catch (err) {

        }
        console.log("Datos del nuevo árbol:", treeData);
    }

    function LocationMarker({ position, setPosition }) {
        useMapEvents({
            click(e) {
                setPosition([e.latlng.lat, e.latlng.lng]);
            },
        });

        return position === null ? null : (
            <Marker position={position} icon={pointEmojiIcon} />
        );
    }

    console.log(formData)
    console.log(position)

    return (
        <>
            <div>
                <form onSubmit={handleSubmit} className="tree-form">
                    <h1 className="test-font">Nuevo Árbol</h1>

                    <select
                        value={specieId}
                        onChange={handleSpecieChange}
                        required
                        className="test-font"
                    >
                        <option value="" className="test-font">Selecciona la especie</option>
                        {species.map((specie) => (
                            <option value={specie.specieId} className="test-font">{specie.commonName}</option>
                        ))}
                    </select><br></br>

                    <input
                        type="number"
                        name="latitude"
                        placeholder="Latitud"
                        value={formData.latitude}
                        onChange={handleChange}
                        className="test-font"
                    /><br></br>

                    <input
                        type="number"
                        name="longitude"
                        placeholder="Longitud"
                        value={formData.longitude}
                        onChange={handleChange}
                        className="test-font"
                    /><br></br>

                    <input
                        type="text"
                        name="imageBase64"
                        placeholder="Foto del árbol (WIP)"
                        value={formData.imageBase64}
                        className="test-font"
                    /><br></br>

                    <button type="submit">
                        Guardar Árbol
                    </button>
                </form>

                <div style={{ height: "400px", width: "100%" }} className="map-container">
                    <p className="test-font">Toca en el mapa la localizacion del Árbol</p>
                    <div id="map">
                        <MapContainer
                            center={periUrbanoGranada}
                            zoom={16}
                            minZoom={15}
                            maxZoom={18}
                            style={{ width: "100%", height: "100%", borderRadius: "inherit" }}
                        >
                            <TileLayer
                                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                                attribution="&copy; OpenStreetMap contributors"
                            />
                            <LocationMarker position={position} setPosition={setPosition} />
                        </MapContainer>
                    </div>
                </div>
            </div >
        </>
    )
}

