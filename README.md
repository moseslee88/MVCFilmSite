###MVCFilmSite

<p>For this weekend, the Two primary goals of the assignment were to implement methods for creating, updating, and deleting records via a DAO (Data Access Object) and to create a full-stack, MySQL database-backed Spring MVC app that allows CRUD operations. I started by working on my data package: initially, I made a Film JavaBean to start so that the  Spring Framework can do its magic in a bean container. Last week in class, we learned that JavaBeans are simply Java classes which adhere to certain coding conventions. For example, classes that
                   -Have a public default (no argument) constructor
                   -allows access to properties using accessor (getter and setter) methods
                    -Implement java.io.Serializable
Then, I followed the rest of the 13 steps to start a Gradle WEB Project in Spring tool Suite (from https://github.com/SkillDistillery/SD11/blob/master/unit_2/week2/SpringMVC/01-IntroSpringMVC/13_steps.md) Before starting my project, I perused over SQL queries and subqueries and tried to understand/enhance my proficiency with the SQL language.  </p>

  I ended up creating a 'FilmController' that 
  Given more time, I would try to incorporate a method in which the user can add an actor or remove an actor from a film, and also I would have tried to beautify the home page with better usage of Bootstrap. 
  
  
  In this program, the user does have the capability of Full CRUD operations. One can view all the list of films in the home page, and then from there, the user can decide whether to Update an existing film in the database, or to remove it completely. Also, from the home page, you can click the 'Click here for NEW film you wish to add!' button and add a Film to the database. Since I incorporated a post-redirect GET into my code, the user cannot add the same film multiple times, even if they click the 'Refresh' button many times. The post-redirect-get is better explained visually here. https://i.stack.imgur.com/b0CDp.png   Once a particular film has been added, the user can then update the film's fields, or delete the film.  
  
  The technologies that I used in my project were Spring MVC, Apache Tomcat server,  the STS development environment, AWS, CSS (for rudimentary styling) and MySQL(Workbench). As a build tool, I utilized the magic of Gradle and its dependencies. For the front-end and creating the JSP, I used JavaServer Pages Standard Tag Library (JSTL). I encountered some challenges creating the JSP, but I learned that a web developer must always match the parameter fields in a form in a JSP with the exact names of the getters/setters in the POJO. Seeing the connection and how the back-end cooperates with the front-end was rewarding to witness. Overall, this was an exciting project to watch come to fruition.  

