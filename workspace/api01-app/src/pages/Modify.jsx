import { useEffect, useRef, useState } from "react";
import axiosInstance from "../auth/Auth";
import { useNavigate, useParams } from "react-router-dom";

const Modify = () =>{
  const {tno} = useParams();
  const navigate = useNavigate();
  const titleRef = useRef(null);
  const dateRef = useRef(null);
  const writerRef = useRef(null);

  const getTodo = async () =>{
    const response = await axiosInstance.get(`http://localhost:8080/api/todo/${tno}`);
    return response.data;
  }
  useEffect(()=>{
    getTodo().then((it)=>{
      titleRef.current.value = it.title;
      dateRef.current.value = it.dueDate;
      writerRef.current.value = it.writer;
    }).catch((error)=>{
      alert("에러가 발생 했습니다.")
      console.log(error);
    })
  },[])

  const handleSubmit = async (e) =>{
    e.preventDefault();
    const formData = new FormData();
    formData.append("title",titleRef.current.value);
    formData.append("dueDate",dateRef.current.value);
    formData.append("writer",writerRef.current.value);
    try{
      const response = await axiosInstance.put(
        `http://localhost:8080/api/todo/${tno}`,formData, {headers:{"Content-Type":"application/json"}});
      alert(`${tno} 를 수정했습니다.`);
      navigate(`/read/${tno}`);
    }catch(error){
      alert(`에러가 발생했습니다.`);
      console.log(error);
    };
  }
  const handleDelete = async()=>{
    try{
      const response = await axiosInstance.delete(`http://localhost:8080/api/todo/${tno}`);
      alert(`${tno} 가 삭제되었습니다.`);
      navigate("/");
    }catch(error){
      alert("에러가 발생했습니다.");
      console.log(error);
    }
  }
  
    return(
      <div>
        <h1>Modify</h1>
        <form onSubmit={handleSubmit}>
          <p><input ref={titleRef} type="text" placeholder="제목 입력" /></p>
          <p><input ref={dateRef} type="date" /></p>
          <p><input ref={writerRef} type="text" placeholder="작성자 입력" /></p>
          <button type="submit">등록</button>
          <button type="button" onClick={handleDelete}>삭제</button>
        </form>
      </div>
    );
  
}
export default Modify;