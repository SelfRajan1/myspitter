
package rajan.springmvc.spitter.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rajan.springmvc.spitter.dao.SpitterDao;
import rajan.springmvc.spitter.persistence.Spitter;

@Service("spitterService")
@Transactional(propagation = Propagation.REQUIRED)
public class SpitterServiceImpl implements SpitterService {

	@Resource(name = "spitterDao")
	private SpitterDao spitterDao;

	public void saveSpitter(Spitter spitter) {
		System.out.println("Inside SS");
		if (spitter.getId() == null) {
			spitterDao.addSpitter(spitter);
		} else {
			spitterDao.saveSpitter(spitter);
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Spitter getSpitter(long id) {
		return spitterDao.getSpitterById(id);
	}

	public Spitter getSpitter(String username) {
		return spitterDao.getSpitterByUsername(username);
	}
}