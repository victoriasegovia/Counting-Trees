import { Link } from 'react-router-dom';

export default function Home() {

    return (
        <>
            <h1>Welcome</h1>
            <Link to={"/access"}><button>Log In</button></Link>
            <Link to={"/access"}><button>Register</button></Link>
        </>
    )
}