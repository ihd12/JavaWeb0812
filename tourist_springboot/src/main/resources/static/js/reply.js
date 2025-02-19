// async : 비동기 함수를 설정
// await : 비동기 호출 부분 설정
async function get1(bno){
    const result = await axios.get(`/replies/list/${bno}`);
    return result.data;
}
async function getList({tno, page, size, goLast}){
    const result = await axios.get(`/replies/list/${tno}`,{params:{page,size}});
    if(goLast){
        const total = result.data.total;
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({tno:tno,page:lastPage,size:size})
    }
    return result.data;
}
async function addReply(replyObj){
    const response = await axios.post(`/replies/`, replyObj);
    return response.data;
}

async function getReply(rno){
    const response = await axios.get(`/replies/${rno}`);
    return response.data;
}
async function modifyReply(replyObj){
    const response = await axios.put(`/replies/${replyObj.rno}`, replyObj);
    return response.data;
}
async function removeReply(rno){
    const response = await axios.delete(`/replies/${rno}`);
    return response.data;
}

















