package org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.business.impl;

import javax.annotation.Resource;

import org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.business.UserService;
import org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.model.User;
import org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.persiste.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public void add(User user) {
		user.setId(null);
		userDao.save(user);
	}

}
