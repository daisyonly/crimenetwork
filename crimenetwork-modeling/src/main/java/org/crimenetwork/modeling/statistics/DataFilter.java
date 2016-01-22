package org.crimenetwork.modeling.statistics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Component;

@Component
public class DataFilter {

	private static volatile DataFilter INSTANCE = null;
	
	private DataFilter() {
		
	}

	public static DataFilter getAnInstance() {
		if (INSTANCE == null) {
			synchronized (DataFilter.class) {
				if (INSTANCE == null) {
					INSTANCE = new DataFilter();
				}
			}
		}
		return INSTANCE;
	}
	
	
	public static void main(String[] args) {
		DataFilter abGraph=DataFilter.getAnInstance();
		
		
	}

}
