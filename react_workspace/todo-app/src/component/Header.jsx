import React from 'react';
import './Header.css';

const Header = () =>{
  return (
    <div className="Header">
      <h3>오늘은 📅</h3>
      <h1>{new Date().toDateString()}</h1>
    </div>
  );
}
// 컴포넌트의 props가 변경되지 않은 경우 리렌더링을 건너뛸 수 있습니다.
export default React.memo(Header);