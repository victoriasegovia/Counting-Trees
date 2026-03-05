import { useState, useContext } from "react";
import { Link, useNavigate } from 'react-router-dom';
import { loginUser, registerUser } from "../services/userService";
import { AuthContext } from "../contexts/AuthContext";
import "../CSS/RegisterForm.css";

function RegisterForm() {

  const { setUser } = useContext(AuthContext);

  const [isRegister, setIsRegister] = useState(false)

  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [confirmPassword, setConfirmPassword] = useState("")

  const [firstName, setFirstName] = useState("")
  const [lastName, setLastName] = useState("")
  const [role, setRole] = useState("GUARDIAN")

  const [error, setError] = useState(null)

  const navigate = useNavigate()

  function handleChange(input) {
    setError(null);

    const { name, value } = input.target
    switch (name) {
      case "email":
        setEmail(value);
        break;
      case "password":
        setPassword(value);
        break;
      case "confirmPassword":
        setConfirmPassword(value);
        break;
      case "firstName":
        setFirstName(value);
        break;
      case "lastName":
        setLastName(value);
        break;
      case "role":
        setRole(value);
        break;
      default:
        break;
    }
  }

  async function handleSubmit(form) {
    form.preventDefault()

    if (isRegister) {
      if (confirmPassword !== password) {
        setError("Las contrasenas no coinciden.")

      } else {
        try {
          const data = await registerUser(firstName, lastName, email, password, role);
          console.log("Token from server:", data.token);
          localStorage.setItem("token", data.token);
          localStorage.setItem("user", JSON.stringify({
            username: data.firstName,
            role: data.role
          }));
          setUser({ username: data.firstName, role: data.role, loggedIn: true });
          navigate("/profile");

        } catch (err) {
          setError(err.message)
        }
      }

    } else {

      try {
        const data = await loginUser(email, password);
        localStorage.setItem("token", data.token);
        localStorage.setItem("user", JSON.stringify({
          username: data.firstName,
          role: data.role
        }));
        console.log(data);

        setUser({ username: data.firstName, role: data.role, loggedIn: true });
        navigate("/map");

      } catch (err) {
        setError(err.message);
      }
    }
  }

  return (
    <div className="container-landing">

      <h1 className="auth-title">
        {isRegister ? "REGISTRO" : "LOG IN"}
      </h1>

      <form className="auth-form" onSubmit={handleSubmit}>
        {isRegister && (
          <>
            <input
              type="text"
              placeholder="First Name"
              name="firstName"
              value={firstName}
              onChange={handleChange}
              required
            />

            <input
              type="text"
              placeholder="Last Name"
              name="lastName"
              value={lastName}
              onChange={handleChange}
              required
            />

            <select name="role" value={role} onChange={handleChange} required>
              <option value="BOTANIST">Botánico</option>
              <option value="GUARDIAN">Guardián</option>
            </select>
          </>
        )}

        <input
          type="email"
          placeholder="Email"
          name="email"
          value={email}
          onChange={handleChange}
          required
        />

        <input
          type="password"
          placeholder="Password"
          name="password"
          value={password}
          onChange={handleChange}
          required
        />

        {isRegister && (
          <input
            type="password"
            placeholder="Confirmar contrasena"
            name="confirmPassword"
            value={confirmPassword}
            onChange={handleChange}
            required
          />
        )}

        <button type="submit" className="btn-access">
          {isRegister ? "REGÍSTRATE" : "LOGIN"}
        </button>
      </form>

      {error && (
        <div className="error-message">
          {error}
        </div>
      )}

      <p className="auth-switch">
        {isRegister ? "¿Ya tienes cuenta?" : "¿No tienes cuenta?"}
        <button
          className="link-button"
          onClick={() => setIsRegister(!isRegister)}
        >
          {isRegister ? "INICIA SESIÓN" : "REGÍSTRATE"}
        </button>
      </p>

      <Link to={"/"}>
        <button className="btn-observer">VOLVER</button>
      </Link>

    </div>
  )
}

export default RegisterForm;
