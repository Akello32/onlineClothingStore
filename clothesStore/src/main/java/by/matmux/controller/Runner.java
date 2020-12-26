package by.matmux.controller;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Clothes;
import by.matmux.dao.mysql.ClothesDaoImpl;

public class Runner {	
	private static final Logger log = LogManager.getLogger(Runner.class);

	public static void main(String[] args) {
		ClothesDaoImpl impl = new ClothesDaoImpl();
		Clothes clothes = new Clothes();
		clothes.setBrandId(1);
		clothes.setColor("red");
		clothes.setNumbers(3);
		clothes.setPrice(new BigDecimal(45));
		clothes.setSize("Xl");
		clothes.setTypeId(1);
		log.debug(impl.create(clothes));
	}
}
