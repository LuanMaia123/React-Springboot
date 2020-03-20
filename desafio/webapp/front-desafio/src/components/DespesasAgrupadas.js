import React, { Component } from 'react';
import CurrencyFormat from 'react-currency-format';
import { Card, Table, Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import DespesasDetalhadas from './DespesasDetalhadas';


export default class DespesasAgrupadas extends Component {

    constructor(props) {
        super(props);
        this.state = {
            modalDespesaShow: false
        };
    }



    render() {
        const despesas = this.props.despesas;
        const total = this.props.total;
        let modalDespesaClose = () => this.setState({ modalDespesaShow: false })
        return (
            <Card className="border border-dimgray bg-dimgray">
                <Card.Header style={{ textAlign: 'left', }}>
                    <FontAwesomeIcon icon={faList} /> Despesas
                </Card.Header>
                <Card.Body>
                    <Table bordered striped hover >
                        <thead>
                            <tr>
                                <th>Mês</th>
                                <th>Despesa</th>
                                <th>Ação</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                despesas.length === 0 ?
                                    <tr align="center">
                                        <td colSpan="6">Sem resultados</td>
                                    </tr> :
                                    despesas.map((despesa) => (
                                        <tr key={despesa.mes}>
                                            <td>{despesa.mesNome}</td>
                                            <td><CurrencyFormat value={despesa.valorLiquido} displayType={'text'} thousandSeparator={true} prefix={'R$'} /></td>
                                            <td>
                                                <Button style={{ marginLeft: '10px' }}
                                                    onClick={() => this.setState({ modalDespesaShow: true, deputadoId: this.props.deputadoId, mes: despesa.mes })}
                                                    variant="outline-primary"
                                                    title="Detalhar">
                                                    <FontAwesomeIcon icon={faInfoCircle} /> Detalhar
                                            </Button>
                                            </td>
                                        </tr>
                                    ))
                            }
                            {this.state.modalDespesaShow &&
                                <DespesasDetalhadas
                                    show={this.state.modalDespesaShow}
                                    onHide={modalDespesaClose}
                                    deputadoid={this.state.deputadoId}
                                    mes={this.state.mes}
                                />}
                        </tbody>
                        <tfoot>
                            <tr>
                                <th id="total" colSpan="2" >Total:</th>
                                <td><CurrencyFormat value={total} displayType={'text'} thousandSeparator={true} prefix={'R$'} /> </td>
                            </tr>
                        </tfoot>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}