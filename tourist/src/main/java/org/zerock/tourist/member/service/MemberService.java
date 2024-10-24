package org.zerock.tourist.member.service;

import org.modelmapper.ModelMapper;
import org.zerock.tourist.member.dao.MemberDAO;
import org.zerock.tourist.member.domain.MemberVO;
import org.zerock.tourist.member.dto.MemberDTO;
import org.zerock.tourist.util.MapperUtil;

public enum MemberService {
  INSTANCE;
  MemberDAO dao = new MemberDAO();
  ModelMapper modelMapper = MapperUtil.INSTANCE.getModelMapper();

  public void addMember(MemberDTO dto) throws Exception {
    MemberVO vo = MemberVO.builder()
        .member_id(dto.getEmail1())
        .member_pw(dto.getMember_pw())
        .email1(dto.getEmail1())
        .email2(dto.getEmail2())
        .name(dto.getName())
        .gender(dto.getGender())
        .phone(dto.getPhone())
        .agree(dto.isAgree())
        .build();
    dao.insertMember(vo);
  }
  public MemberDTO getMember(String member_id, String member_pw) throws Exception {
    MemberVO vo = dao.selectMember(member_id, member_pw);
    return modelMapper.map(vo, MemberDTO.class);
  }
}














