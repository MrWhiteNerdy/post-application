<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <h1>Posts</h1>
    <c:if test="${empty posts}">
        <p>No posts yet. <a href="/posts/new">Add one now</a>!</p>
    </c:if>
    <div class="row">
        <c:forEach items="${posts}" var="post">
            <div class="col-md-4 my-2">
                <div class="card card-body">
                    <h5>${post.title}</h5>
                    <p>${fn:substring(post.body, 0, 40)}...</p>
                    <a href="/posts/${post.id}" class="btn btn-primary">Read More</a>
                </div>
            </div>
        </c:forEach>
    </div>
</t:layout>
