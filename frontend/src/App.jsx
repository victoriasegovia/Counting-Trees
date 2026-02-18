import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from "./contexts/AuthContext";

import Landing from './pages/Landing';
import RegisterForm from './pages/RegisterForm';
import MapView from './pages/MapView';
import StatsView from './pages/StatsView';
import NewPlantForm from './pages/NewPlantForm';
import AppLayout from "./pages/AppLayout";
import InfoView from "./pages/InfoView";
import ProfileView from "./pages/ProfileView";
import PlantDetail from './pages/PlantDetail';
import PlantsListView from './pages/PlantsListView';

import './App.css';

function App() {

  return (
    <>
      <AuthProvider>
        <Router>
          <Routes>

            {/* WITHOUT TopBar & BottomBar */}
            <Route path="/" element={<Landing />} />
            <Route path="/access" element={<RegisterForm />} />

            {/* WITH TopBar & BottomBar */}
            <Route
              path="/map"
              element={
                <AppLayout>
                  <MapView />
                </AppLayout>
              }
            />

            <Route
              path="/stats"
              element={
                <AppLayout>
                  <StatsView />
                </AppLayout>
              }
            />

            <Route
              path="/new-plant"
              element={
                <AppLayout>
                  <NewPlantForm />
                </AppLayout>
              }
            />

            <Route
              path="/plants"
              element={
                <AppLayout>
                  <PlantsListView />
                </AppLayout>
              }
            />

            <Route
              path="/plants/:id"
              element={
                <AppLayout>
                  <PlantDetail />
                </AppLayout>
              }
            />

            <Route
              path="/info"
              element={
                <AppLayout>
                  <InfoView />
                </AppLayout>
              }
            />

            <Route
              path="/profile"
              element={
                <AppLayout>
                  <ProfileView />
                </AppLayout>
              }
            />

          </Routes>
        </Router>
      </AuthProvider>
    </>
  )

}

export default App
