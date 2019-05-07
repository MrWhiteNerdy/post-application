<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <c:if test="${not empty titleError}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${titleError}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>
    <c:if test="${not empty bodyError}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${bodyError}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>
    <h1>Edit Post</h1>
    <form action="/posts/${post.id}/edit" method="post">
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" name="title" id="title" value="${post.title}"/>
        </div>
        <div class="form-group">
            <label for="body">Body:</label>
            <textarea class="form-control" name="body" id="body" rows="5">${post.body}</textarea>
        </div>
        <button type="submit" class="btn btn-primary float-right">Submit</button>
    </form>
</t:layout>
