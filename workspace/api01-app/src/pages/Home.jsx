
import { useEffect, useRef, useState } from "react";
import axiosInstance from "../auth/Auth";
import { Link } from "react-router-dom";

const Home = () =>{
  const [data , setData] = useState([]);
  const [isDataLoaded, setIsDataLoaded] = useState(false);
  const completeRef = useRef();
  const keywordRef = useRef();
  const fromRef = useRef();
  const toRef = useRef();
  const getList = async (param) =>{
    const response = await axiosInstance.get("http://localhost:8080/api/todo/list",{params:param} )
    return response.data;
  }
  const showList = (num) =>{
    const param = {
      "completed": completeRef.current.checked,
      "keyword":keywordRef.current.value,
      "from": fromRef.current.value,
      "to" : toRef.current.value,
      "page" : num
    }
    getList(param).then((data)=>{
      setData(data);
    }).catch((error)=>{
      console.log(error);
    })
  }
  // 컴포넌트 마운트 될때 한번만 실행되도록 하는 useEffect
  // setData가 실행될때 비동기 함수로 처리하지 않으면 계속해서 리렌더링이 일어나게됨
  useEffect(()=>{
    getList().then((data)=>{
      setData(data);
      setIsDataLoaded(true);
      console.log("데이터 로딩 완료");
    }).catch((e)=>{
      alert("오류가 발생했습니다.");
      console.log(e);
    })
  },[])
  const handlePagination = (event) =>{
    const target = event.target;
    if(target.tagName !== 'A'){
      return;
    }
    const num = target.getAttribute("data-num");
    showList(num);
  }
  const handleSearch = (event) =>{
    event.preventDefault();
    showList(data.page==0?1:data.page);
  }
  if(!isDataLoaded){
    console.log("데이터 로딩중");
    return(
    <div>
      <h1>Home</h1>
      <p>데이터 로딩중</p>
    </div>
    );
  }else{
    console.log("데이터 출력", data);
    const pageNumber = Array.from({length:data.end - data.start + 1},(_,i) => data.start+i);
    if(pageNumber[0]==0){
      pageNumber[0]=1
    }
    console.log(pageNumber);
    return(
      <div className="row">
        <div className="row content">
            <div className="col">
                <div className="card">
                    <div className="card-body">
                        <h5 className="card-title">검색</h5>
                        <form>
                            <div className="mb-3">
                                <input type="checkbox" ref={completeRef}  defaultChecked={data?.pageRequestDTO?.completed ? "checked": ""} />완료여부
                            </div>
                            <div className="mb-3">
                                {/* <input type="checkbox" name="types" value="t" defaultChecked={data?.pageRequestDTO?.type==="t" ? "checked":""} />제목
                                <input type="checkbox" name="types" value="w" defaultChecked={data?.pageRequestDTO?.type==="w" ? "checked":""} />작성자 */}
                                <input type="text" ref={keywordRef} className="form-control" defaultValue={data?.pageRequestDTO?.keyword} />
                            </div>
                            <div className="input-group mb-3 dueDateDiv">
                                <input type="date" ref={fromRef} className="form-control" defaultValue={data?.pageRequestDTO?.from} />
                                <input type="date" ref={toRef} className="form-control" defaultValue={data?.pageRequestDTO?.to} />
                            </div>
                            <div className="input-group mb-3">
                                <div className="float-end">
                                    <button className="btn btn-primary" onClick={handleSearch}>검색</button>
                                    <button className="btn btn-info clearBtn" type="reset">초기화</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div className="row content">
            <div className="col">
                <div className="card">
                    <div className="card-header">
                        Featured
                    </div>
                    <div className="card-body">
                        <h5 className="card-title">할 일 목록</h5>
                        <table className="table">
                            <thead>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">제목</th>
                                <th scope="col">작성자</th>
                                <th scope="col">기한</th>
                                <th scope="col">완료여부</th>
                            </tr>
                            </thead>
                            <tbody>
                            {data?.dtoList?.map((dto)=>{
                              return(
                              <tr>
                                    <th scope="row">{dto.tno}</th>
                                    <td>
                                        <Link to={`/read/${dto.tno}?${data.pageRequestDTO.link}`}
                                            className="text-decoration-none" data-tno={dto.tno}>
                                        {dto.title}</Link>
                                    </td>
                                    <td>{dto.writer}</td>
                                    <td>{dto.dueDate}</td>
                                    <td>{dto.complete?'O':'X'}</td>
                                </tr>)
                            })}
                            </tbody>
                        </table>
                        <div className="float-end">
                            <ul className="pagination flax-wrap" onClick={handlePagination}>
                                {data.prev&&<li className="page-item">
                                        <a className="page-link" data-num={data.start - 1}>Previous</a>
                                    </li>}
                                {pageNumber.map((num)=>(
                                    <li className={`page-item ${data.page == num ? "active": ""}`}>
                                        <a className="page-link" data-num={num}>{num}</a>
                                    </li>
                                ))}
                                {data.next&&
                                <li className="page-item">
                                  <a className="page-link" data-num={data.end + 1}>Next</a>
                                </li>}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
      
    );
  }
}

export default Home;

