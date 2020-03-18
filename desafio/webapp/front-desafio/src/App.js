import React from 'react';
import './App.css';
import { Container, Row, Col } from 'react-bootstrap';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';


import NavigationBar from './components/NavigationBar';
import Welcome from './components/welcome';
import Footer from './components/Footer';
import DeputadosList from './components/DeputadosList';

function App() {
  const marginTop = {
    marginTop: "20px"
  }
  return (
    <Router>
      <NavigationBar />      
      <Container style={{ textAlign: 'center' }}>
        <Row>
          <Col lg={12} style={marginTop}>
            <Switch>
              <Route path="/" exact component={Welcome} />
              <Route path="/DeputadosList" exact component={DeputadosList} />
            </Switch>
          </Col>
        </Row>
        <Footer />
      </Container>
    </Router>
  );
}

export default App;
