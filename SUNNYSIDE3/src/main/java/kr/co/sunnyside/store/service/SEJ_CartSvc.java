package kr.co.sunnyside.store.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface SEJ_CartSvc {
	/**장바구니 저장 */
	public int do_save(DTO dto);
	
	/**장바구니 삭제 */
	public int do_delete(DTO dto);
	
	/**장바구니 전체삭제 */
	public int do_deleteAll();
	
	/**장바구니 수정 */
	public int do_update(DTO dto);

	/**장바구니 목록 */
	public List<?> do_retrieve(DTO dto);
	
	/**장바구니 금액 총합계 */    
	public DTO do_selectOne(DTO dto);
	
	/**장바구니 동일한 상품 레코드 확인*/
	public int do_countCart(String productId, String userId);
	
	/**장바구니 상품수량 변경*/
	public int do_updateCountCart(DTO dto);

}