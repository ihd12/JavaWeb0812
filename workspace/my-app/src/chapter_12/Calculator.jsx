import React,{useState} from "react";
import TemperatureInput from "./TemperatureInput";
// 물이 끊는지 여부를 출력하는 함수
function BoilingVerdict(props){
  if(props.celsius >= 100){
    return <p>물이 끓습니다.</p>
  }
  return <p>물이 끓지 않습니다.</p>
}
// 화씨를 섭씨로 변경하는 수식
function toCelsius(fahrenheit){
  return ((fahrenheit-32) * 5) / 9;
}
// 섭씨를 화씨로 변경하는 수식
function toFahrenheit(celsius){
  return (celsius*9) / 5 + 32;
}
function tryConvert(temperature, convert){
  const input = parseFloat(temperature);
  // input값이 숫자인 아닌지 확인하는 if문
  if(Number.isNaN(input)){
    return "";
  }
  // 매개변수로 가지고온 함수를 실행 섭씨->화씨 , 화씨->섭씨
  const output = convert(input);
  const rounded = Math.round(output * 1000)/1000;
  return rounded.toString();
}
function Calculator(props){
  const [temperature, setTemperature] = useState("");
  const [scale,setScale] = useState("c");

  // 섭씨 설정시 value를 변경
  const handleCelsiusChange = (temperature) =>{
    setTemperature(temperature);
    setScale("c");
  }
  //화씨 온도 설정
  const handleFahrenheitChange = (temperature) =>{
    setTemperature(temperature);
    setScale("f");
  }
  //섭씨 온도를 설정
  const celsius = scale ==="f" ? tryConvert(temperature,toCelsius) : temperature;
  //화씨 온도 설정
  const fahrenheit = scale === "c" ? tryConvert(temperature,toFahrenheit) : temperature;
  return (
    <div>
      <TemperatureInput
      scale="c"
      temperature={celsius}
      onTemperatureChange={handleCelsiusChange} />
      <TemperatureInput
      scale="f"
      temperature={fahrenheit}
      onTemperatureChange={handleFahrenheitChange} />
      <BoilingVerdict celsius={parseFloat(celsius)} />
    </div>
  );
}
export default Calculator;