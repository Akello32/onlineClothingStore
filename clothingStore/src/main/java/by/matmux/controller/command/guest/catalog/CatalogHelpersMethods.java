package by.matmux.controller.command.guest.catalog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Type;

public class CatalogHelpersMethods {
	public static void addTypeAndBrand(List<Clothes> clothes, List<Brand> brands, List<Type> types) {
		for (Clothes c : clothes) {
			for (Brand b : brands) {
				if (c.getBrand().getIdentity() == b.getIdentity()) {
					c.getBrand().setName(b.getName());
				}
			}

			for (Type t : types) {
				if (c.getType().getIdentity() == t.getIdentity()) {
					c.getType().setName(t.getName());
				}
			}
		}
	}

	public static void addBrand(List<Clothes> clothes, List<Brand> brands) {
		for (Clothes c : clothes) {
			for (Brand b : brands) {
				if (c.getBrand().getIdentity() == b.getIdentity()) {
					c.getBrand().setName(b.getName());
				}
			}
		}
	}

	public static void/*List<Clothes>*/ deleteDuplicates(List<Clothes> clothes) {
		List<Clothes> result = new ArrayList<>();
		Set<String> imageSet = new HashSet<>();
		
		for (Clothes c : clothes) {
			if (!imageSet.contains(c.getImgPath())) {
				imageSet.add(c.getImgPath());
				result.add(c);
			}
		}
		
		clothes.clear();
		clothes.addAll(result);
//		return result;
	}
}
