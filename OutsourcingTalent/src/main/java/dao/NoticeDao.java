package dao;

import java.util.List;

import entity.NoticeEntity;

public interface NoticeDao {

	public List<?> queryByUserId(String userId);
	public List<NoticeEntity> queryByUserId(String userId,int number);
	public Boolean saveNotice(NoticeEntity entity);
}
