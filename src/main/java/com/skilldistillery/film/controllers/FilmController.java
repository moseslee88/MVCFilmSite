package com.skilldistillery.film.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.data.Film;
import com.skilldistillery.film.data.FilmDao;


@Controller
public class FilmController {


	@Autowired
	private FilmDao dao;
	
	@RequestMapping(path="home.do")
	public ModelAndView home() {
		  return new ModelAndView("WEB-INF/views/home.jsp");
		}

		@RequestMapping(path = "getTitle.do")
		public ModelAndView getFilmTitleById(@RequestParam(name = "filmId") Integer filmId) {
		  String viewName = "WEB-INF/views/home.jsp";
		  ModelAndView mv = new ModelAndView(viewName);
		  String title = dao.getFilmTitleById(filmId);
		  mv.addObject("filmTitle", title);
		  return mv;
		}
		
		
		@RequestMapping(path = "getKeyword.do")
		//public ModelAndView getFilmTitleByKeyword(@RequestParam(name = "filmkey") String filmkey) {
			public ModelAndView getFilmTitleByKeyword(@RequestParam("filmkey") String filmkey) {
		  String viewName = "WEB-INF/views/home.jsp";
		  ModelAndView mv = new ModelAndView(viewName);
		  List<Film> title = dao.getFilmTitleByKeyword(filmkey);
		  mv.addObject("filmTitlekey", title); 
		  mv.addObject("falsetitle", filmkey);  
		  return mv;
		}
		
		
		@RequestMapping(path = "getFilms.do",
				method=RequestMethod.GET)
		//public ModelAndView getFilmTitleByKeyword(@RequestParam(name = "filmkey") String filmkey) {
		public ModelAndView getAllFilms() {
			String viewName = "AllFilms.jsp";
			ModelAndView mv = new ModelAndView(viewName);
			List<Film> title = dao.getAllFilms();
			mv.addObject("filmTitlekey", title);  
			return mv;
		}
		
		
		@RequestMapping(path = "postTitle.do", 
				method=RequestMethod.POST)
		public ModelAndView addFilmTitle(Film film,
                RedirectAttributes redir, HttpSession session) {
			dao.addFilm(film);
			ModelAndView mv = new ModelAndView();
			System.out.println("film: " + film);
			redir.addFlashAttribute("filmlist", dao.getAllFilms());
			redir.addFlashAttribute("film", film);
			mv.setViewName("redirect:FilmAdded.do");
			return mv;
		}
		
	
	@RequestMapping(path="FilmAdded.do",       //here I implemented the mapping to handle post-Redirect
            method=RequestMethod.GET)     
public ModelAndView gotFilmandAddtoList(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("JSPafterAddedFilm.jsp");
		mv.addObject("filmlist", dao.getAllFilms());
		mv.addObject("film", film);
		return mv;
}
	
	// deletes FILM object after clicking button
	@RequestMapping(value = "FilmDeleted.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(@RequestParam(name = "filmId") int id) {
		dao.getAllFilms();
		dao.deleteFilm(id);
		ModelAndView mv = new ModelAndView();
	    System.out.println("film id deleted: " + id);
		mv.setViewName("JSPafterdelete.jsp");
		mv.addObject("filmlist", dao.getAllFilms());
		mv.addObject("id", id);
//		mv.addObject("film", film);

		return mv;
	}
	
	// here I do the updating of film
	@RequestMapping(path = "FilmUpdated.do", 
			method=RequestMethod.POST)
	public ModelAndView updateFilmTitle(Film film) {
		dao.updateFilm(film);  
		System.out.println(film);
		dao.getAllFilms();
		int id=film.getId();
		ModelAndView mv = new ModelAndView();
		System.out.println("film: " + film);
		mv.setViewName("JSPafterdelete.jsp");
		mv.addObject("film", film);
		mv.addObject("filmlist", dao.getAllFilms());
		return mv;
	}
	
	
//	@RequestMapping(value = "FilmUpdated.do", method = RequestMethod.POST)
//	public ModelAndView addEmployee(Employee employee, @RequestParam("address_id") int id) {
//		ModelAndView mv = new ModelAndView();
//		Film f = new Film();
//		f.setTitle(title);
//		mv.setViewName("JSPafterdelete.jsp");
//		dao.update......;
//		return mv;
//	}
		

	
}
