import { useState } from "react";
import { Link } from 'react-router-dom';
import { loginUser } from "../services/userService";

function RegisterForm({ setUser }) {

  const [isRegister, setIsRegister] = useState(false)

  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")

  const [firstName, setFirstName] = useState("")
  const [lastName, setLastName] = useState("")
  const [role, setRole] = useState("GUARDIAN");

  const [error, setError] = useState(null);

  function handleChange(input) {
    const { name, value } = input.target
    switch (name) {
      case "email":
        setEmail(value);
        break;
      case "password":
        setPassword(value);
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

  function handleSubmit(form) {
    
    form.preventDefault()
    if (isRegister) {
      console.log("Registrando:", { email, password, firstName, lastName });
      setUser({ username: firstName, role, loggedIn: true });
      navigate("/map");

    } else {

      try {
        const data = loginUser(email, password);
        localStorage.setItem("token", data.token);      // guardar JWT
        setUser({ username: data.username, role: data.role, loggedIn: true });
        navigate("/map");
      } catch (err) {
        setError(err.message);
      }
    }
  }

  return (

    <div>

      <h1>{isRegister ? "REGISTRO" : "LOGIN"}</h1>

      <form onSubmit={handleSubmit}>
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

            <br />

            <input
              type="text"
              placeholder="Last Name"
              name="lastName"
              value={lastName}
              onChange={handleChange}
              required
            />

            <br />

            <select name="role" value={role} onChange={handleChange} required>
              <option value="BOTANIST">Botánico</option>
              <option value="GUARDIAN">Guardián</option>
            </select>
          </>
        )}

        <br />

        <input
          type="email"
          placeholder="Email"
          name="email"
          value={email}
          onChange={handleChange}
          required
        />

        <br />

        <input
          type="password"
          placeholder="Password"
          name="password"
          value={password}
          onChange={handleChange}
          required
        />

        <br />

        {isRegister && (
          <input
            type="confirm-password"
            placeholder="Confirmar contrasena"
            name="password"
            value={password}
            onChange={handleChange}
            required
          />)}

        <br />

        <button type="submit">{isRegister ? "REGÍSTRATE" : "LOGIN"}</button>
      </form>

      <br />

      <p>
        {isRegister ? "¿Ya tienes cuenta?" : "¿No tienes cuenta?"}{" "}
        <button onClick={() => setIsRegister(!isRegister)}>
          {isRegister ? "INICIA SESIÓN" : "REGÍSTRATE"}
        </button>
      </p>

      <br />

      <Link to={"/"}><button>VOLVER</button></Link>

    </div>

  )
}

export default RegisterForm;
