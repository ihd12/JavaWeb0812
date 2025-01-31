import React, {useState, useEffect} from "react";
import useCounter from "./useCounter"

const MAX_CAPACITY = 10;
function Accommodate(props){
  const[isFull, setIsFull] = useState(false);
  const[count , increaseCount, decreaseCount] = useCounter(0);

  // componentDidMount()와 같은 역할을 하는 useEffect()
  useEffect(()=>{
    console.log("마운트() 실행");
  },[]);

  //componentDidUpdate와 같은 역할을 하는 useEffect() 사용법
  // useEffect(이펙트 함수) : 컴포넌트가 변경될때마다 실행되는 함수
  useEffect(()=>{
    console.log("====================");
    console.log("useEffect() is called.")
    console.log(`isFull: ${isFull}`);
  })
  // count가 변경될때만 실행되는 useEffect 함수
  useEffect(() =>{
    setIsFull(count >= MAX_CAPACITY);
    console.log(`Current count value: ${count}`);
  },[count]);

  // componentWillunmount()와 같은 역할을 하는 useEffect()
  useEffect(()=>{
    return () =>{
      console.log("언마운트() 실행");
    }
  });

  return(
    <div style={{padding:16}}>
      <p>{`총 ${count}명 수용했습니다.`}</p>
      <button onClick={increaseCount} disabled={isFull}>입장</button>
      <button onClick={decreaseCount}>퇴장</button>
      {isFull && <p style={{color:"red"}}>정원이 가득찼습니다.</p>}
    </div>
  )
}
export default Accommodate;