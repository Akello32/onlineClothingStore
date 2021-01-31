package by.matmux.service;

import by.matmux.dao.Transaction;

abstract class ServiceImpl implements Service {
	protected Transaction transaction = null;

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
