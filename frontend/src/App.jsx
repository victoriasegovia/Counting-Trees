import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import RegisterForm from './pages/RegisterForm';
import './App.css';

function App() {

  return (
    <>
      <Router>
        {/* Routes */}
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/access" element={<RegisterForm />} />
        </Routes>
      </Router>
    </>
  )

}

export default App
