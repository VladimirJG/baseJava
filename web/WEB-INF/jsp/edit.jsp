<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page import="com.basejava.webapp.model.SectionType" %>
<%@ page import="com.basejava.webapp.model.ListSection" %>
<%@ page import="com.basejava.webapp.model.CompanySection" %>
<%@ page import="com.basejava.webapp.util.DateUtil" %>
<%@ page import="com.basejava.webapp.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/theme/${theme}.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/edit-resume-styles.css">
    <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<form method="post" action="resume" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="uuid" value="${resume.uuid}">
    <input type="hidden" name="theme" value="${theme}">
    <div class="scrollable-panel">
        <div class="form-wrapper">
            <div class="section">ФИО</div>
            <label>
                <input class="field" type="text" name="fullName" size=55 placeholder="ФИО" value="${resume.fullName}"
                       required>
            </label>

            <div class="section">Контакты</div>
            <c:forEach var="type" items="<%=ContactType.values()%>">
                <label>
                    <input class="field" type="text" name="${type.name()}" size=30 placeholder="${type.title}"
                           value="${resume.getContact(type)}">
                </label>
            </c:forEach>

            <div class="spacer"></div>

            <div class="section">Секции</div>

            <c:forEach var="type" items="<%=SectionType.values()%>">
                <c:set var="section" value="${resume.getSection(type)}"/>
                <jsp:useBean id="section" type="com.basejava.webapp.model.AbstractSection"/>
                <div class="field-label">${type.title}</div>
                <c:choose>
                    <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                        <label>
                            <textarea class="field" name='${type}'><%=section%></textarea>
                        </label>
                    </c:when>
                    <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                        <label>
                <textarea class="field"
                          name='${type}'><%=String.join("\n", ((ListSection) section).getStrings())%></textarea>
                        </label>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <c:forEach var="company" items="<%=((CompanySection) section).getCompanies()%>"
                                   varStatus="counter">
                            <c:choose>
                                <c:when test="${counter.index == 0}">
                                </c:when>
                                <c:otherwise>
                                    <div class="spacer"></div>
                                </c:otherwise>
                            </c:choose>

                            <label>
                                <input class="field" type="text" placeholder="Название" name='${type}' size=100
                                       value="${company.homePage.name}">
                            </label>
                            <label>
                                <input class="field" type="text" placeholder="Ссылка" name='${type}url' size=100
                                       value="${company.homePage.url}">
                            </label>

                            <c:forEach var="pos" items="${company.positions}">
                                <jsp:useBean id="pos" type="com.basejava.webapp.model.Company.Position"/>

                                <div class="date-section">
                                    <label>
                                        <input class="field date" name="${type}${counter.index}startDate"
                                               placeholder="Начало, ММ/ГГГГ"
                                               size=10
                                               value="<%=DateUtil.format(pos.getStartDate())%>">
                                    </label>
                                    <label>
                                        <input class="field date date-margin" name="${type}${counter.index}endDate"
                                               placeholder="Окончание, ММ/ГГГГ"
                                               size=10
                                               value="<%=DateUtil.format(pos.getEndDate())%>">
                                    </label>
                                </div>

                                <label>
                                    <input class="field" type="text" placeholder="Заголовок"
                                           name='${type}${counter.index}title' size=75
                                           value="${pos.title}">
                                </label>
                                <label>
                                    <textarea class="field" placeholder="Описание"
                                              name="${type}${counter.index}description">${pos.description}</textarea>
                                </label>

                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:forEach>

            <div class="spacer"></div>

            <div class="button-section">
                <button class="red-cancel-button" type="button" onclick="window.history.back()">Отменить</button>
                <c:if test="<%=!Config.get().isImmutable(resume.getUuid())%>">
                    <button class="green-submit-button" type="submit">Сохранить</button>
                </c:if>
            </div>

        </div>
    </div>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
