import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useState, useEffect } from "react";
import Landing from './pages/Landing';
import RegisterForm from './pages/RegisterForm';
import MapView from './pages/MapView';
import StatsView from './pages/StatsView';
import NewTreeView from './pages/NewTreeView';
import AppLayout from "./pages/AppLayout";
import InfoView from "./pages/InfoView";
import ProfileView from "./pages/ProfileView";
import * as jwt from "jwt-decode";
import './App.css';

function App() {

  const [user, setUser] = useState({ username: "Invitado", role: "OBSERVADOR", loggedIn: false });

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      try {
        import("jwt-decode").then(jwt => {
          const decoded = jwt.default(token);
          setUser({ username: decoded.firstName, role: decoded.role, loggedIn: true });
        });
      } catch {
        localStorage.removeItem("token");
      }
    }
  }, []);

  return (
    <>
      <Router>
        <Routes>

          {/* WITHOUT TopBar & BottomBar */}
          <Route path="/" element={<Landing />} />
          <Route path="/access" element={<RegisterForm user={user} setUser={setUser} />} />

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

          <Route
            path="/info"
            element={
              <AppLayout user={user} setUser={setUser}>
                <InfoView user={user} />
              </AppLayout>
            }
          />

          <Route
            path="/profile"
            element={
              <AppLayout user={user} setUser={setUser}>
                <ProfileView user={user} />
              </AppLayout>
            }
          />

        </Routes>
      </Router>
    </>
  )

}

export default App
