package kr.co.sunnyside.store.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.store.service.SEJ_StroreSvc;
import kr.co.sunnyside.store.service.SEJ_StroreVO;

@Service
public class SEJ_StoreSvcImple implements SEJ_StroreSvc {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	//dao 등록
	@Autowired
	private SEJ_StroreDaoImpl storeDaoImpl;
	
	
	
	/**상품등록
	 *  1. 파일을 업로드 한다.
	 *  2. 입력한 정보를 저장 한다.
	 * 
	 * */
	@Override
	public int do_save(DTO dto) {
		LOG.debug("==================");
		LOG.debug("=@Service="+dto);
		LOG.debug("==================");
		 
		return storeDaoImpl.do_save(dto);
	}
	
	/**상품삭제*/
	@Override
	public int do_delete(DTO dto) {
		
		return storeDaoImpl.do_delete(dto);
	}
	
	/**상품정보수정  
	 * 1. 파일을 업로드 한다.
	 * 2. 입력한 정보를 저장 한다.
	 * 
	 * */
	@Override
	public int do_update(DTO dto) {
		
		return storeDaoImpl.do_update(dto);
	}
	
	/**상품선택*/
	@Override
	public DTO do_selectOne(DTO dto) {
		
		return storeDaoImpl.do_selectOne(dto);
	}
	
	/**팝콘 조회*/
	@Override
	public List<?> do_retrieve_popcorn(DTO dto) {
		return storeDaoImpl.do_retrieve_popcorn(dto);
	}

	/**음료조회*/
	@Override
	public List<?> do_retrieve_drink(DTO dto) {
		return storeDaoImpl.do_retrieve_drink(dto);
	}
	/**영화예매권조회*/
	@Override
	public List<?>do_retrieve_movieticket(DTO dto) {
		return storeDaoImpl.do_retrieve_movieticket(dto);
	}
	/**상품전체조회*/
	@Override
	public List<?> do_retrieve(DTO dto) {
		
		return storeDaoImpl.do_retrieve(dto);
	}
	




}
