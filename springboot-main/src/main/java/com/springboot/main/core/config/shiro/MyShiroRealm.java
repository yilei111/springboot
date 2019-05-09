package com.springboot.main.core.config.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.main.eimm.permission.entity.Permission;
import com.springboot.main.eimm.permission.service.PermissionService;
import com.springboot.main.eimm.user.entity.User;
import com.springboot.main.eimm.user.service.UserService;
import javax.annotation.Resource;
import java.util.*;

/**
 * @method shiro自定义Realm
 * @author Mr yi
 * @time 2019年5月6日
 */
public class MyShiroRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;

	@Resource
	private PermissionService permissionService;

	@Autowired
	private RedisSessionDAO redisSessionDAO;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		User user = (User) SecurityUtils.getSubject().getPrincipal(); 
		List<Permission> permissionList = permissionService.getPermissionListByUserId(user.getId());
		// 获取该用户具有的全部权限
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (Permission permission : permissionList) {
			String url = permission.getPermission_url();
			if(StringUtils.isNotBlank(url))
			info.addStringPermission(permission.getPermission_url());
		}
		return info;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String userName = (String) token.getPrincipal();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", userName);
		User user = userService.selectUserByUserName(map);

		if (user == null || StringUtils.isBlank(userName)) {
			throw new UnknownAccountException();
		}
		if (StringUtils.equals("002", user.getUser_state())) { // 锁定
			throw new LockedAccountException(); // 帐号锁定
		}
		if (StringUtils.equals("003", user.getUser_state())) { // 禁用
			throw new DisabledAccountException("该账户已被禁用！");
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, // 用户
				user.getUser_pwd(), // 密码
				ByteSource.Util.bytes(userName), getName() // realm name
		);
		// 当验证都通过后，把用户信息放在session里
		/*
		 * Session session = SecurityUtils.getSubject().getSession();
		 * session.setAttribute("userSession", user);
		 * session.setAttribute("userSessionId", user.getId());
		 */
		return authenticationInfo;
	}

	/**
	 * @method 根据userId 清除当前session存在的用户的权限缓存（在shiro中权限清空后会重新调用授权方法）
	 * @author Mr yi
	 * @time 2019年5月6日
	 * @param userIds
	 */
	public void clearUserAuthByUserId(List<String> userIds) {
		if (null == userIds || userIds.size() == 0)
			return;
		// 获取所有session
		Collection<Session> sessions = redisSessionDAO.getActiveSessions();
		// 定义返回
		List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
		for (Session session : sessions) {
			// 获取session登录信息。
			Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if (null != obj && obj instanceof SimplePrincipalCollection) {
				// 强转
				SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
				// 判断用户，匹配用户ID。
				obj = spc.getPrimaryPrincipal();
				if (null != obj && obj instanceof User) {
					User user = (User) obj;
					// 比较用户ID，符合即加入集合
					if (null != user && userIds.contains(user.getId())) {
						list.add(spc);
					}
				}
			}
		}
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();
		for (SimplePrincipalCollection simplePrincipalCollection : list) {
			realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
		}
	}
}
