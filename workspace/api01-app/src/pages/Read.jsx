import { useParams } from "react-router-dom";
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
      <div>
        <h1>Read</h1>
        {data.tno}, {data.dueDate}, {data.title}, {data.writer}, {data.complete}
      </div>
    );
  }
}
export default Read;