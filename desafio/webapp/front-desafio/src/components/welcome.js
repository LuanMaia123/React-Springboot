import React, { Component } from 'react';
import { Jumbotron } from 'react-bootstrap';
import logo from './vibelogo.png';

export default class Welcome extends Component {
    render() {
        const margin = {
            margin: "20px",
            marginBottom: "100px"
        }
        return (
            <>
                <div>
                    <img src={logo} alt="logo" />
                    <h3>Desafio Vibe Tecnologia</h3>
                </div>
                <Jumbotron className="bg-dark text-white" style={margin}>
                    <h1>Tecnologias ultilizadas</h1>
                        <div style={{ display: 'flex', flexDirection: 'column', marginTop:'40px' }} >
                            <ul style={{textAlign:'left', alignSelf:'center'}}>
                                <li>Spring Boot 2</li>
                                <li>Project Lombok</li>
                                <li>ReactJs</li>
                                <li>React Bootstrap</li>
                                <li>React Fontawesome</li>
                                <li>Java</li>
                                <li>Maven</li>
                                <li>Postgresql</li>
                            </ul>
                        </div>
                </Jumbotron>
            </>
        );
    }

}