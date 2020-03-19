import React, { Component } from 'react';
import { Modal, Button, Form, Row, Col, Image, Card, Table } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import moment from 'moment';

export default class ModalDeputadoDetalhe extends Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;

    }

    initialState = {
        nomeCivil: '', dataNascimento: '', sexo: '', siglaPartido: '', urlFoto: '', despesas: []
    };

    componentDidMount() {
        this.obterDetalheDeputado(this.props.deputadoid);
    }

    obterDetalheDeputado(id) {
        axios.get("http://localhost:8080/rest/deputados/detalhe", {
            params: {
                deputadoId: id
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
        const { nomeCivil, dataNascimento, sexo, siglaPartido, urlFoto, despesas } = this.state;
        const total = despesas && despesas.reduce( function(cnt,o){ return cnt + o.valorLiquido; }, 0)
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
                                <Form.Label column sm="10">{nomeCivil}</Form.Label>
                                <Form.Label column sm="2">Sexo:</Form.Label>
                                <Form.Label column sm="10">{sexo === "M" ? "Masculino" : "Feminino"}</Form.Label>
                                <Form.Label column sm="2">Data de Nascimento:</Form.Label>
                                <Form.Label column sm="10">{moment(dataNascimento).format('MM/DD/YYYY')}</Form.Label>
                                <Form.Label column sm="2">Partido:</Form.Label>
                                <Form.Label column sm="10">{siglaPartido}</Form.Label>
                            </Col>
                        </Form.Group>

                        <Form.Group as={Row}>
                            <Col sm="12">
                                <Card className="border border-dimgray bg-dimgray">
                                    <Card.Header style={{ textAlign: 'left', }}><FontAwesomeIcon icon={faList} /> Despesas</Card.Header>
                                    <Card.Body>
                                        <Table bordered striped hover >
                                            <thead>
                                                <tr>
                                                    <th>Mês</th>
                                                    <th>Despesa</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {
                                                    despesas.length === 0 ?
                                                        <tr align="center">
                                                            <td colSpan="6">Sem resultados</td>
                                                        </tr> :
                                                        despesas.map((despesa) => (
                                                            <tr key={despesa.valorLiquido}>
                                                                <td>{despesa.mes}</td>
                                                                <td>{despesa.valorLiquido}</td>
                                                            </tr>
                                                        ))
                                                }
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <th id="total" >Total:</th>
                                                    <td>{total} </td>
                                                </tr>
                                            </tfoot>
                                        </Table>
                                    </Card.Body>                        
                                </Card>
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