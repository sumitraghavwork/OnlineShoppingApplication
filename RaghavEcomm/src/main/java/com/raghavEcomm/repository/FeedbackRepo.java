package com.raghavEcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {

}
