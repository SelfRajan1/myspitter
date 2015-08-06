
package rajan.springmvc.spitter.dao;

import rajan.springmvc.spitter.persistence.Spitter;

public interface SpitterDao {
	void addSpitter(Spitter spitter);

	void saveSpitter(Spitter spitter);

	Spitter getSpitterById(long id);

	Spitter getSpitterByUsername(String username);
}