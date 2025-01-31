import React, {useState} from "react";
// 커스텀 훅 : 개발자가 직접 만들어 사용하는 훅
// 여러 컴포넌트에 반복적으로 사용되는 로직을 재사용 하기위해 만드는 훅
function useCounter(initialValue){
  // count가 변경되면 화면이 재랜더링 됩니다.
  const[count, setCount] = useState(initialValue);
  //count를 1씩 더하는 함수
  const increaseCount = () => setCount(count => count+1);
  //count를 1씩 빼는 함수,  0보다 작아질 수 없음
  const decreaseCount = () => setCount(count => Math.max(count-1,0));
  // 커스텀 훅의 내용을 돌려줌
  return [count, increaseCount, decreaseCount];
}
export default useCounter;