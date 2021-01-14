import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './Formdetail.css';

const FormDetail = (props) =>{
	return (
<div className="wrapper fadeInDown">
  <div id="formContent">
   
    <h2 className="active"> Sign In </h2>

  
    <form>
      <input type="email" id="login"  name="login" placeholder="login" value={props.username} onChange={props.namechange}></input>
      <input type="password" id="password"  name="login" placeholder="password" value={props.password} onChange={props.pwdInputHandler}></input>
      <input type="submit" style={props.styleProp} value="Log In" onClick={props.submitdetailHandler}></input>
    </form>

   
    

  </div>
</div>
)
}

export default FormDetail;