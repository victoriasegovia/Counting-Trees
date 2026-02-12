import { useState } from "react";
import { Link } from 'react-router-dom';
import { getUserById } from "../services/userService";

function RegisterForm() {

  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")

  const [firstName, setFirstName] = useState("")
  const [lastName, setLastName] = useState("")

  function handleChange(input) {
    const { name, value } = input.target
    set
  }

  function handleSubmit(form) {
    form.preventDefault()
  }

  return (
    <div>
        <Link to={"/"}><button>Home</button></Link>

        <h2>Login</h2>
        
        <form onSubmit={handleSubmit}>
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={() => handleChange()}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={() => handleChange()}
            required
          />
          <br></br>

          <button
            type="submit"
          >
            Login
          </button>
        </form>
    </div>

  )
}

export default RegisterForm;
