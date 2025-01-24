import './App.css';
import ApiLogin from './pages/ApiLogin';
import Home from './pages/Home';
import {Routes, Route} from 'react-router-dom';
import Read from './pages/Read';
import Register from './pages/Register';
import Modify from './pages/Modify';
import Header from './component/Header';
import { useEffect, useState } from 'react';
import Footer from './component/Footer';

function App() {
  const [accessToken, setAccessToken] = useState();
  useEffect(()=>{
    const token = localStorage.getItem("accessToken");
    setAccessToken(token);
  },[])
  const handleLogout = () =>{
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
    setAccessToken(null);
  }
  return (
    <div className="App">
      <Header accessToken={accessToken} handleLogout={handleLogout}/>
      <Routes>
        <Route index element={<Home />} />
        <Route path="read/:tno" element={<Read />} />
        <Route path="register" element={<Register />} />
        <Route path="modify/:tno" element={<Modify />} />
        <Route path="login" element={<ApiLogin setAccessToken={setAccessToken}/>} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
