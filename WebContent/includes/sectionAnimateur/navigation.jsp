<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
            
            
       <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">           
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="<%=request.getContextPath()%>/AuthentificationCookieServlet"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/ManagerFormationServlet?action=listeFormationsAnim"><i class="fa fa-list fa-fw"></i> Formations</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/ManagerStagiaireServlet?action=listeStagiaires"><i class="fa fa-group fa-fw"></i> Stagiaires</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/ManagerStagiaireServlet?action=listeAnimateurs"><i class="fa fa-unlock fa-fw"></i> Animateurs</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
