import React,{useRef} from "react";
function TextInputWithFocusButton(props){
  const inputElem = useRef(null);

  const onButtonClick = () => {
    inputElem.current.focus();
    inputElem.current.value = "123";
  }
  return(
    <>
      <p>{inputElem.current}</p>
      <input ref={inputElem} type="text" />
      <button onClick={onButtonClick}>Focus the input</button>
    </>
  )
}
export default TextInputWithFocusButton;