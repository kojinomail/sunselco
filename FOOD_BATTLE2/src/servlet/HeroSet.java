package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemDao;
import dao.WeaponDao;
import model.HeroStatusBean;
import model.ItemBean;
import model.WeaponBean;

/**
 * Servlet implementation class HeroSet
 */
@WebServlet("/HeroSet")
public class HeroSet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


		ItemDao itemDao = new ItemDao();
		List<ItemBean> itemList = itemDao.findAll();
		HttpSession session3 = request.getSession();
		session3.setAttribute("itemList", itemList);

		WeaponDao weaponDao = new WeaponDao();
		List<WeaponBean> weaponList = weaponDao.findAll();
		HttpSession session4 = request.getSession();
		session4.setAttribute("weaponList", weaponList);

		String name = request.getParameter("name");

		HeroStatusBean hero = new HeroStatusBean(name,100,20,20,0);
		if( hero.getPersonal_belogingsList().size()==0) {
			hero.setPersonal_belogingsList(itemList);
			hero.setPersonal_belogingsList();
		}
		if( hero.getPersonal_equipmentList().size()==0) {
			hero.setPersonal_equipmentList(weaponList);
			hero.setPersonal_equipmentList();
			String syoki="はし";
			hero.setPersonal_equipmentList(syoki);
		}



		HttpSession session5 = request.getSession();
		session5.setAttribute("hero", hero);


		RequestDispatcher dispatcher = request.getRequestDispatcher("tolk.html");
		dispatcher.forward(request, response);

	}
}