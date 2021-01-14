import React,{ Component } from 'react'

import Login from './ClassComponent/Login';
import Welcome from './ClassComponent/Welcome';

import { BrowserRouter,Router,Route, NavLink, Switch, Redirect  } from 'react-router-dom';


class Container extends Component {

  state={
     "username":"",
  };
  componentDidMount(){
  	console.log(this.props);
  }
  render(){
  	console.log(this.props);
   
  return (
  <div>
  <Switch>
  <Route path="/"  exact component={Login}/>
           <Route path="/login"  component={Login}/>
            <Route path="/home"  component={Welcome}/>
           </Switch>
  </div>
    
  );
}
}

export default Container;
