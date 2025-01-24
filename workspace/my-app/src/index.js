import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';

// import App from './App';
// import Library from './chapter_03/Library';
// import Clock from './chapter_04/Clock';
// import CommentList from './chapter_05/CommentList';
// import NotificationList from './chapter_06/NotificationList';
// import Accommodate from './chapter_07/Accommodate';
// import TextInputWithFocusButton from './chapter_07/TextInputWithFocusButton';
// import Toggle from './chapter_08/Toggle';
// import ConfirmButton from './chapter_08/ConfirmButton';
// import LoginControl from './chapter_09/LoginControl';
// import MainPage from './chapter_09/MainPage'
// import LandingPage from './chapter_09/LandingPage'
// import NumberList from './chapter_10/NumberList'
// import AttendanceBook from './chapter_10/AttendanceBook';
// import NameForm from './chapter_11/NameForm'
// import RequestForm from './chapter_11/RequestForm'
// import FruitSelect from './chapter_11/FruitSelect';
// import Reservation from './chapter_11/Reservation'
// import SignUp from './chapter_11/SignUp';
// import Calculator from './chapter_12/Calculator';
// import ProfileCard from './chapter_13/ProfileCard';
// import SignUpDialog from './chapter_13/Dialog';
// import DarkOrLight from './chapter_14/DarkOrLight';
// import MainPage from './chapter_15/MainPage';
import Sample from './chapter_15/Sample';
// const numbers = [1,2,3,4,5];
const root = ReactDOM.createRoot(document.getElementById('root'));
// setInterval(()=>{
  
  root.render(
    <React.StrictMode>
      {/* <App /> */}
      {/* <Library /> */}
      {/* <Clock /> */}
      {/* <CommentList /> */}
      {/* <NotificationList /> */}
      {/* <Accommodate /> */}
      {/* <TextInputWithFocusButton /> */}
      {/* <Toggle /> */}
      {/* <ConfirmButton /> */}
      {/* <LoginControl /> */}
      {/* <MainPage /> */}
      {/* <LandingPage /> */}
      {/* <NumberList numbers={numbers} /> */}
      {/* <AttendanceBook /> */}
      {/* <NameForm /> */}
      {/* <RequestForm /> */}
      {/* <FruitSelect /> */}
      {/* <Reservation /> */}
      {/* <SignUp /> */}
      {/* <Calculator /> */}
      {/* <ProfileCard /> */}
      {/* <SignUpDialog /> */}
      {/* <DarkOrLight /> */}
      {/* <MainPage /> */}
      <Sample />
    </React.StrictMode>,
    // document.getElementById('root')
  );
// },1000);


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
