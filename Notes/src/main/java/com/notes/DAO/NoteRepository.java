package com.notes.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.Model.Note;
import com.notes.Model.User;

public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	List<Note> findByUser(User user);
	
}
