package com.trainerservice.entity;

import java.util.List;
import java.util.Map;

public class TrainerApiResponse {
	private TrainerDto trainerentity;
	private  Map<String,List<StudentEntity>> studententity;
	
	public TrainerApiResponse() {
		
	}
	public TrainerApiResponse(TrainerDto trainerentity, Map<String,List<StudentEntity>> studententity) {
		super();
		this.trainerentity = trainerentity;
		this.studententity = studententity;
	 
	}
	public TrainerDto getTrainerentity() {
		return trainerentity;
	}
	public void setTrainerentity(TrainerDto trainerentity) {
		this.trainerentity = trainerentity;
	}
	public Map<String,List<StudentEntity>> getStudententity() {
		return studententity;
	}
	public void setStudententity(Map<String,List<StudentEntity>> studententity) {
		this.studententity = studententity;
	}
	
	
	

}
