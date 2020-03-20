import React, { Component } from 'react';
import CurrencyFormat from 'react-currency-format';
import { Card, Table, Button, Modal } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList } from '@fortawesome/free-solid-svg-icons';
import moment from 'moment';
import axios from 'axios';

export default class DespesasDetalhadas extends Component {

    constructor(props) {
        super(props);
        this.state = {
            despesas:[],
            mes:this.props.mes,
            deputadoId:this.props.deputadoid
        }
    }

    componentDidMount() {
        this.obterDetalheDespesa(this.state.deputadoId, this.state.mes);
    }

    obterDetalheDespesa(id, mes) {
        axios.get("http://localhost:8080/rest/deputados/mes", {
            params: {
                deputadoId: id,
                mes: mes
            }
        }).then(response => response.data)
            .then((data) => {
                this.setState({
                    despesas: data
                })
            })
    }

    render() {
        const despesas = this.state.despesas;
        return (
            <Modal
                {...this.props}
                size="lg"
                aria-labelledby="contained-modal-title-vcenter"
                centered
            >
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                        Detalhes das despesas
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Card className="border border-dimgray bg-dimgray">
                        <Card.Header style={{ textAlign: 'left', }}>
                            <FontAwesomeIcon icon={faList} /> Despesas detalhadas
                        </Card.Header>
                        <Card.Body>
                            <Table bordered striped hover >
                                <thead>
                                    <tr>
                                        <th>Tipo de despesa</th>
                                        <th>Tipo de Documento</th>
                                        <th>Data da Despesa</th>
                                        <th>Valor Líquido</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        despesas.length === 0 ?
                                            <tr align="center">
                                                <td colSpan="6">Sem resultados</td>
                                            </tr> :
                                            despesas.map((despesa) => (
                                                <tr key={despesa.tipoDespesa}>
                                                    <td>{despesa.tipoDespesa}</td>
                                                    <td>{despesa.tipoDocumento}</td>
                                                    <td>{despesa.dataDocumento ? moment(despesa.dataDocumento).format('DD/MM/YYYY') : ""}</td>
                                                    <td><CurrencyFormat value={despesa.valorLiquido} displayType={'text'} thousandSeparator={true} prefix={'R$'} /></td>
                                                </tr>
                                            ))
                                    }
                                </tbody>
                            </Table>
                        </Card.Body>
                    </Card>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="outline-info" onClick={this.props.onHide}>Fechar</Button>
                </Modal.Footer>
            </Modal>
        );
    }
}