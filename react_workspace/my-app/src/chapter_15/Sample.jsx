import React from "react";
import styled from "styled-components";
import './style.css';

const color = 'black';
const Button = styled.button`
  color:${props => props.dark ? "white" : "dark"};
  background:${props => props.dark ? "black" : "white"};
  border: 1px solid black;
`;
function Sample(props){
  return(
    <div>
      <Button>Normal</Button>
      <Button dark>Dark</Button>
      {/* 리액트에서는 class가 아닌 className으로 설정 */}
      <div className="wrap">
        <p>안녕하세요. 소플입니다.</p>
      </div>
    </div>
  );
}
export default Sample;