package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Review;

public interface ReviewDao extends AbstractDAO<Review>{
	List<Review> getReviewByDate();
}
