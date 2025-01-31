import "./TodoEditor.css";
import {useState, useRef} from "react";

const TodoEditor = ({onCreate}) =>{
  const [content,setContent] = useState("");
  const inputRef = useRef();
  const onChangeContent = (event) =>{
    setContent(event.target.value);
  };
  const onKeyDown = (e) =>{
    if(e.keyCode===13){
      onSubmit();
    }
  };
  const onSubmit = () =>{
    if(!content){
      inputRef.current.focus();
      return;
    }
    onCreate(content);
    setContent("");
  }
  return(
    <div className="TodoEditor">
      <h4>새로운 Todo 작성하기 ✏</h4>
      <div className="editor_wrapper">
        <input 
          ref={inputRef}
          value={content}
          onKeyDown={onKeyDown}
          onChange={onChangeContent}
          placeholder="새로운 Todo..." />
        <button onClick={onSubmit}>추가</button>
      </div>
    </div>
  );
}
export default TodoEditor;