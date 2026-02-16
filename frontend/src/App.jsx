import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useState } from "react";
import Landing from './pages/Landing';
import RegisterForm from './pages/RegisterForm';
import MapView from './pages/MapView';
import StatsView from './pages/StatsView';
import NewTreeView from './pages/NewTreeView';
import AppLayout from "./pages/AppLayout";
import './App.css';

function App() {

  const [user, setUser] = useState({
    username: "Observador",
    role: "observer",
    loggedIn: false,
  });

  return (
    <>
      <Router>
        <Routes>
          
          {/* WITHOUT TopBar & BottomBar */}
          <Route path="/" element={<Landing />} />
          <Route path="/access" element={<RegisterForm />} />

          {/* WITH TopBar & BottomBar */}
          <Route
            path="/map"
            element={
              <AppLayout user={user} setUser={setUser}>
                <MapView user={user} />
              </AppLayout>
            }
          />

          <Route
            path="/stats"
            element={
              <AppLayout user={user} setUser={setUser}>
                <StatsView user={user} />
              </AppLayout>
            }
          />

          <Route
            path="/new-tree"
            element={
              <AppLayout user={user} setUser={setUser}>
                <NewTreeView user={user} />
              </AppLayout>
            }
          />
        </Routes>
      </Router>
    </>
  )

}

export default App
