import React, {Component} from 'react';
import {
  View,StyleSheet,Text,Button
} from 'react-native';

import {NativeModules} from 'react-native';

export default class App extends Component{

  constructor(props) {
    super(props);
   
    this.state = {
      batteryLevel: '',
    };
   }

   getBatteryLevel = (callback) => {
    NativeModules.BatteryStatus.getBatteryStatus(callback);
   }

  componentDidMount() {
    this.getBatteryLevel((batteryLevel) => {
      console.log("BatteryLevel: ",batteryLevel);  
      this.setState({ batteryLevel: batteryLevel}); 
      });
  }

  handleChange() {
    this.setState({batteryLevel});
  }

  render(){
  return (
    <View style = {styles.container}>
      <Text style={{color: 'blue', fontSize: 20, fontWeight:'bold'}}>
       BatteryLevel: {this.state.batteryLevel}
      </Text>
    </View>
  );
}
}

const styles = StyleSheet.create({
  container: {
    flex:1,
    justifyContent: 'center',
    alignItems: 'center',
  },

});

