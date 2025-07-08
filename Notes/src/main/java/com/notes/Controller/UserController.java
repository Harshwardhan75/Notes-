package com.notes.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.notes.DAO.NoteRepository;
import com.notes.DAO.UserRepository;
import com.notes.Model.Note;
import com.notes.Model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NoteRepository noteRepository;

	@ModelAttribute
	public void commonAttribute(Model model, Principal principal) {
		model.addAttribute("user", this.userRepository.findByEmail(principal.getName()));
	}

	@GetMapping("/")
	public String dashboard(Model model) {
		return "user/user_dashboard";
	}

	@GetMapping("/profile")
	public String profile(Model model) {
		model.addAttribute("title", "User Profile");
		return "user/profile";
	}

	@GetMapping("/notes")
	public String viewNotes(Model model, Principal principal) {
		List<Note> notes = this.noteRepository.findByUser(this.userRepository.findByEmail(principal.getName()));
		model.addAttribute("notes", notes);
		return "user/allnotes";
	}

	@GetMapping("/create")
	public String createNote(Model model) {
		model.addAttribute("note", new Note());
		return "user/create";
	}

	@PostMapping("/save")
	public String createNoteHelper(@ModelAttribute Note note, Principal principal) {
		User user = this.userRepository.findByEmail(principal.getName());
		note.setUser(user);
		user.getNotes().add(note);
		this.userRepository.save(user);
		return "redirect:/user/";
	}

	@GetMapping("/delete/{id}")
	public String deleteNote(@PathVariable Integer id, Model model, Principal principal) {
		User user = this.userRepository.findByEmail(principal.getName());
		Note note = this.noteRepository.findById(id).get();
//		user.getNotes().remove(note);
//		this.userRepository.save(user);

		this.noteRepository.delete(note);
		return "redirect:/user/notes";
	}

	@GetMapping("/edit/{id}")
	public String editNote(@PathVariable Integer id, Model model) {
		Note note = this.noteRepository.findById(id).get();
		model.addAttribute("note", note);
		return "user/edit";
	}
	
	@PostMapping("/update/{id}")
	public String updateNote(@PathVariable Integer id, @ModelAttribute Note note) {
		Note noteOriginal = this.noteRepository.findById(id).get();
		noteOriginal.setTitle(note.getTitle());
		noteOriginal.setContent(note.getContent());
		this.noteRepository.save(noteOriginal);
		return "redirect:/user/notes";
	}
}
