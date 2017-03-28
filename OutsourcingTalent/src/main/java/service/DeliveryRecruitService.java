package service;

public interface DeliveryRecruitService {

	public Boolean delivery(Integer recruitId,Integer userId,Integer resumeId);
	public Boolean hasDeliveried(Integer userId,Integer recruitId);
}
