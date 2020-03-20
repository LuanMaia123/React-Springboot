import React, { Component } from 'react';
import { Modal, Button, Form, Row, Col, Image } from 'react-bootstrap';
import axios from 'axios';
import moment from 'moment';
import DespesasAgrupadas from './DespesasAgrupadas';

export default class ModalDeputadoDetalhe extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;

    }

    initialState = {
        nomeCivil: '', dataNascimento: '', sexo: '', siglaPartido: '', urlFoto: '', despesas: [], detalhesDespesas: false
    };

    componentDidMount() {
        this.obterDetalheDeputado(this.props.deputadoid, this.state.detalhesDespesas);
    }

    obterDetalheDeputado(id, detalhada) {
        axios.get("http://localhost:8080/rest/deputados/detalhe", {
            params: {
                deputadoId: id,
                detalhada: detalhada
            }
        }).then(response => response.data)
            .then((data) => {
                this.setState({
                    nomeCivil: data.nomeCivil,
                    dataNascimento: data.dataNascimento,
                    sexo: data.sexo,
                    siglaPartido: data.ultimoStatus.siglaPartido,
                    urlFoto: data.ultimoStatus.urlFoto,
                    despesas: data.despesas
                })
            })
    }


    render() {
        moment.locale('pt-BR');
        const deputadoId = this.props.deputadoid;
        const { nomeCivil, dataNascimento, sexo, siglaPartido, urlFoto, despesas, detalhesDespesas } = this.state;
        const total = despesas && despesas.reduce(function (cnt, o) { return cnt + o.valorLiquido; }, 0)
        let mostrarDetalhe = () => this.setState({ detalhesDespesas: true })
        return (
            <Modal
                {...this.props}
                size="lg"
                aria-labelledby="contained-modal-title-vcenter"
                centered
            >
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                        Detalhes do parlamentar
        </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group as={Row}>
                            <Col sm="2">
                                <Image src={urlFoto} roundedCircle width="100" height="120" />
                            </Col>
                            <Col sm="10">
                                <Form.Label column sm="2">Nome:</Form.Label>
                                <Form.Label column sm="10">{nomeCivil ? nomeCivil : ""}</Form.Label>
                                <Form.Label column sm="2">Sexo:</Form.Label>
                                <Form.Label column sm="10">{sexo ? sexo === "M" ? "Masculino" : "Feminino" : ""}</Form.Label>
                                <Form.Label column sm="2">Data de Nascimento:</Form.Label>
                                <Form.Label column sm="10">{dataNascimento ? moment(dataNascimento).format('DD/MM/YYYY') : ""}</Form.Label>
                                <Form.Label column sm="2">Partido:</Form.Label>
                                <Form.Label column sm="10">{siglaPartido ? siglaPartido : ""}</Form.Label>
                            </Col>
                        </Form.Group>

                        <Form.Group as={Row}>
                            <Col sm="12">
                                <DespesasAgrupadas
                                    despesas={despesas}
                                    total={total}
                                    onHide={mostrarDetalhe}
                                    deputadoId={deputadoId}
                                />
                            </Col>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="outline-info" onClick={this.props.onHide}>Fechar</Button>
                </Modal.Footer>
            </Modal>
        );
    }
}