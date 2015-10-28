package com.cnit.yoyo.rmi.system.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.member.MemberMapper;
import com.cnit.yoyo.dao.member.PamAccountMapper;
import com.cnit.yoyo.dao.member.RolesMapper;
import com.cnit.yoyo.dao.shop.StoreMemberMapper;
import com.cnit.yoyo.dao.system.RoleUserLinkMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarYear;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.member.Roles;
import com.cnit.yoyo.model.shop.StoreMember;
import com.cnit.yoyo.model.system.Role;
import com.cnit.yoyo.model.system.RoleUserLink;
import com.cnit.yoyo.model.system.dto.SiteUserRoleDTO;
import com.cnit.yoyo.model.system.dto.UserAddDTO;
import com.cnit.yoyo.model.system.dto.UserListDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 账号管理类
 * 
 * @author ssd
 * @date 2015-4-30 下午4:41:44
 */
@Service("userServiceImpl")
public class UserServiceImpl {

	@Autowired
	private PamAccountMapper pamAccountMapper;
	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@Autowired
	private PasswordHelper passwordHelper;
	@Autowired
	private RoleUserLinkMapper roleUserLinkMapper;

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private StoreMemberMapper storeMemberMapper;
	@Autowired
	private RolesMapper rolesMapper;

	@Autowired
	private RemoteService otherService;

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	public int createUser(PamAccount user) {
		// 加密密码
		passwordHelper.encryptPassword(user);
		return pamAccountMapper.insert(user);
	}

	/**
	 * 
	 *
	 * @Description: 更新用户 @param @param user @param @return 设定文件 @author
	 *               ssd @date 2015-4-30 下午4:41:56 @return int 返回类型 @throws
	 */
	public int updateUser(PamAccount user) {
		passwordHelper.encryptPassword(user);
		return pamAccountMapper.updateByPrimaryKey(user);
	}

	/**
	 * 
	 *
	 * @Description: 删除用户 @param @param userId 设定文件 @author ssd @date 2015-4-30
	 *               下午4:42:07 @return void 返回类型 @throws
	 */
	public void deleteUser(int userId) {
		pamAccountMapper.deleteByPrimaryKey(userId);
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(int userId, String newPassword) {
		PamAccount user = pamAccountMapper.selectUser(userId);
		user.setLoginPassword(newPassword);
		passwordHelper.encryptPassword(user);
		pamAccountMapper.updateByPrimaryKey(user);
	}

	/**
	 * 
	 *
	 * @Description: 查找用户 @param @param userId @param @return 设定文件 @author
	 *               ssd @date 2015-4-30 下午4:42:17 @return PamAccount
	 *               返回类型 @throws
	 */
	public PamAccount findOne(int userId) {
		return pamAccountMapper.selectUser(userId);
	}

	public Object findAll(Object data) {
		List<PamAccount> list = pamAccountMapper.selectUnionBySelective((PamAccount) data);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
	}

	/**
	 * 
	 *
	 * @Description: 删除用户 @param @param data @param @return 设定文件 @author
	 *               ssd @date 2015-4-29 下午7:00:44 @return Object 返回类型 @throws
	 */
	public Object deleteUser(Object data) {
		HeadObject head = new HeadObject();
		try {
			Integer id = (Integer) data;
			// 先判断是否存绑定了角色，如果绑定了角色，则需要先删除用户角色关系表
			/*
			 * int count = roleUserLinkMapper.selectByAccountId(id); if(count ==
			 * 0 ){ pamAccountMapper.deleteByPrimaryKey(id);
			 * head.setRetCode(ErrorCode.SUCCESS); } else {
			 */

			HeadObject headObject = CommonHeadUtil.geneHeadObject("postWantService.findByAccoutId");
			ResultObject resultObjec = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject, id));
			int count = (Integer) resultObjec.getContent();
			if (count == 0) {
				roleUserLinkMapper.deleteByAccountId(id);
				pamAccountMapper.deleteByPrimaryKey(id);
				head.setRetCode(ErrorCode.SUCCESS);
			} else {
				head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}

	/**
	 * 
	 *
	 * @Description: 删除卖家用户 @param @param data @param @return 设定文件 @author
	 *               ssd @date 2015-4-29 下午7:00:44 @return Object 返回类型 @throws
	 */
	public Object deleteShopUser(Object data) {
		HeadObject head = new HeadObject();
		try {
			Integer id = (Integer) data;
			Member member = new Member();
			member.setAccountId(id);
			List<Member> list = memberMapper.findByMember(member);
			int mId = 0;
			if (list != null && list.size() > 0) {
				member = list.get(0);
				mId = member.getMemberId();
			}
			Long memberId = (long) mId;
			// 先判断是否存绑定了角色，如果绑定了角色，则需要先删除用户角色关系表
			int count = storeMemberMapper.selectByMemberId(memberId);
			if (count == 0) {
				pamAccountMapper.deleteByPrimaryKey(id);
				head.setRetCode(ErrorCode.SUCCESS);
			} else {
				head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}

	/**
	 * 
	 *
	 * @Description:获取页面用户信息列表 @param @param data @param @return 设定文件 @author
	 *                         ssd @date 2015-4-30 下午4:43:47 @return Object
	 *                         返回类型 @throws
	 */
	public Object getUserManagerList(Object data) {
		Map<String, Object> params = (Map<String, Object>) data;
		PageHelper.startPage(StringUtil.isEmpty(params.get("pageIndex")) ? GlobalStatic.DEFAULT_PAGE_INDEX : (Integer) params.get("pageIndex"), StringUtil.isEmpty(params.get("pageSize")) ? GlobalStatic.DEFAULT_PAGE_SIZE : (Integer) params.get("pageSize"));
		ResultPage<UserListDTO> page = new ResultPage<UserListDTO>(pamAccountMapper.selectAllUserRole());
		JSONObject json = JSONObject.fromObject(page);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
	}

	/**
	 * 
	 *
	 * @Description:获取页面用户信息列表 @param @param data @param @return 设定文件 @author
	 *                         ssd @date 2015-4-30 下午4:43:47 @return Object
	 *                         返回类型 @throws
	 */
	public Object getShopUserList(Object data) {
		Map<String, Object> params = (Map<String, Object>) data;
		PageHelper.startPage(StringUtil.isEmpty(params.get("pageIndex")) ? GlobalStatic.DEFAULT_PAGE_INDEX : (Integer) params.get("pageIndex"), StringUtil.isEmpty(params.get("pageSize")) ? GlobalStatic.DEFAULT_PAGE_SIZE : (Integer) params.get("pageSize"));
		ResultPage<PamAccount> page = new ResultPage<PamAccount>(pamAccountMapper.selectAllShop());
		JSONObject json = JSONObject.fromObject(page);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
	}

	/**
	 * 
	 *
	 * @Description: 根据用户查询用户 @param @param data @param @return @param @throws
	 *               Exception 设定文件 @author ssd @date 2015-4-30
	 *               下午4:44:14 @return Object 返回类型 @throws
	 */
	public Object findUserByUsername(Object data) throws Exception {
		HeadObject head = new HeadObject();
		JSONObject content = JSONObject.fromObject(data);
		String userName = content.getString("userName");
		PamAccount user = findByUsername(userName);
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, JSONObject.fromObject(user));
	}

	/**
	 * 
	 *
	 * @Description: 根据用户名查询买家 @param @param data @param @return @param @throws
	 *               Exception 设定文件 @author ssd @date 2015-4-30
	 *               下午4:44:14 @return Object 返回类型 @throws
	 */
	public Object findMemByUsername(Object data) throws Exception {
		HeadObject head = new HeadObject();
		JSONObject content = JSONObject.fromObject(data);
		String userName = content.getString("userName");
		PamAccount user = pamAccountMapper.findMemByUsername(userName);
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, JSONObject.fromObject(user));
	}

	/**
	 * 
	 *
	 * @Description: 根据用户名查询卖家 @param @param data @param @return @param @throws
	 *               Exception 设定文件 @author ssd @date 2015-4-30
	 *               下午4:44:14 @return Object 返回类型 @throws
	 */
	public Object findShopByUsername(Object data) throws Exception {
		HeadObject head = new HeadObject();
		JSONObject content = JSONObject.fromObject(data);
		String userName = content.getString("userName");
		PamAccount user = pamAccountMapper.findShopByUsername(userName);
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, JSONObject.fromObject(user));
	}

	/**
	 * 
	 *
	 * @Description: 根据用户名查询平台管理员 @param @param
	 *               data @param @return @param @throws Exception 设定文件 @author
	 *               ssd @date 2015-4-30 下午4:44:14 @return Object 返回类型 @throws
	 */
	public Object findMpByUsername(Object data) throws Exception {
		HeadObject head = new HeadObject();
		JSONObject content = JSONObject.fromObject(data);
		String userName = content.getString("userName");
		PamAccount user = pamAccountMapper.findMpByUsername(userName);
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, JSONObject.fromObject(user));
	}

	/**
	 * 
	 *
	 * @Description: 根据用户名查找门户的用户，为了防止后台的账户能登录门户 @param @param
	 *               data @param @return @param @throws Exception 设定文件 @author
	 *               ssd @date 2015-4-30 下午4:44:14 @return Object 返回类型 @throws
	 */
	public Object findPortalByUsername(Object data) throws Exception {
		HeadObject head = new HeadObject();
		JSONObject content = JSONObject.fromObject(data);
		String userName = content.getString("userName");
		PamAccount user = pamAccountMapper.findPortalByUsername(userName);
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, JSONObject.fromObject(user));
	}

	/**
	 * 
	 *
	 * @Description: 根据用户名或者类型查询用户 @param @param
	 *               data @param @return @param @throws Exception 设定文件 @author
	 *               ssd @date 2015-4-30 下午4:44:14 @return Object 返回类型 @throws
	 */
	public Object findUserByUsernameOrType(Object data) throws Exception {
		HeadObject head = new HeadObject();
		JSONObject content = JSONObject.fromObject(data);
		String userName = content.getString("userName");
		String accountType = content.getString("accountType");
		PamAccount pa = new PamAccount();
		pa.setLoginName(userName);
		pa.setAccountType(accountType);
		PamAccount user = findByUsernameOrType(pa);
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, JSONObject.fromObject(user));
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public PamAccount findByUsernameOrType(PamAccount pa) {
		return pamAccountMapper.findByUsernameOrType(pa);
	}

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public PamAccount findByUsername(String userName) {
		return pamAccountMapper.findByUsername(userName);
	}

	/**
	 * 
	 *
	 * @Description: 根据用户名查询角色 @param @param data @param @return @param @throws
	 *               Exception 设定文件 @author ssd @date 2015-4-30
	 *               下午4:44:40 @return Object 返回类型 @throws
	 */
	public Object findRoles(Object data) throws Exception {
		HeadObject head = new HeadObject();
		JSONObject content = JSONObject.fromObject(data);
		String userName = content.getString("userName");
		Set<String> roles = findRolesByUserName(userName);
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, roles);
	}

	/**
	 * 
	 *
	 * @Description: 添加用户，并且根据角色ID将用户和角色关联 @param @param data @param @return
	 *               设定文件 @author ssd @date 2015-4-22 下午5:40:15 @return Object
	 *               返回类型 @throws
	 */
	public Object insertUser(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			UserAddDTO dto = (UserAddDTO) JSONObject.toBean(content, UserAddDTO.class);
			PamAccount pamAccount = findByUsername(dto.getLoginName());
			int accoutId = 0;
			if (null == pamAccount) {
				PamAccount user = new PamAccount();
				user.setAccountType("200");
				user.setLoginName(dto.getLoginName());
				user.setLoginPassword(dto.getLoginPassword());
				String date = DateUtils.getCurrentDate("");
				user.setCreateTime(date);
				user.setRegTime(date);
				user.setAccountStatus("1");
				createUser(user);

				PamAccount account = findByUsername(dto.getLoginName());
				accoutId = account.getAccountId();

				int roleId = dto.getRoleId();
				RoleUserLink link = new RoleUserLink();
				link.setAccountId(accoutId);
				link.setRoleId(roleId);
				/*
				 * Role role = new Role(); role.setRoleId(roleId); //PamAccount
				 * pa = new PamAccount(); //pa.setAccountId(accoutId);
				 * link.setRole(role); link.setUser(account);
				 */
				roleUserLinkMapper.insertSelective(link);
				head.setRetCode(ErrorCode.SUCCESS);
			} else {
				// accoutId = pamAccount.getAccountId();
				head.setRetCode(ErrorCode.IS_EXIST);
			}

		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}

	/**
	 * 为卖家账号添加卖家角色
	 * 
	 * @param data
	 * @return
	 */
	public Object insertShopUserRole(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			SiteUserRoleDTO dto = (SiteUserRoleDTO) JSONObject.toBean(content, SiteUserRoleDTO.class);
			int accountId = dto.getUserId();
			Member member = new Member();
			member.setAccountId(accountId);
			List<Member> list = memberMapper.findByMember(member);
			if (list != null && list.size() > 0) {
				member = list.get(0);

				int mId = member.getMemberId();
				Long memeberId = (long) mId;
				Roles role = rolesMapper.selectByPrimaryKey(dto.getRoleId());
				StoreMember smember = new StoreMember();
				smember.setCommanyId(role.getCompanyId());
				smember.setMemberId(memeberId);
				smember.setRoleId(role.getRolesId());
				smember.setStoreId(role.getStoreId());
				StoreMember sm = storeMemberMapper.selectByOtherId(smember);
				if (null == sm) {
					storeMemberMapper.insertSelective(smember);
				}

				head.setRetCode(ErrorCode.SUCCESS);
			} else {
				head.setRetCode(ErrorCode.IS_NOT_MEMBER);
			}

		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}

	/**
	 * 
	 *
	 * @Description: 更新用户 @param @param data @param @return 设定文件 @author
	 *               ssd @date 2015-4-29 下午7:46:11 @return Object 返回类型 @throws
	 */
	public Object updateUser(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			UserAddDTO dto = (UserAddDTO) JSONObject.toBean(content, UserAddDTO.class);
			PamAccount pamAccount = pamAccountMapper.selectByPrimaryKey(dto.getAccountId());
			if (null != pamAccount) {
				pamAccount.setLoginName(dto.getLoginName());
				pamAccount.setLoginPassword(dto.getLoginPassword());
				updateUser(pamAccount);

				RoleUserLink link = roleUserLinkMapper.findByAccountId(dto.getAccountId());
				if (null != link) {
					link.setRoleId(dto.getRoleId());
					roleUserLinkMapper.updateByPrimaryKey(link);
				}

				head.setRetCode(ErrorCode.SUCCESS);
			} else {
				head.setRetCode(ErrorCode.NO_DATA);
			}

		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findRolesByUserName(String username) {
		PamAccount user = pamAccountMapper.selectUserRoleByUsername(username);

		if (user == null) {
			return Collections.EMPTY_SET;
		}
		List<Role> roleList = user.getRoles();
		Set<String> roles = new HashSet<String>();
		if (null != roleList) {
			int len = roleList.size();
			if (len > 0) {
				for (int i = 0; i < roleList.size(); i++) {
					String role = roleList.get(i).getRoleName();
					roles.add(role);
				}
			}
		}
		return roles;
	}

	/**
	 * 
	 *
	 * @Description:根据用户名查询权限 @param @param data @param @return @param @throws
	 *                        Exception 设定文件 @author ssd @date 2015-4-30
	 *                        下午4:45:14 @return Object 返回类型 @throws
	 */
	public Object findPermissions(Object data) throws Exception {
		HeadObject head = new HeadObject();
		JSONObject content = JSONObject.fromObject(data);
		String userName = content.getString("userName");
		Set<String> permissions = findPermissionsByUsername(userName);
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, permissions);
	}

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findPermissionsByUsername(String username) {
		PamAccount user = pamAccountMapper.selectUserRoleByUsername(username);

		if (user == null) {
			return Collections.EMPTY_SET;
		}
		List<Role> roleList = user.getRoles();
		List<Integer> roles = new ArrayList<Integer>();
		if (null != roleList) {
			int len = roleList.size();
			if (len > 0) {
				for (int i = 0; i < roleList.size(); i++) {
					int role = roleList.get(i).getRoleId();
					roles.add(role);
				}
			}
		}
		return roleServiceImpl.findPermissions(roles.toArray(new Integer[0]));
	}

}
