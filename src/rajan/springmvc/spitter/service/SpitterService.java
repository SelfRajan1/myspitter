
package rajan.springmvc.spitter.service;

import rajan.springmvc.spitter.persistence.Spitter;

public interface SpitterService {

	void saveSpitter(Spitter spitter);

	Spitter getSpitter(long id);

	Spitter getSpitter(String username);

}