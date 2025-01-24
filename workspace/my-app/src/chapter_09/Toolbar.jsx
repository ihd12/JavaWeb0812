import React from "react";

const style = {
  wrapper : {
    padding:16,
    display:"flex",
    flexDirection:"row",
    borderBottom: "1px solid grey"
  },
  greeting :{
    marginRight:8,
  },
}
function Toolbar(props){
  const {isLoggedIn, onClickLogin, onClickLogout} = props;
  return(
    <div style={style.wrapper}>
      {/* 인라인 if : 조건식 && 실행문*/}
      {isLoggedIn && <span style={style.greeting}>환영합니다!</span>}
      {/* 삼항연산자 : 조건식 ? true실행문 : false실행문 */}
      {isLoggedIn ? (<button onClick={onClickLogout}>로그아웃</button>)
       : (<button onClick={onClickLogin}>로그인</button>)}
    </div>
  );
}
export default Toolbar;