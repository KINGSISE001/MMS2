package cn.lastwhisper.modular.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import cn.lastwhisper.modular.vo.EasyUIDataGridResult;
import cn.lastwhisper.modular.vo.EasyUIOptionalTreeNode;
import cn.lastwhisper.modular.vo.GlobalResult;
import cn.lastwhisper.modular.pojo.Role;
import cn.lastwhisper.modular.pojo.User;

/**
 * 
 * @ClassName:  UserService   
 * @Description:处理用户相关业务  
 * @author:     最后的轻语_dd43
 * @date:       2019年4月5日
 */
public interface UserService {
	/**
	 * 
	* 
	* @Description: TODO(这里用一句话描述这个类的作用) 
	* @author wangsheng
	* @date 2021年9月22日 上午11:36:24 
	* @param user_code
	* @return
	 */
    Integer findUserByCode(String user_code);
	
	/**
	 * 
	 * @Title: findUserByCodeAndPwd   
	 * @Description: 根据账号和密码查找用户
	 * @param user_code 账号
	 * @param user_pwd 密码
	 * @return
	 */
    User findUserByCodeAndPwd(String user_code, String user_pwd);
	/**
	 * 
	 * @Title: findUserList   
	 * @Description: 查询用户分页信息  
	 * @param page 当前页
	 * @param rows 页面大小
	 * @return
	 */
    EasyUIDataGridResult findUserlistByPage(User user,Integer page, Integer rows);
	/**
	 * 
	 * @Title: findUserName   
	 * @Description: like查询用户名
	 * @param q 用户名
	 * @return
	 */
    List<User> findUserName(String q);
	
	/**
	 * @Title: findUserRole
	 * @Description: 查询所有角色，并设置选中的用户角色为true 
	 * 				 -- 1.获取用户对应的角色,例如user_id=221 select uuid
	 *               from user_role,role where user_role.userid=221 and
	 *               user_role.roleuuid = role.uuid and role.enble=1; 
	 *               -- 2.获取所有角色
	 *               select uuid,name from role where role.enble=1; --
	 *               -- 3.封装返回值将用户对应的角色设置为true,uuid、name
	 * @param user_id
	 * @return List<Tree>
	 * @date 2019年2月16日下午2:56:10
	 */
	List<EasyUIOptionalTreeNode> findUserRole(Integer user_id);
	/**
	 * 
	* @Title: findUserRoleByUserid 
	* @Description: 根据用户id查询对应的角色  
	* @param user_id
	* @return List<Role>
	* @date 2019年2月21日下午12:08:37
	 */
	List<Role> findUserRoleByUserid(Integer user_id);
	/**
	 * 
	 * @Title: updateUserRole
	 * @Description: 更新用户对应的角色
	 * @param user_id
	 * @param checkedIds
	 * @return Integer
	 * @date 2019年2月16日下午3:57:33
	 */
	GlobalResult updateUserRole(Integer user_id, String checkedIds);
	
	/**
	 * 
	 * @Title: addUser   
	 * @Description: 添加用户
	 * @param user 用户
	 * @return
	 */
    GlobalResult addUser(User user);
	/**
	 * 
	 * @Title: updateUser   
	 * @Description: 更新用户信息
	 * @param 用户信息
	 * @return
	 */
    GlobalResult updateUser(User user);
	/**
	 * 
	 * @Title: updatePwd   
	 * @Description: 更新用户的密码
	 * @author: 最后的轻语_dd43    
	 * @param user 当前用户
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @return
	 */
    GlobalResult updatePwd(User user,String oldPwd,String newPwd);
	/**
	 * 
	 * @Title: deleteUser   
	 * @Description: 删除用户   
	 * @param userId
	 * @return
	 */
    GlobalResult deleteUser(Integer user_id);
	
	/**
	 * 
	 * @Title: export   
	 * @Description: 导出excel
	 * @author: 最后的轻语_dd43    
	 * @param os
	 * @param user
	 */
    void export(OutputStream os, User user);
	
	/**
	 * 
	 * @Title: doImport   
	 * @Description: 导入excel
	 * @author: 最后的轻语_dd43    
	 * @param is
	 * @throws IOException
	 */
    Integer doImport(InputStream is) throws IOException;
}
