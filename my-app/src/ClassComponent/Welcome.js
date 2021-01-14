import React,{ Component } from 'react';




class Welcome extends Component {
 componentDidMount(){
 console.log(this.props.history);
 }
  render(){
   
  return (
   <div>
<h1>Welcome {this.props.location.state} !</h1>
   </div>
    
  );
}
}

export default Welcome;
