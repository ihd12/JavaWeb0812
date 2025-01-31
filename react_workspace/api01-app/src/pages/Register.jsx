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
    <div className="row content">
          <div className="col">
              <div className="card">
                  <div className="card-header">
                      Featured
                  </div>
                  <div className="card-body">
                      <form onSubmit={handleSubmit}>
                          <div className="input-group mb-3">
                              <span className="input-group-text">Title</span>
                              <input type="text" ref={titleRef} name="title" className="form-control" placeholder="Title" />
                          </div>
                          <div className="input-group mb-3">
                              <span className="input-group-text">DueDate</span>
                              <input type="date" ref={dateRef} name="dueDate" className="form-control" />
                          </div>
                          <div className="input-group mb-3">
                              <span className="input-group-text">Writer</span>
                              <input type="text" ref={writerRef} name="writer" className="form-control" />
                          </div>
                          <div className="my-4">
                              <div className="float-end">
                                  <button type="submit" className="btn btn-primary">등록</button>
                                  <button type="reset" className="btn btn-secondary">초기화</button>
                              </div>
                          </div>
                      </form>
                  </div>
              </div>
          </div>
      </div>
  );
}
export default Register;