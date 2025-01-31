import React , {useState} from 'react';
function WarningBanner(props){
  if(!props.warning){
    // null을 이용하여 특정 컴포넌를 렌더링 하지 않음
    return null;
  }
  return(
    <div>경고!</div>
  );
}
function MainPage(props){
  const [showWarning, setShowWarning] = useState(false);
  const handleToggleClick = () => {
    setShowWarning(prevShowWarning => !prevShowWarning);
  }
  return(
    <div>
      <WarningBanner warning={showWarning} />
      <button onClick={handleToggleClick}>
        {showWarning ? '감추기' : '보이기'}
      </button>
    </div>
  );
}
export default MainPage;