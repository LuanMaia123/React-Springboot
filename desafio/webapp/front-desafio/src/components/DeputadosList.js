import React, { Component } from 'react';
import { Card, Table, ButtonGroup, Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faEye } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';


export default class DeputadosList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            parlamentares: []
        };
    }

    componentDidMount() {
        this.obterTodosDeputadosPaginado();
    }

    obterTodosDeputadosPaginado() {
        axios.get("http://localhost:8080/rest/teste")
            .then(response => response.data)
            .then((data) => {
                this.setState({ parlamentares: data })
            })
    }

    render() {
        return (
            <Card className="border border-dark bg-dark text-white">
                <Card.Header style={{ textAlign: 'left' }}><FontAwesomeIcon icon={faList} /> Lista de Parlamentares</Card.Header>
                <Card.Body>
                    <Table bordered striped hover variant="dark">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Partido</th>
                                <th>UF</th>
                                <th>Visualizações</th>
                                <th>Detalhes</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.parlamentares.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="6">Sem resultados</td>
                                    </tr> :
                                    this.state.parlamentares.map((deputado) => (
                                        <tr key={deputado.id}>
                                            <td>{deputado.nome}</td>
                                            <td>{deputado.siglaPartido}</td>
                                            <td>{deputado.siglaUf}</td>
                                            <td>{deputado.id}</td>
                                            <td>
                                                <ButtonGroup>
                                                    <Button size="sm" variant="outline-primary" title="Detalhar"><FontAwesomeIcon icon={faEye} /></Button>
                                                </ButtonGroup>
                                            </td>
                                        </tr>
                                    ))
                            }

                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}