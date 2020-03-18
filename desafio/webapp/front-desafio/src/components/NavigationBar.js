import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import { Link } from 'react-router-dom';

class NavigationBar extends React.Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark" expand="lg">
                <Link to={""} className="navbar-brand">
                    Tecnologias
                </Link>
                <Nav className="mr-auto">
                    <Link to={"DeputadosList"} className="nav-link">Deputados</Link>
                </Nav>
            </Navbar>
        );
    }
}

export default NavigationBar;