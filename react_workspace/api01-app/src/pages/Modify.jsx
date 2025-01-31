import { useEffect, useRef, useState } from "react";
import axiosInstance from "../auth/Auth";
import { Link, useNavigate, useParams } from "react-router-dom";

const Modify = () =>{
  const {tno} = useParams();
  const navigate = useNavigate();
  const titleRef = useRef(null);
  const dateRef = useRef(null);
  const writerRef = useRef(null);
  const completeRef = useRef(null);

  const getTodo = async () =>{
    const response = await axiosInstance.get(`http://localhost:8080/api/todo/${tno}`);
    return response.data;
  }
  useEffect(()=>{
    getTodo().then((it)=>{
      titleRef.current.value = it.title;
      dateRef.current.value = it.dueDate;
      writerRef.current.value = it.writer;
      completeRef.current.checked = it.complete;
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
    formData.append("complete", completeRef.current.checked)
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
        <div className="row content">
            <div className="col">
                <div className="card">
                    <div className="card-header">
                        Featured
                    </div>
                    <div className="card-body">
                        <form onSubmit={handleSubmit}>
                        <div className="input-group mb-3">
                            <span className="input-group-text">TNO</span>
                            <input type="text" name="tno" className="form-control" defaultValue={tno} readOnly />
                        </div>
                        <div className="input-group mb-3">
                            <span className="input-group-text">Title</span>
                            <input type="text" ref={titleRef} name="title" className="form-control" />
                        </div>
                        <div className="input-group mb-3">
                            <span className="input-group-text">DueDate</span>
                            <input type="date" ref={dateRef} name="dueDate" className="form-control" />
                        </div>
                        <div className="input-group mb-3">
                            <span className="input-group-text">Writer</span>
                            <input type="text" ref={writerRef} name="writer" className="form-control" readOnly />
                        </div>
                        <div className="form-check">
                            <label className="form-check-label">
                                Finished &nbsp;
                            </label>
                            <input type="checkbox" ref={completeRef} name="finished" className="form-check-input" />
                        </div>
                        <div className="input-group mb-3">
                            <span className="input-group-text">Image</span>
                            <input type="file" name="file" className="form-control" />
                        </div>

                        <div className="my-4">
                            <div className="float-end">
                                <button type="button" className="btn btn-danger" onClick={handleDelete}>Remove</button>
                                <button type="submit" className="btn btn-primary">Modify</button>
                                <Link className="btn btn-secondary" to={"/"}>List</Link>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
      </div>
    );
  
}
export default Modify;