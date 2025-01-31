import React, {useState} from "react";

function FancyBorder(props){
  return(
    <div className={'FancyBorder FancyBorder-'+props.color}>
      {props.children}
    </div>
  );
}

function Dialog(props){
  return (
    <FancyBorder color="blue">
      <h1 className="Dialog-title">
        {props.title}
      </h1>
      <p className="Dialog-message">
        {props.message}
      </p>
      {props.children}
    </FancyBorder>
  );
}
function SignUpDialog(props){
  const [nickname, setNickname] = useState('');
  const handleChange = (event) =>{
    setNickname(event.target.value);
  }
  const handleSignUp = () =>{
    alert(`어서오세요, ${nickname}님!`);
  }
  return (
    <Dialog 
      title="화성 탐사 프로그램"
      message = "닉네임을 입력해 주세요.">
      <input value={nickname} onChange={handleChange} />
      <button onClick={handleSignUp}>
        가입하기
      </button>
    </Dialog>
  );
}
export default SignUpDialog;