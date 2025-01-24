import React from "react";
import styled from "styled-components";
const color = 'black';
const Wrapper = styled.div`
  padding:1em;
  background:${color};
`;
const Title = styled.h1`
  font-size:1.5em;
  color:white;
  text-align:center;
`;
function MainPage(props){
  return(
    <Wrapper>
      <Title>
        안녕, 리액트!
      </Title>
    </Wrapper>
  );
}
export default MainPage;