package kr.co.sunnyside.userpage.service;

import kr.co.sunnyside.cmn.DTO;

public class KDY_ReservationVO  extends DTO{
	private String pay_code;
	private String product_nm;
	private String pay_cost;
	private String pay_dt;
	
	KDY_ReservationVO(){}

	public String getPay_code() {
		return pay_code;
	}

	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}

	public String getProduct_nm() {
		return product_nm;
	}

	public void setProduct_nm(String product_nm) {
		this.product_nm = product_nm;
	}

	public String getPay_cost() {
		return pay_cost;
	}

	public void setPay_cost(String pay_cost) {
		this.pay_cost = pay_cost;
	}

	public String getPay_dt() {
		return pay_dt;
	}

	public void setPay_dt(String pay_dt) {
		this.pay_dt = pay_dt;
	}

	@Override
	public String toString() {
		return "KDY_ReservationVO [pay_code=" + pay_code + ", product_nm=" + product_nm + ", pay_cost=" + pay_cost
				+ ", pay_dt=" + pay_dt + "]";
	}

	public KDY_ReservationVO(String pay_code, String product_nm, String pay_cost, String pay_dt) {
		super();
		this.pay_code = pay_code;
		this.product_nm = product_nm;
		this.pay_cost = pay_cost;
		this.pay_dt = pay_dt;
	}
	
	
}
