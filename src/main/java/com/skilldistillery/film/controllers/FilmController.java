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
	
	@RequestMapping
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
			String viewName = "WEB-INF/views/home.jsp";
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
	public ModelAndView deleteEmployee(@RequestParam(name = "filmId") int id, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		dao.deleteFilm(id);
	    System.out.println("film id deleted: " + id);
		redir.addFlashAttribute("filmlist", dao.getAllFilms());
		mv.setViewName("redirect:FilmAdded.do");
		return mv;
	}
	
	@RequestMapping(path = "updatepostTitle.do", 
			method=RequestMethod.POST)
	public ModelAndView updateFilmTitle(Film film, RedirectAttributes redir) {
		dao.updateFilm(film);
		String viewName = "JSPafterAddedFilm.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		System.out.println("film: " + film);
		redir.addFlashAttribute("filmlist", dao.getAllFilms());
		mv.setViewName("redirect:FilmAdded.do");
		return mv;
	}
		

	
}
