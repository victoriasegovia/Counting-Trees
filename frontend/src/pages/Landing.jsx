import { Link } from 'react-router-dom';
import "../CSS/Landing.css";

export default function Landing() {

    return (
        <>
            <div className='container-landing'>

                <div className='title'>
                    <h1>COUNTING</h1><br></br>
                    <h1>TREES</h1>
                </div>

                <p className='trees'>🌳🌳🌳</p>
                <div>
                    <div style={{ position: 'relative', display: 'inline-block', width: '100%' }}>
                        <span className='eyes' style={{
                            position: 'absolute',
                            top: '-30px',
                            right: '-5px',
                            fontSize: '3rem'
                        }} >👀</span>
                        <Link to={"/map"}><button className='btn-observer'>CONTINUA COMO OBSERVADOR</button></Link>
                    </div>
                    <Link to={"/access"}><button className='btn-access'>LOG IN</button></Link>
                    <Link to={"/access"}><button className='btn-access'>REGÍSTRATE</button></Link>
                </div >

            </div>
        </>
    )
}