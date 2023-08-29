<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Списки резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table cellspacing="8" cellpadding="10" border="10" width="100%">
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Delete</th>
            <th>Edit</th>
        </tr>
        <jsp:useBean id="resumes" scope="request" type="java.util.List"/>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume"/>
            <tr>
                <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                <td><%=ContactType.MAIL.toHtml(resume.getContact(ContactType.MAIL))%>
                </td>
                <td><%=ContactType.TELEPHONE.toHtml(resume.getContact(ContactType.TELEPHONE))%>
                </td>
                <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png" alt="Delete"></a></td>
                <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png" alt="Edit"></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
