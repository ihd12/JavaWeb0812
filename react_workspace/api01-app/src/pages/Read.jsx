import { Link, useParams } from "react-router-dom";
import axiosInstance from "../auth/Auth";
import { useEffect, useState } from "react";

const Read = () =>{
  const {tno} = useParams(); 
  const [data,setData] = useState();
  const [isDataLoaded, setIsDataLoaded] = useState(false);
  const getTodo = async () =>{
    const response = await axiosInstance.get(`http://localhost:8080/api/todo/${tno}`);
    return response.data;
  }
  useEffect(()=>{
    getTodo().then((it)=>{
      setData(it);
      setIsDataLoaded(true);
    }).catch((error)=>{
      alert("에러가 발생 했습니다.")
      console.log(error);
    })
  },[])
  if(!isDataLoaded){
    return(<div><h1>Read</h1><p>데이터 로드중</p></div>);
  }else{
    return(
      <div className="row content">
            <div className="col">
                <div className="card">
                    <div className="card-header">
                        Featured
                    </div>
                    <div className="card-body">
                        <div className="input-group mb-3">
                            <span className="input-group-text">TNO</span>
                            <input type="text" name="tno" className="form-control" defaultValue={data.tno} readOnly />
                        </div>
                        <div className="input-group mb-3">
                            <span className="input-group-text">Title</span>
                            <input type="text" name="title" className="form-control" defaultValue={data.title} readOnly />
                        </div>
                        <div className="input-group mb-3">
                            <span className="input-group-text">DueDate</span>
                            <input type="date" name="dueDate" className="form-control" defaultValue={data.dueDate} readOnly />
                        </div>
                        <div className="input-group mb-3">
                            <span className="input-group-text">Writer</span>
                            <input type="text" name="writer" className="form-control" defaultValue={data.writer} readOnly />
                        </div>
                        <div className="form-check">
                            <label className="form-check-label">
                                Finished &nbsp;
                            </label>
                            <input type="checkbox" name="finished" className="form-check-input" defaultChecked={data.complete} disabled />
                        </div>
                        <div className="input-group mb-3">
                            <span className="input-group-text">Image</span>
                            <input type="file" name="file" className="form-control" />
                        </div>
                        <div className="my-4">
                            <div className="float-end">
                                <Link className="btn btn-primary" to={"/modify/"+data.tno}>Modify</Link>
                                <Link className="btn btn-secondary" to={"/"}>List</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
  }
}
export default Read;