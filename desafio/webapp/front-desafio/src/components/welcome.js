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
                    <div style={{ display: 'flex', flexDirection: 'column', marginTop: '40px' }} >
                        <ul style={{ textAlign: 'left', alignSelf: 'center' }}>
                            <li>Spring Boot 2</li>
                            <li>Project Lombok</li>
                            <li>ReactJs</li>
                            <li>React Bootstrap</li>
                            <li>React Fontawesome</li>
                            <li>Java</li>
                            <li>Maven</li>
                            <li>Postgresql</li>
                        </ul>
                        <p>
                            Obrigado pela nova oportunidade de mostrar um pouco do meu conhecimento.
                            Essa aplicação, é um desafio proposto por vocês, mas vai além disso, pois,
                            eu me dafiei também. Nessa aplicação eu usei a tecnologia ReactJs para fazer
                            as telas, eu comecei a estudar ReactJS dia 12 desse mês e resolvi aplicar o que
                            estou estudando neste desafio, estou acompanhando o curso da RocketSeat, e até o
                            momento é um curso muito bom. Apliquei os conhecimentos que tinha e fui a traz dos
                            que eu não tinha para fazer o front-end da aplicação nessa tecnologia, ainda estou
                            estudando, então é provável que daqui a uma semana eu veja muita coisa que fiz e que
                            posso melhorar. Sempre da pra melhorar!

                            Valeu!
                                </p>
                    </div>
                </Jumbotron>
            </>
        );
    }

}