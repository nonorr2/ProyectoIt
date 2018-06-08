<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:set name="theme" value="'simple'" scope="page" />
<!DOCTYPE html>
<html>
    <head>
        <title>Trabajo IT</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/estilo.css">   
        
        <s:if test="error">
            <%@include file="login.jsp"%>
            <script>
                document.getElementById('id01').style.display = 'block';
            </script>
        </s:if>
    </head>
    <body>
        
        <!-- Navbar -->
        <div class="w3-top">
            <div class="w3-bar w3-red w3-card w3-left-align w3-large">
                <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-red" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
                    <s:form action="inicio" method="post">
                    <a><s:submit value="HOME" cssClass="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" /></a>
                </s:form>
                <s:form action="temas" method="post">
                    <a><s:submit value="TEMAS" cssClass="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" /></a>
                </s:form>
                <s:form action="publicaciones" method="post">
                    <a><s:submit value="PUBLICACIONES" cssClass="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"/></a>
                </s:form>
                <s:form action="registro" method="post">
                    <a><s:submit value="REGISTRARSE"  cssClass="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white"/></a>
                </s:form>
                <a class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" onclick="document.getElementById('id01').style.display = 'block'">LOGIN</a>               
            </div>

            <!-- Navbar on small screens -->
            <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium w3-large">
                <s:form action="inicio" method="post">
                    <a><s:submit value="HOME" cssClass="w3-bar-item w3-button w3-padding-large" /></a>
                </s:form>
                <s:form action="temas" method="post">
                    <a><s:submit value="TEMAS" cssClass="w3-bar-item w3-button w3-padding-large" /></a>
                </s:form>
                <s:form action="publicaciones" method="post">
                    <a><s:submit value="PUBLICACIONES" cssClass="w3-bar-item w3-button w3-padding-large"/></a>
                </s:form>
                <s:form action="registro" method="post">
                    <a><s:submit value="REGISTRARSE"  cssClass="w3-bar-item w3-button w3-padding-large"/></a>
                </s:form>
                <a class="w3-bar-item w3-button w3-padding-large" onclick="document.getElementById('id01').style.display = 'block'"> Login</a>
            </div>
        </div>
        <%@include file="login.jsp" %>
        <script>
            // Get the modal
            var modal = document.getElementById('id01');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }

// Used to toggle the menu on small screens when clicking on the menu button
            function myFunction() {
                var x = document.getElementById("navDemo");
                if (x.className.indexOf("w3-show") == -1) {
                    x.className += " w3-show";
                } else {
                    x.className = x.className.replace(" w3-show", "");
                }
            }
        </script>


