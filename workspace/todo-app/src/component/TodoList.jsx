import "./TodoList.css";
import TodoItem from "./TodoItem";
import React, {useState, useMemo} from "react";

const TodoList=({todo , onUpdate, onDelete})=>{
  const [search,setSearch] = useState("");
  const onChangeSearch = (e) =>{
    setSearch(e.target.value);
  };
  const getSearchResult = () =>{
    return search === "" 
          ? todo 
          : todo.filter((data)=>data.content.toLowerCase().includes(search.toLowerCase()));
  };
  // useMemo : 사용한 함수의 반환값을 기억하고 다시한번 
  // 함수가 불렸을때 반환값이 같으면 다시 함수를 실행 하지 않음
  const analyzeTodo = useMemo(()=>{
    const totalCount = todo.length;
    const doneCount = todo.filter((data)=>data.isDone).length;
    const notDoneCount = totalCount - doneCount;
    return {
      totalCount,doneCount,notDoneCount,
    };
  },[todo]) 
  const {totalCount,doneCount,notDoneCount} = analyzeTodo;
  return(
    <div className="TodoList">
      <h4>Todo List 🎈</h4>
      <div>
        <div>총개수: {totalCount}</div>
        <div>완료된 할 일: {doneCount}</div>
        <div>아직 완료하지 못한 할 일: {notDoneCount}</div>
      </div>
      <input onChange={onChangeSearch}
        className="searchbar" 
        placeholder="검색어를 입력하세요" />
      <div className="list_wrapper">
        {getSearchResult().map((data)=>(
          <TodoItem 
            key={data.id}
            {...data} 
            onUpdate={onUpdate}
            onDelete={onDelete}
          />
        ))}
      </div>
    </div>
  );
}
export default TodoList;