package com.adminservice.entity;

import java.util.List;
import java.util.Map;

public class TrainerApiResponse {
	private TrainerEntity trainerentity;
	private  Map<String,List<StudentEntity>> studententity;
	public TrainerApiResponse() {
		
	}
	public TrainerApiResponse(TrainerEntity trainerentity, Map<String,List<StudentEntity>> studententity) {
		super();
		this.trainerentity = trainerentity;
		this.studententity = studententity;
	}
	public TrainerEntity getTrainerentity() {
		return trainerentity;
	}
	public void setTrainerentity(TrainerEntity trainerentity) {
		this.trainerentity = trainerentity;
	}
	public Map<String,List<StudentEntity>> getStudententity() {
		return studententity;
	}
	public void setStudententity(Map<String,List<StudentEntity>> studententity) {
		this.studententity = studententity;
	}
	
	

}
