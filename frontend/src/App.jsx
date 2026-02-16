import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { NavLink } from "react-router-dom";
import Home from './pages/Home';
import RegisterForm from './pages/RegisterForm';
import MapView from './pages/MapView';
import StatsView from './pages/StatsView';
import NewTreeView from './pages/NewTreeView';
import './App.css';

function App() {

  return (
    <>
      <Router>
        {/* Routes */}
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/access" element={<RegisterForm />} />
          <Route path="/map" element={<MapView />} />
          <Route path="/stats" element={<StatsView />} />
          <Route path="/new-tree" element={<NewTreeView />} />
        </Routes>
      </Router>
    </>
  )

}

export default App
