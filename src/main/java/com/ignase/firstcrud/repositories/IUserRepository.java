package com.ignase.firstcrud.repositories;

import com.ignase.firstcrud.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository <UserModel, Long> {
}
