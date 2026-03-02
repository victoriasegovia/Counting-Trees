import { useEffect, useState } from "react";
import { getSpecies } from "../services/specieService";
import { postPlant } from "../services/plantService";
import MapPicker from "../components/MapPicker";
import "../CSS/AppLayout.css";
import "../CSS/PlantForm.css";

export default function NewPlantForm() {

    const [species, setSpecies] = useState([]);
    const [showMap, setShowMap] = useState(false);

    const [formData, setFormData] = useState({
        latitude: "",
        longitude: "",
        specie: "",
        plantedBy: null,
        plantVerificationStatus: "PENDING",
        imageBase64: "",
    });

    useEffect(() => {
        async function fetchSpecies() {
            try {
                const data = await getSpecies();
                setSpecies(data);
            } catch (err) {
                console.log(err.message);
            }
        }
        fetchSpecies();
    }, []);

    function handleChange(e) {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    }

    function handleLocationConfirm(lat, lng) {
        setFormData(prev => ({
            ...prev,
            latitude: lat,
            longitude: lng,
        }));
        setShowMap(false);
    }

    async function handleSubmit(e) {
        e.preventDefault();

        if (!formData.latitude || !formData.longitude) {
            alert("Selecciona la ubicación en el mapa");
            return;
        }

        try {
            await postPlant({
                ...formData,
                specie: Number(formData.specie),
            });

            alert("Árbol guardado 🌿");

        } catch (err) {
            console.log(err);
        }
    }

    if (showMap) {
        return (
            <MapPicker
                initialPosition={
                    formData.latitude && formData.longitude
                        ? [formData.latitude, formData.longitude]
                        : null
                }
                onConfirm={handleLocationConfirm}
                onCancel={() => setShowMap(false)}
            />
        );
    }

    return (
        <div className="view">
            <form onSubmit={handleSubmit} className="tree-form">

                <h2 className="section-title">Nuevo Árbol</h2>

                <select
                    name="specie"
                    value={formData.specie}
                    onChange={handleChange}
                    required
                >
                    <option value="">Selecciona la especie</option>
                    {species.map(specie => (
                        <option key={specie.specieId} value={specie.specieId}>
                            {specie.commonName}
                        </option>
                    ))}
                </select>

                <div className="coordinates-row">
                    <input
                        type="number"
                        name="latitude"
                        placeholder="Latitud"
                        value={formData.latitude}
                        readOnly
                    />

                    <input
                        type="number"
                        name="longitude"
                        placeholder="Longitud"
                        value={formData.longitude}
                        readOnly
                    />

                    <button
                        type="button"
                        className="map-button"
                        onClick={() => setShowMap(true)}
                    >
                        📍
                    </button>
                </div>

                <input
                    type="text"
                    placeholder="Foto del árbol (WIP)"
                    disabled
                />

                <button type="submit" className="save-button">
                    Guardar Árbol
                </button>

            </form>
        </div>
    );
}