import './App.css';
import Header from './component/Header'
import TodoEditor from './component/TodoEditor';
import TodoList from './component/TodoList';
import {useState,useRef,useCallback} from 'react';

const mockData = [
  {
    id:0,
    isDone:false,
    content: "React 공부하기",
    createDate: new Date().getTime(),
  },
  {
    id:1,
    isDone:false,
    content: "빨래 널기",
    createDate: new Date().getTime(),
  },
  {
    id:2,
    isDone:false,
    content: "청소 하기",
    createDate: new Date().getTime(),
  },
];

function App() {
  const [todo, setTodo] = useState(mockData);
  const idRef = useRef(3);
  const onCreate = (content) =>{
    const newItem = {
      id:idRef.current,
      content,
      isDone:false,
      createDate:new Date().getTime(),
    }
    setTodo([newItem, ...todo])
    idRef.current += 1;
  };
  const onUpdate = useCallback((targetId) =>{
    // useCallback 훅은 처음 생성되면 다시 함수를 생성하지 않기 때문에
    // 함수형 업데이트를 이용하여 state데이터를 매번 갱신하도록 작성
    setTodo((todo)=>(
      todo.map(
        (data)=>
          data.id === targetId ? {...data, isDone:!data.isDone} : data
      ))
    );
  },[]);
  const onDelete = useCallback((targetId) =>{
    setTodo((todo)=>
      (todo.filter((data) => data.id !== targetId)));
  },[]);
  return (
    <div className="App">
      <Header />
      <TodoEditor onCreate={onCreate}/>
      <TodoList 
        todo={todo} 
        onUpdate={onUpdate} 
        onDelete={onDelete}
      />
    </div>
  );
}

export default App;
