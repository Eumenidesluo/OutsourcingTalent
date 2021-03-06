package dao;

import entity.UserEntity;

import java.util.List;

/**
 * Created by Eumenides on 2017/2/18.
 */
public interface UserDao {
    public void savePassword();
    public void delete();
    public UserEntity findByEmail(String email);
    public List<?> queryByEntity(UserEntity userEntity);
    public void save(UserEntity user);
    public void update(UserEntity user);
    public Boolean isNameUnique(String username);
    public int isEmailUnique(String unique);
}
