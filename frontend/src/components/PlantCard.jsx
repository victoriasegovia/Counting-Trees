import { Link } from 'react-router-dom';
import "../CSS/PlantCard.css";

export default function PlantCard({ plant }) {
    return (
        <>
            <div className="plant-card">
                <div className="plant-card-header">
                    <h2 className="plant-title">ID {plant.plantId}# Nombre Especie</h2>
                    <p className="alert-sign">⚠️</p>
                </div>

                <div className="plant-card-body">
                    <div className="plant-photo">Foto WIP</div>
                </div>

                <div className="plant-card-footer">
                    <Link to={`/plants/${plant.plantId}`}>
                        <button className="view-plant-btn">Ver planta</button>
                    </Link>
                </div>
            </div>
        </>
    )
}