package dao;

import java.util.List;

import entity.NoticeEntity;

public interface NoticeDao {

	public List<?> queryByUserId(Integer userId);
	public List<NoticeEntity> queryByUserId(Integer userId,int number);
	public Boolean saveNotice(NoticeEntity entity);
}
