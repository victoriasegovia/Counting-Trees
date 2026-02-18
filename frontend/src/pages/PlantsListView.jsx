import { useState, useEffect } from "react";
import { getPlants } from "../services/plantService";
import PlantCard from "../components/PlantCard";

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
        <>
            <h2>Lista</h2>
            {plants.map((plant) => (
                <PlantCard key={plant.plantId} plant={plant} />
            ))}
        </>
    )
}