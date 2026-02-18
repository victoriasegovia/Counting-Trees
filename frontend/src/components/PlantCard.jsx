import { Link } from 'react-router-dom';

export default function PlantCard({ plant }) {
    return (
        <>
            <div>
                <p>ALERT SIGN WIP</p>
                <h2>{plant.plantId}</h2>
                <p>Foto WIP</p>
                <Link to={`/plants/${plant.plantId}`}><button>Ver planta</button></Link>
            </div>
        </>
    )
}