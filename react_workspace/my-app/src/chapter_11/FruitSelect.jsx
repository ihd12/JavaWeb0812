import React,{useState} from "react";

function FruitSelect(props){
  const[value,setValue] = useState('grape');
  const handleChange = (event) =>{
    setValue(event.target.value);
  }
  const handleSubmit = (event) =>{
    alert(`선택한 과일: ${value}`);
    event.preventDefault();
  }
  return(
    <form onSubmit={handleSubmit}>
      <label>
        과일을 선택하세요:
        <select value={value} onChange={handleChange} >
          <option value="apple">사과</option>
          <option value="banana">바나나</option>
          <option value="grape">포도</option>
          <option value="watermelon">수박</option>
        </select>
      </label>
      {/* file의 경우 읽기 전용으로 비제어 컴포넌트라고 불림 */}
      <input type="file" />
      <button type="submit">제출</button>
    </form>
  )
}
export default FruitSelect;