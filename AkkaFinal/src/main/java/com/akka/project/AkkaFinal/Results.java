package com.akka.project.AkkaFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Results {
	
	private Vector<Integer> total;
	private Vector<Integer> success;
	
	public Results(Vector<Integer> total, Vector<Integer> success) {
		super();
		this.total = total;
		this.success = success;
	}

	public Vector<Integer> getTotal() {
		return total;
	}

	public void setTotal(Vector<Integer> total) {
		this.total = total;
	}

	public Vector<Integer> getSuccess() {
		return success;
	}

	public void setSuccess(Vector<Integer> success) {
		this.success = success;
	}
	
	

}
