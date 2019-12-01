package com.scaleup.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scaleup.user.exception.RecordNotFoundException;
import com.scaleup.user.model.User;
import com.scaleup.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	     
	    @Autowired
	    private UserRepository userRepository;

    	@PersistenceContext
    	private EntityManager entityManager;
    		 
    	@Override
	    public List<User> getAllUsers()
	    {
	        List<User> employeeList = userRepository.findAll();
	         
	        if(employeeList.size() > 0) {
	            return employeeList;
	        } else {
	            return new ArrayList<User>();
	        }
	    }
	     @Override
	    public User getUserById(Long id) throws RecordNotFoundException
	    {
	        Optional<User> user = userRepository.findById(id);
	         
	        if(user.isPresent()) {
	            return user.get();
	        } else {
	            throw new RecordNotFoundException("No user record exist for given id");
	        }
	    }
	     @Override
	    public User createOrUpdateUser(User userEntity) {
	        Optional<User> user = userRepository.findById(userEntity.getId());
	         
	        if(user.isPresent())
	        {
	            User newEntity = user.get();
	            newEntity.setEmail(userEntity.getEmail());
	            newEntity.setFirstName(userEntity.getFirstName());
	            newEntity.setLastName(userEntity.getLastName());
	 
	            newEntity = userRepository.save(newEntity);
	             
	            return newEntity;
	        } else {
	        	userEntity = userRepository.save(userEntity);
	             
	            return userEntity;
	        }
	    }
	     @Override
	    public void deleteUserByEmail(String email) throws RecordNotFoundException
	    {
	         List<User> users= userRepository.findByEmail(email);
	         //System.err.println("****: "+users);
	         if(!users.isEmpty()) {
	        for (User user : users) {
				//System.err.println(user.getEmail() +" bdvjhdfjvk: "+user.getId());
				userRepository.deleteById(user.getId());
	        }
	         }else {
	            throw new RecordNotFoundException("No user record exist for given id");
	        }
	    }
	    
	    	@Override
	        public List<User> getFirstName(String queryParam) {
	    		
List<User> tquery = entityManager.createNamedQuery("User.findByFirstNameAndLastNameAndEmail", 
User.class)
            .setParameter("firstName","%"+queryParam+"%")
            .setParameter("lastName","%"+queryParam+"%")
            .setParameter("email", "%"+queryParam+"%").getResultList();
	    		System.err.println("%%%%: "+tquery);
	        return tquery;
	    	}
}
