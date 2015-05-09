package org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.persiste;

import org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, String>{

}
