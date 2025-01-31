import { Link } from "react-router-dom";

const Header = ({accessToken,handleLogout}) =>{
  return(
    <div className="row">
    <div className="col">
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
                <a className="navbar-brand" href="/todo/list">할 일 목록</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                  <div className="navbar-nav">
                    {!accessToken&&<Link className="nav-link" to={"login"}>로그인</Link>}
                    {!accessToken&&<Link className="nav-link" to={"join"}>회원가입</Link>}
                    {accessToken&&<Link className="nav-link" to={"register"}>할일등록</Link>}
                    {accessToken&&<Link className="nav-link" onClick={handleLogout}>로그아웃</Link>}
                    {accessToken&&<Link className="nav-link removeBtn">회원탈퇴</Link>}
                  </div>
                </div>
            </div>
        </nav>
    </div>
</div>
  );
}
export default Header;