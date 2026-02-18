import { useState, useEffect } from "react"
import { useParams } from 'react-router-dom'
import { getPlantById } from "../services/plantService"
import { getAlertTypes } from "../services/alertTypeService"
import { postNote } from "../services/noteService"
import { postAlert } from "../services/alertService"
import { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";

import "../CSS/Content.css";

const emptyPlant = {
    latitude: null,
    longitude: null,
    specie: null,
    plantedBy: null,
    plantVerificationStatus: "",
    imageBase64: "",
    noteIds: [],
    alertIds: [],
}

export default function PlantDetail() {

    const { user } = useContext(AuthContext)
    const { id } = useParams()

    const [plant, setPlant] = useState(emptyPlant)
    const [noteOpen, setNoteOpen] = useState(false)
    const [alertOpen, setAlertOpen] = useState(false)
    const [alertTypes, setAlertTypes] = useState([])

    const [note, setNote] = useState({
        text: "",
        plant: Number(id),
    })

    const [alert, setAlert] = useState({
        description: "",
        plant: { plantId: Number(id) },
        alertType: { alertType: Number(null) },
    })

    useEffect(() => {
        const fetchPlantById = async () => {
            try {
                const data = await getPlantById(id);
                console.log(data);
                setPlant(data);
            } catch (err) {
                console.log(err.message);
            }
        };
        fetchPlantById();

    }, [id]);

    useEffect(() => {
        const fetchAlertTypes = async () => {
            try {
                const data = await getAlertTypes(id);
                console.log(data);
                setAlertTypes(data);
            } catch (err) {
                console.log(err.message);
            }
        };
        fetchAlertTypes();

    }, [alertOpen])

    const getVerificationBadgeClass = (status) => {
        switch (status) {
            case "VERIFIED":
                return "badge verified";
            case "REJECTED":
                return "badge rejected";
            default:
                return "badge pending";
        }
    };

    function handleAlertTypeChange(e) {
        const value = Number(e.target.value);
        setAlert(prev => ({
            ...prev,
            alertType: {alertTypeId: value}
        }));
    }

    function handleNoteChange(input) {
        const { name, value } = input.target
        setNote({
            ...note, [name]: value
        })
    }

    async function handleNoteSubmit() {
        try {
            await postNote(note);
            setNote({ text: "", plant: id });
            setNoteOpen(false);
        } catch (err) {
            console.log(err)
        }
    }

    async function handleAlertSubmit() {
        try {
            await postAlert(alert);
            setAlert({ description: "", plant: Number(id), alertType: null });
            setAlertOpen(false);
        } catch (err) {
            console.log(err)
        }
    }

    // console.log(note)
    // console.log(alertTypes)
    console.log(alert)

    return (
        <>
            {plant.alertIds.length != 0 &&
                <div>
                    <div className="alert-pulse"></div>
                </div>
            }

            <h1>Plant Name</h1>
            <p>ID #{id}</p>

            <img src={plant.imageBase64} alt="Plant photo" />
            <span className={getVerificationBadgeClass(plant.plantVerificationStatus)}>
                {plant.plantVerificationStatus}
            </span>

            <br></br>
            {user?.role == "BOTANIST" && !alertOpen && (
                <button onClick={() => setAlertOpen(true)}>Crear alerta</button>
            )}

            {alertOpen &&
                <div>
                    <select
                        value={alert.alertTypeId ?? ""}
                        onChange={handleAlertTypeChange}
                        required
                        className="test-font"
                    >
                        <option value="" className="test-font">Selecciona el tipo de alerta</option>
                        {alertTypes.map((alertType) => (
                            <option key={alertType.alertTypeId} value={alertType.alertTypeId} className="test-font">{alertType.name}</option>
                        ))}
                    </select>

                    <textarea
                        placeholder="Anade una pequena descripcion solo si es necesario..."
                        value={alert.description}
                        onChange={(e) =>
                            setAlert(prev => ({
                                ...prev,
                                description: e.target.value
                            }))
                        }
                    />

                    <div>
                        <button onClick={() => setAlertOpen(false)}>
                            Cancelar
                        </button>
                        <button onClick={handleAlertSubmit}>
                            Guardar
                        </button>
                    </div>

                </div>
            }

            <p><strong>Especie ID:</strong> {plant.specieId}</p>
            <p><strong>Estado salud:</strong> {plant.healthStatusId}</p>
            <p><strong>Registrada el:</strong> WIP</p>
            <p><strong>Latitud:</strong> {plant.latitude}</p>
            <p><strong>Longitud:</strong> {plant.longitude}</p>
            <p><strong>Plantada por ID:</strong> {plant.plantedById}</p>

            <span>Notas: {plant.noteIds.length}</span>
            <span>Alertas: {plant.alertIds.length}</span>

            <br></br>
            {!noteOpen &&
                <button onClick={() => setNoteOpen(true)}>Anadir nota</button>
            }

            {noteOpen &&
                <div>
                    <textarea
                        placeholder="Escribe algo sobre esta planta..."
                        value={note.text}
                        onChange={(e) =>
                            setNote(prev => ({
                                ...prev,
                                text: e.target.value
                            }))
                        }
                    />

                    <div>
                        <button onClick={() => setNoteOpen(false)}>
                            Cancelar
                        </button>
                        <button onClick={handleNoteSubmit}>
                            Guardar
                        </button>
                    </div>
                </div>
            }

        </>
    )
}