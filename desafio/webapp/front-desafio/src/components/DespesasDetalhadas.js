import React, { Component } from 'react';
import CurrencyFormat from 'react-currency-format';
import { Card, Table, Button } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faList, faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import moment from 'moment';

export default class DespesasDetalhadas extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        const despesas = this.props.despesas;
        return (
            <Card className="border border-dimgray bg-dimgray">
                <Card.Header style={{ textAlign: 'left', }}>
                    <FontAwesomeIcon icon={faList} /> Despesas detalhadas
                    <Button style={{ marginLeft: '10px' }}
                        onClick={this.props.onHide}
                        size="sm"
                        variant="outline-primary"
                        title="Detalhar">
                        <FontAwesomeIcon icon={faInfoCircle} /> Agrupar
                </Button>
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
                                            <td>{despesa.dataDocumento ? moment(despesa.dataDocumento).format('MM/DD/YYYY') : ""}</td>
                                            <td><CurrencyFormat value={despesa.valorLiquido} displayType={'text'} thousandSeparator={true} prefix={'R$'} /></td>
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