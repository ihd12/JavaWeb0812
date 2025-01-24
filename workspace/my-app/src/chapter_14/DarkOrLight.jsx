import {useState, useCallback} from "react";
import ThemeContext from "./ThemeContext";
import MainContext from "./MainContext";

function DarkOrLight(props){
  const [theme, setTheme] = useState("light");
  const toggleTheme = useCallback(()=>{
    if(theme==="light"){
      setTheme("dark");
    }else if(theme==="dark"){
      setTheme("light");
    }
  },[theme]);
  return (
    //Context사용방법
    //Context가 필요한 컴포넌트를 Context.Provider로 감싸준다.
    <ThemeContext.Provider value={{ theme, toggleTheme}}>
      <MainContext />
    </ThemeContext.Provider>
  );
}
export default DarkOrLight;