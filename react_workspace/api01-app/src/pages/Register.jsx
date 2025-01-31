import { useRef } from "react";
import axiosInstance from "../auth/Auth";
import { useNavigate } from "react-router-dom";

const Register = () =>{
  const navigate = useNavigate();
  const titleRef = useRef(null);
  const dateRef = useRef(null);
  const writerRef = useRef(null);

  const handleSubmit = async (e) =>{
    e.preventDefault();
    const formData = new FormData();
    formData.append("title",titleRef.current.value);
    formData.append("dueDate",dateRef.current.value);
    formData.append("writer",writerRef.current.value);
    try{
      const response = await axiosInstance.post(
        "http://localhost:8080/api/todo/",formData, {headers:{"Content-Type":"application/json"}});
      const tno = response.data.tno;
      alert(`${tno} 를 추가했습니다.`);
      navigate("/");
    }catch(error){
      alert(`에러가 발생했습니다.`);
      console.log(error);
    };
  }
  return(
    <div>
      <h1>Register</h1>
      <form onSubmit={handleSubmit}>
        <p><input ref={titleRef} type="text" placeholder="제목 입력" /></p>
        <p><input ref={dateRef} type="date" /></p>
        <p><input ref={writerRef} type="text" placeholder="작성자 입력" /></p>
        <button type="submit">등록</button>
      </form>
    </div>
  );
}
export default Register;