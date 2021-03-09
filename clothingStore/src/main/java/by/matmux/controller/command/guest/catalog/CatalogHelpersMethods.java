package by.matmux.controller.command.guest.catalog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Type;
import by.matmux.exception.PersistentException;
import by.matmux.service.ClothesService;

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

	public static List<Clothes> getPaginationCatalog(List<Clothes> clothes, ClothesService service, int prevId,
			int nextId, HttpServletRequest request) throws PersistentException {
		if (prevId != 0) {
			clothes = service.findPrevPageClothes(prevId);
			if (clothes.size() == 9) {
				clothes.remove(8);
				request.setAttribute("prevBul", true);
				request.setAttribute("nextBul", true);
				request.setAttribute("startId", prevId+1);
			}
		} else if (nextId != 0) {
			clothes = service.findNextPageClothes(nextId);
			request.setAttribute("prevBul", true);
			if (clothes.size() == 9) {
				clothes.remove(8);
				request.setAttribute("nextBul", true);
				request.setAttribute("startId", nextId+1);
			}
		} else {
			clothes = service.findNextPageClothes(0);
			if (clothes.size() == 9) {
				clothes.remove(8);
				request.setAttribute("nextBul", true);
			}
		}
		return clothes;
	}

	/*
	 * public static void deleteDuplicates(List<Clothes> clothes) { List<Clothes>
	 * result = new ArrayList<>(); Set<String> imageSet = new HashSet<>();
	 * 
	 * for (Clothes c : clothes) { if (!imageSet.contains(c.getImgPath())) {
	 * imageSet.add(c.getImgPath()); result.add(c); } }
	 * 
	 * clothes.clear(); clothes.addAll(result); // return result; }
	 */
}
