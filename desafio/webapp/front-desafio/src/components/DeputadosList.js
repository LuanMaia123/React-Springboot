import React, { Component } from 'react';
import { Card, Table, ButtonGroup, Button, InputGroup } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faEye, faStepBackward, faStepForward } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import ModalDeputadoDetalhe from './ModalDeputadoDetalhe';



export default class DeputadosList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            parlamentares: [],
            paginaAtual: 1,
            itensPorPagina: 5,
            modalDeputadoShow: false
        };
    }

    componentDidMount() {
        this.obterTodosDeputadosPaginado(this.state.paginaAtual);
    }

    obterTodosDeputadosPaginado(pagina) {
        axios.get("http://localhost:8080/rest/deputados/lista", {
            params: {
                paginaAtual: pagina,
                itensPorPagina: 5
            }
        })
            .then(response => response.data)
            .then((data) => {
                this.setState({ parlamentares: data })
            })
    }

    paginaAnterior = () => {
        if (this.state.paginaAtual > 1) {
            const valor = this.state.paginaAtual - 1;
            this.setState({
                paginaAtual: valor
            })
            this.obterTodosDeputadosPaginado(valor)
        }
    };

    proximaPagina = () => {
        const valor = this.state.paginaAtual + 1;
        this.setState({
            paginaAtual: valor
        })
        this.obterTodosDeputadosPaginado(valor)
    };

    render() {
        const { parlamentares, paginaAtual } = this.state;
        let modalDeputadoClose = () => this.setState({ modalDeputadoShow: false },() => {
            this.obterTodosDeputadosPaginado(this.state.paginaAtual);
          })

        return (
            <Card style={{ marginBottom: '8rem' }} className="border border-dark bg-dark text-white">
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
                                parlamentares.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="6">Sem resultados</td>
                                    </tr> :
                                    parlamentares.map((parlamentar) => (
                                        <tr key={parlamentar.id}>
                                            <td>{parlamentar.nome}</td>
                                            <td>{parlamentar.siglaPartido}</td>
                                            <td>{parlamentar.siglaUf}</td>
                                            <td>{parlamentar.visualizacoes}</td>
                                            <td>
                                                <ButtonGroup>
                                                    <Button
                                                        onClick={() => this.setState({ modalDeputadoShow: true, id: parlamentar.id })}
                                                        size="sm"
                                                        variant="outline-primary"
                                                        title="Detalhar">
                                                        <FontAwesomeIcon icon={faEye} />
                                                    </Button>
                                                </ButtonGroup>
                                            </td>

                                        </tr>
                                    ))

                            }
                            {this.state.modalDeputadoShow &&
                                <ModalDeputadoDetalhe
                                    show={this.state.modalDeputadoShow}
                                    onHide={modalDeputadoClose}
                                    deputadoid={this.state.id}
                                />}

                        </tbody>
                    </Table>
                </Card.Body>
                <Card.Footer>
                    <div style={{ display: 'flex', justifyContent: 'center' }}>
                        <InputGroup.Append>
                            <Button onClick={this.paginaAnterior} style={{ marginRight: '10px' }} type="button" variant="outline-info" disabled={paginaAtual === 1 ? true : false}>
                                <FontAwesomeIcon icon={faStepBackward} /> Anterior
                                </Button>
                            <Button onClick={this.proximaPagina} type="button" variant="outline-info">
                                <FontAwesomeIcon icon={faStepForward} /> Próximo
                                 </Button>
                        </InputGroup.Append>
                    </div>
                </Card.Footer>
            </Card>
        );
    }
}