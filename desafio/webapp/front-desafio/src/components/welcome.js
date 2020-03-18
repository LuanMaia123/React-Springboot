import React from 'react';
import { Jumbotron } from 'react-bootstrap';

class Welcome extends React.Component {
    render() {
        const margin = {
            margin: "20px",
            marginBottom: "100px"
        }
        return (
            <Jumbotron className="bg-dark text-white" style={margin}>
                <h1>Tecnologias ultilizadas</h1>
                <p >
                    <div style={{ display: 'flex', flexDirection: 'column', justifyContent: '' }} >
                        <li>
                            Spring Boot 2
                        </li>
                        <li>
                            Project Lombok
                        </li>
                        <li>
                            ReactJs
                        </li>
                        <li>
                            Java
                        </li>
                        <li>
                            Maven
                        </li>
                    </div>
                </p>
            </Jumbotron>
        );
    }

}

export default Welcome;