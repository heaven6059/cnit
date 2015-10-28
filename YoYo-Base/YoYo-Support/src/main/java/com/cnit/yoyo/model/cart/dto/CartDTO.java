package com.cnit.yoyo.model.cart.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.model.goods.Goods;
import com.cnit.yoyo.model.goods.Store;

public class CartDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7046202302585461137L;

	private Store store;
	private Goods goods;
	private String appointment;
	private List<CartProductDTO> cartProducts;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List<CartProductDTO> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProductDTO> cartProducts) {
		this.cartProducts = cartProducts;
	}

	public String getAppointment() {
		return appointment;
	}

	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
