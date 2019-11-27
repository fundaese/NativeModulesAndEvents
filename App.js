import React, {Component} from 'react';
import {
  View,
  StyleSheet,
  Text,
  Button,
  NativeModules,
  TouchableOpacity,
  NativeEventEmitter
} from 'react-native';

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
    this.getBatteryLevel((batteryLevel) => {                                //native module from android           
      console.log("BatteryLevel: ",batteryLevel);  
      this.setState({ batteryLevel: batteryLevel}); 
    })

   const eventEmitter = new NativeEventEmitter(NativeModules.ToastExample); //event listener
    eventEmitter.addListener('batteryLow', (event) => {
       alert("Low Battery!", event.battery) 
    })
  }

  render(){
  return (
    <View style = {styles.container}>
      <Text style={{color: 'blue', fontSize: 20, fontWeight:'bold'}}>
       BatteryLevel:{this.state.batteryLevel}%
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

