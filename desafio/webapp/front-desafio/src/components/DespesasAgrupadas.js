import React, { Component } from 'react';
import CurrencyFormat from 'react-currency-format';
import { Card, Table, Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faInfoCircle } from '@fortawesome/free-solid-svg-icons';

export default class DespesasAgrupadas extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        const despesas = this.props.despesas;
        const total = this.props.total;
        return (
            <Card className="border border-dimgray bg-dimgray">
                <Card.Header style={{ textAlign: 'left', }}>
                    <FontAwesomeIcon icon={faList} /> Despesas
                <Button style={{ marginLeft: '10px' }}
                        onClick={this.props.onHide}
                        size="sm"
                        variant="outline-primary"
                        title="Detalhar">
                        <FontAwesomeIcon icon={faInfoCircle} /> Detalhar
                </Button>

                </Card.Header>
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
                                            <td><CurrencyFormat value={despesa.valorLiquido} displayType={'text'} thousandSeparator={true} prefix={'R$'} /></td>
                                        </tr>
                                    ))
                            }
                        </tbody>
                        <tfoot>
                            <tr>
                                <th id="total" >Total:</th>
                                <td><CurrencyFormat value={total} displayType={'text'} thousandSeparator={true} prefix={'R$'} /> </td>
                            </tr>
                        </tfoot>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}