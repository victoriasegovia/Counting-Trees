import { useState, useEffect } from "react";
import { getPlants } from "../services/plantService";
import PlantCard from "../components/PlantCard";
import "../CSS/PlantCard.css";

export default function PlantsListView() {

    const [plants, setPlants] = useState([])

    useEffect(() => {
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
        <div className="plant-list">
            {plants.map((plant) => (
                <PlantCard key={plant.plantId} plant={plant} />
            ))}
        </div>
    )
}