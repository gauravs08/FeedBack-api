package com.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.Expose;

@XmlRootElement 
public class Feedbacks {

	public Feedbacks() {}
	@Expose
	List<Feedback> feedbacks = new ArrayList<Feedback>();

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void addFeedback(Feedback feedback) {
		this.feedbacks.add(feedback);
	}
    
  
}