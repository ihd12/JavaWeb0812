import React from "react";
import Notification from "./Notification";

const reservedNotifications = [
  {
    id:1,
    message:"안녕하세요, 오늘 일정을 알려드립니다.",
  },
  {
    id:2,
    message:"점심 식사 시간입니다.",
  },
  {
    id:3,
    message:"이제 곧 미팅이 시작됩니다.",
  },
]
//1초에 한번식 실행되는 setInterval함수를 저장하는 변수
var timer;

// NotificationList의 실행순서
// 1. constructor() 실행
// 2. render() 실행
// 3. componentDidMount() 실행
// 3-1. setState() 실행 -> render() 실행
// 3-2. setState() 실행 -> render() 실행
// 3-3. setState() 실행 -> render() 실행
// 4. componentWillUnmount() 실행

class NotificationList extends React.Component{
  constructor(props){
    super(props);
    //앞으로 사용할 state를 초기화하는 부분
    this.state={
      notifications : [],
    };
  }
  // componentDidMount() : 컴포넌트가 생성될때 실행되는 함수
  componentDidMount(){
    //this.state 안에 존재하는 notifications를 변수에 저장
    const {notifications} = this.state;
    //1초에 한번식 실행되도록 설정
    timer = setInterval(()=>{
      // state의notifications데이터 개수가 reservedNotifications보다 작을 때 실행
      if(notifications.length < reservedNotifications.length){
        // state의 notifications 안 데이터의 최대개수를 index로 저장
        const index = notifications.length;
        // notifications에 reservedNotifications의 index번째에 데이터를 추가
        notifications.push(reservedNotifications[index]);
        // setState() : notifications에 추가한 데이터를 이용하여 재랜더링을 진행
        this.setState({
          notifications:notifications,
        });
      }else{
        this.setState({
          notifications:[],
        });
        clearInterval(timer);
      }
    },1000);
  }

  // componentWillUnmount() : 위의 모든 실행이 끝나면 실행되는 함수
  componentWillUnmount(){
    if(timer){
      clearInterval(timer);
    }
  }

  render(){
    return(
      <div>
        {this.state.notifications.map((notification)=>{
          return <Notification 
          key={notification.id} 
          id={notification.id}
          message={notification.message} />
        })}
      </div>
    );
  }
}
export default NotificationList;