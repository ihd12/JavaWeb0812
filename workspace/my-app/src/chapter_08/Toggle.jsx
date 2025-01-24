import React, {useState} from "react";

function Toggle(props){
  const [isToggleOn, setIsToggleOn] = useState(true);
  // function handleClick(){
  //   setIsToggleOn((isToggleOn)=>!isToggleOn);
  // }
  const handleClick = (str) => {
    console.log(str);
    setIsToggleOn((isToggleOn)=>!isToggleOn);
  }
  return (
    <button onClick={()=>handleClick("a")}>
      {isToggleOn ? "켜짐" : "꺼짐"}
    </button>
  );
}
export default Toggle;