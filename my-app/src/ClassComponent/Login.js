import React,{ Component } from 'react';

import FormDetail from './FuncComponent/FormDetail';




class Login extends Component {
  state={
     "username":"",
    "password":""
  };
componentDidMount(){
  console.log("inside login comp...")
  console.log(this.props);
}
 
nameChangeInputHandler=(event)=>{
   this.setState({
   "username":event.target.value
   })

}

pwdChangeInputHandler=(event)=>{
   this.setState({
   "password":event.target.value
   })
}
submitDetails=(event)=>{
event.preventDefault();
this.props.history.push("/home",this.state.username);
//console.log(this.props);
}

  render(){
    let style=null;
   if(this.state.password.length>6 && this.state.username.length>6){
style={
  backgroundColor: "#56baed"
}
   }
  return (
   
    <div className="App">
      <FormDetail styleProp={style} submitdetailHandler={this.submitDetails.bind(this)} username={this.state.username} password ={this.state.password} namechange={this.nameChangeInputHandler.bind(this)} pwdInputHandler={this.pwdChangeInputHandler.bind(this)}/>
     
    </div>
    
    
  );
}
}

export default Login;
