package com.traveltimeaware.app.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.traveltimeaware.app.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}
